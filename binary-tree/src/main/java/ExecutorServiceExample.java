import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
public class ExecutorServiceExample {
    private static class MyThread implements Runnable {
        private String message;
        public MyThread(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println("I ran with - " + message);
        }
    }

    private static class MyCallable implements Callable<String> {
        private String message;
        public MyCallable(String message) {
            this.message = message;
        }

        public String call() {
            System.out.println("I got called with - " + message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return message;
        }
    }

    public static void main(String [] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyThread [] myThreads = new MyThread[10];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread("Runnable " + i);
            executorService.execute(myThreads[i]);
        }

        MyCallable [] myCallables = new MyCallable[10];
        for (int i = 0; i < myCallables.length; i++) {
            myCallables[i] = new MyCallable("Callable " + i);
        }

        executorService.invokeAny(Arrays.asList(myCallables));
        System.out.println("Statement after invoke all.");
        executorService.shutdown();

    }
}
