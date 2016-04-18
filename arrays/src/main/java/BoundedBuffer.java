import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * There is a fixed side buffer
 * There is a producer and a consumer consuming from it.
 * Write code which is thread-safe and lets make progress to both.
 */
public class BoundedBuffer {
    private int [] buffer;
    private int head = 0, tail = 0, size = 0, capacity;
    private Lock lock;
    private Condition notFull, notEmpty;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void add(int data) {
        try {
            lock.lock();
            while (size == buffer.length) {
                System.out.println("Add waiting for not full");
                notFull.await();
            }

            buffer[head] = data;
            head = (head + 1) % buffer.length;
            size++;
            if (size == 1) {
                notEmpty.signal();
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally {
            lock.unlock();
        }
    }

    public int remove() {
        try {
            lock.lock();
            while (size == 0) {
                notEmpty.await();
            }
            int result = buffer[tail];
            tail = (tail + 1) % buffer.length;
            size--;
            if(size == capacity - 1) {
                notFull.signal();
            }
            return result;
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally{
            lock.unlock();
        }
        return 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return getCapacity() == getSize();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public Producer createProducer() {
        return new Producer(this);
    }

    public Consumer createConsumer() {
        return new Consumer(this);
    }

    private static abstract class BoundedBufferUser implements Runnable {
        private volatile boolean shutdown;
        protected final BoundedBuffer boundedBuffer;

        public BoundedBufferUser(BoundedBuffer boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
            shutdown = false;
        }

        public void run() {
            while (!shutdown) {
                useBuffer();
            }
            System.out.println("Shutdown = " + shutdown);
        }

        public void shutdown() {
            shutdown = true;
        }

        public abstract void useBuffer();
    }

    private static class Producer extends BoundedBufferUser {
        private int currentValue = 0;
        public Producer(BoundedBuffer boundedBuffer) {
            super(boundedBuffer);
        }

        @Override
        public void useBuffer() {
            boundedBuffer.add(currentValue++);
            System.out.println("Producer added " + currentValue);
        }
    }

    private static class Consumer extends BoundedBufferUser {
        public Consumer(BoundedBuffer boundedBuffer) {
            super(boundedBuffer);
        }

        @Override
        public void useBuffer() {
            System.out.println("Consumer removed " + boundedBuffer.remove());
        }
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer(20);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Producer producer = boundedBuffer.createProducer();
        Consumer consumer = boundedBuffer.createConsumer();
        executorService.execute(producer);
        executorService.execute(consumer);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Sleep interrupted");
        }

        producer.shutdown();
        consumer.shutdown();
        executorService.shutdownNow();
    }
}
