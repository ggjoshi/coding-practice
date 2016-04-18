/**
 * Fibonacci generator
 */
public class FibonacciGenerator {
    /*
     * Get nth fibonacci number
     */
    public double fibo(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("Can not get fibonacci number for < 0");
        }
        if(n <= 1) {
            return 1;
        }
        double nMinus2 = 1, nMinus1 = 1;
        for (int i = 2; i <= n; i++) {
            double current = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = current;
        }
        return nMinus1;
    }

    public static void main(String [] args) {
        FibonacciGenerator fibonacciGenerator = new FibonacciGenerator();
        for (int i = 0; i < 20; i++) {
            System.out.println("n = " + i + " fibo = " + fibonacciGenerator.fibo(i));
        }
    }
}
