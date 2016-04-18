import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DelayedScheduler which can schedule Runnable tasks with a delay.
 */
public class DelayedScheduler {
    private Lock lock;
    private PriorityQueue<DelayedTask> priorityQueue;
    private Condition condition;
    private ExecutorService executorService;
    private DelayedTaskDispatcher delayedTaskDispatcher;

    private static class DelayedTask implements Comparable<DelayedTask>{
        private Runnable runnable;
        private long scheduledTime;
        public DelayedTask(Runnable runnable,
                           long scheduledTime) {
            this.runnable = runnable;
            this.scheduledTime = scheduledTime;
        }

        public int compareTo(DelayedTask other) {
            long diff = scheduledTime - other.scheduledTime;
            if(diff == 0) {
                return 0;
            } else if(diff > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public DelayedScheduler(int numThreads) {
        priorityQueue = new PriorityQueue<DelayedTask>();
        lock = new ReentrantLock();
        condition = lock.newCondition();
        executorService = Executors.newFixedThreadPool(numThreads + 1);
        delayedTaskDispatcher = new DelayedTaskDispatcher(executorService, priorityQueue, lock, condition);
        executorService.execute(delayedTaskDispatcher);
    }

    public void schedule(Runnable runnable, long delayInMs) {
        try {
            long currentTime = System.currentTimeMillis();
            System.out.println("Schedule time = " + currentTime);
            lock.lock();
            priorityQueue.offer(new DelayedTask(runnable, currentTime + delayInMs));
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        delayedTaskDispatcher.shutdownDispatcher();
        executorService.shutdown();
        try {
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            System.out.println("Exception in shutting down " + ex.getMessage());
        }
        executorService.shutdownNow();
    }

    private static class DelayedTaskDispatcher implements Runnable {
        private static long DEFAULT_WAIT_TIME_MS = 10;
        private boolean shutdown;
        private Lock lock;
        private PriorityQueue<DelayedTask> priorityQueue;
        private Condition condition;
        private ExecutorService executorService;

        public DelayedTaskDispatcher(ExecutorService executorService,
                                     PriorityQueue<DelayedTask> priorityQueue,
                                     Lock lock,
                                     Condition condition) {
            this.lock = lock;
            this.priorityQueue = priorityQueue;
            this.condition = condition;
            this.executorService = executorService;
            this.shutdown = false;
        }

        public void run() {
            try {
                lock.lock();
                while (!shutdown) {
                    long waitTime = DEFAULT_WAIT_TIME_MS;
                    if(!priorityQueue.isEmpty()) {
                        long currentTime = System.currentTimeMillis();
                        long earliestTaskTime = priorityQueue.peek().scheduledTime;
                        waitTime = earliestTaskTime - currentTime;
                    }

                    if(waitTime > 0) {
                        try {
                            condition.await(waitTime, TimeUnit.MILLISECONDS);
                        } catch (Exception ex) {
                            System.out.println("Exception in waiting; ignoring " + ex.toString());
                        }
                    } else {
                        executorService.execute(priorityQueue.poll().runnable);
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        public void shutdownDispatcher() {
            try {
                lock.lock();
                shutdown = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        DelayedScheduler delayedScheduler = new DelayedScheduler(5);
        delayedScheduler.schedule(new Runnable() {
            public void run() {
                System.out.println("Execution time task1 - " + System.currentTimeMillis());
            }
        }, 1000);

        delayedScheduler.schedule(new Runnable() {
            public void run() {
                System.out.println("Execution time task1 - " + System.currentTimeMillis());
            }
        }, 3000);

        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println("Main thread sleep exception " + ex.getMessage());
        }
        delayedScheduler.shutdown();
    }
}
