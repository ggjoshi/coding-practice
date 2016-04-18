import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Limits rate per given number per second.
 */
public class RateLimiter {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int limitPerSecond;
    private long currentSlotStartTime;
    private long currentCount;

    public RateLimiter(int limitPerSecond) {
        this.limitPerSecond = limitPerSecond;
        this.currentSlotStartTime = 0;
        this.currentCount = 0;
    }

    public void acquire(int permits) {
        if(permits > limitPerSecond) {
            throw new IllegalArgumentException("Permits can not be greater than rate.");
        }
        long currentTime = System.currentTimeMillis();
        long expectedCurrentSlotStartTime = currentTime % 1000;
        lock.lock();
        if(currentSlotStartTime == expectedCurrentSlotStartTime) {
            currentCount += permits;
            if(currentCount > limitPerSecond) {
                while (currentTime < currentSlotStartTime + 1000) {
                    try {
                        condition.await(currentSlotStartTime + 1000 - currentTime, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException ex) {
                        System.out.println("Thread interrupted; going back to wait again");
                    }
                }
            }
        } else {
            currentSlotStartTime = expectedCurrentSlotStartTime;
            currentCount = permits;
        }
        lock.unlock();
    }
}
