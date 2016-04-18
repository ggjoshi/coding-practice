/**
 *
 */
public class RecursionAndDP {
    /**
     * child running up staircase with n steps.
     * can hop either 1, 2, 3 steps
     * write a method to count how many ways child can run up the stairs.
     * To reach step n, child may have hopped 1 2 or 3 steps so
     * NoOfWays(n) = NoOfWays(n - 1) + NoOfWays(n - 2) + NoOfWays(n - 3)
     * NoOfWays(0) = 1
     * NoOfWays(1) = 1
     * NoOfWays(2) = 2
     * to get to 3; possible sequences are
     * 1, 1, 1
     * 1, 2
     * 2, 1
     * 3
     * to get to 4, ways will be
     * 1, 1, 1, 1
     * 1, 1, 2
     * 1, 2, 1
     * 2, 1, 1
     * 2, 2
     * 1, 3
     * 3, 1
     *
     */
    public int noOfWaysToStepN(int n) {
        System.out.println("n = " + n);
        if(n < 0) {
            return 0;
        }
        if(n == 0 || n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int noOfWaysTillNMinus3 = 1, noOfWaysTillNMinus2 = 1, noOfWaysTillNMinus1 = 2;
        for(int i = 3; i <= n; i++) {
            int current = noOfWaysTillNMinus1 + noOfWaysTillNMinus2 + noOfWaysTillNMinus3;
            noOfWaysTillNMinus3 = noOfWaysTillNMinus2;
            noOfWaysTillNMinus2 = noOfWaysTillNMinus1;
            noOfWaysTillNMinus1 = current;
        }
        return noOfWaysTillNMinus1;
    }

    public static void main(String[] args) {
        RecursionAndDP recursionAndDP = new RecursionAndDP();
        System.out.println(recursionAndDP.noOfWaysToStepN(3));
        System.out.println(recursionAndDP.noOfWaysToStepN(4));
        System.out.println(recursionAndDP.noOfWaysToStepN(5));
    }
}
