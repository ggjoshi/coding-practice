import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Multi thread sync.
 */
public class TwoThreadSync {
    private ExecutorService executorService;

    public TwoThreadSync() {
        executorService = Executors.newFixedThreadPool(10);
    }

    private static class IntegerHolder {
        public int value;
        public IntegerHolder(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    private static class NumberPrintingThread implements Runnable {
        private boolean iAmOdd;
        private int n;
        private IntegerHolder currentValue;
        private Lock lock;
        private Condition condition;

        public NumberPrintingThread(int n,
                                    IntegerHolder currentValue,
                                    boolean iAmOdd,
                                    Lock lock,
                                    Condition condition) {
            this.currentValue = currentValue;
            this.lock = lock;
            this.n = n;
            this.iAmOdd = iAmOdd;
            this.condition = condition;
        }

        public void run() {
            try {
                while(true) {
                    lock.lock();
                    while ((currentValue.getValue() % 2 == 0) == iAmOdd &&
                        (currentValue.getValue() < n)) {
                        condition.await(1, TimeUnit.MILLISECONDS);
                    }
                    System.out.println("iAmOdd=" + iAmOdd + " : " + currentValue);
                    currentValue.increment();
                    condition.signal();
                    if (currentValue.getValue() >= n) {
                        return;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Exception in waiting; quiting from iAmOdd = " + iAmOdd);
                System.out.println(ex.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    public void printNumbersTillOnTwoThreads(int n) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        IntegerHolder currentValue = new IntegerHolder(0);

        NumberPrintingThread oddThread = new NumberPrintingThread(n, currentValue, true, lock, condition);
        NumberPrintingThread evenThread = new NumberPrintingThread(n, currentValue, false, lock, condition);
        executorService.submit(oddThread);
        executorService.submit(evenThread);
        while(currentValue.getValue() < n) {
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        TwoThreadSync twoThreadSync = new TwoThreadSync();
        twoThreadSync.printNumbersTillOnTwoThreads(2);
        twoThreadSync.executorService.shutdown();
        try {
            twoThreadSync.executorService.awaitTermination(50, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {

        } finally {
            twoThreadSync.executorService.shutdownNow();
        }
    }
}
