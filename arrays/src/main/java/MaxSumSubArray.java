import java.util.Arrays;

/**
 * Maximum sum sub array.
 */
public class MaxSumSubArray {
    public int findMaxSumSubArray(int [] array) {
        if(array == null) {
            throw new IllegalArgumentException("Null array passed in");
        }
        int maxSum = Integer.MIN_VALUE, maxStart = -1, maxEnd = -1;
        int currentStart = 0, currentSum = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum = currentSum + array[i];
            if(currentSum > maxSum) {
                maxStart = currentStart;
                maxEnd = i;
                maxSum = currentSum;
            }

            if(currentSum < 0) {
                currentSum = 0;
                currentStart = i + 1;
            }
        }

        System.out.println("The max sum for array " + Arrays.toString(array) +
                " is " + maxSum +
                " between " +
                maxStart + " and " +
                maxEnd);
        return maxSum;
    }

    public static void main(String [] args) {
        MaxSumSubArray maxSumSubArray = new MaxSumSubArray();
        maxSumSubArray.findMaxSumSubArray(new int [] {-4, -3});
    }
}
