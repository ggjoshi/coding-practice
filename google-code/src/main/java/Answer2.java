import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Answer2 {
    /*
     * Finds out if this has x elements visible from left.
     */
    private static boolean isValidPermutationFromLeft(int [] permutation, int visibleCount) {
        Deque<Integer> stack = new ArrayDeque<Integer>(permutation.length);
        stack.push(permutation[0]);
        int currentVisibleCount = 1;
        for(int i = 1; i < permutation.length; i++) {
            if(permutation[i] > stack.peek()) {
                stack.push(permutation[i]);
                currentVisibleCount++;
            }
        }
        return currentVisibleCount == visibleCount;
    }

    /*
     * Reverses an array.
     */
    private static void reverse(int [] permutation) {
        int low = 0, high = permutation.length - 1;
        while (low < high) {
            swap(permutation, low, high);
            low++;
            high--;
        }
    }

    /*
     * Checks if it is valid permutation from left and right.
     */
    private static boolean isValidPermutation(int [] permutation, int x, int y) {
        if(!isValidPermutationFromLeft(permutation, x)) {
            return false;
        }
        reverse(permutation);
        boolean result = isValidPermutationFromLeft(permutation, y);
        reverse(permutation);
        return result;
    }

    /*
     * Swaps two elements in an array.
     */
    private static void swap(int [] permutation, int pos1, int pos2) {
        int temp = permutation[pos1];
        permutation[pos1] = permutation[pos2];
        permutation[pos2] = temp;
    }

    /*
     * This generates permutations by swapping start position element with every element in the array.
     * For each permutation it checks if it is valid permutation with respect to num of visible rabbits.
     */
    private static BigInteger findPermutationsWhichSatisfyConstraintHelper(int [] permutation,
                                                                           int start, int x, int y) {
        if(start == permutation.length) {
            return isValidPermutation(permutation, x, y) ? BigInteger.ONE : BigInteger.ZERO;
        }

        BigInteger result = BigInteger.ZERO;

        for(int i = start; i < permutation.length; i++) {
            swap(permutation, i, start);
            result = result.add(findPermutationsWhichSatisfyConstraintHelper(permutation, start + 1, x, y));
            swap(permutation, i, start);
        }
        return result;
    }

    /*
     * Calls a recursive function to find valid permutations.
     */
    private static BigInteger findPermutationsWhichSatisfyConstraint(int [] permutation, int x, int y) {
        return findPermutationsWhichSatisfyConstraintHelper(permutation, 0, x, y);
    }


    /*
     * Fill up a permutation array with numbers from 1 to n and then count valid permutations.
     */
    private static BigInteger computeNumOfWaysForArrangingRabbits(int x, int y, int n) {
        int [] permutation = new int [n];
        for(int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }
        return findPermutationsWhichSatisfyConstraint(permutation, x, y);
    }

    /**
     * This uses a brute force approach to generate permutations of integers from 1 to n and
     * find out which of these have x visible from left and y from right.
     * This could be simplified and optimized much more by using theory of combinations and permutations.
     */
    public static String answer(int x, int y, int n) {
        return computeNumOfWaysForArrangingRabbits(x, y, n).toString();
    }

    public static void main(String[] args) {
        System.out.println(answer(10, 25, 40));
        System.out.println(answer(1, 2, 6));
    }
}
