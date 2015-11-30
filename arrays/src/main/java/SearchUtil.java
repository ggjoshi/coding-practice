/**
 * Util class containing methods related to searching in an array/list etc.
 */
public class SearchUtil {
    /*
     * Given a sorted input array and a target,
     * find the index where input array contains target.
     * Return -1 if the target it not found.
     */
    public static int binarySearch(int [] input, int target) {
        if (input == null) {
            throw new IllegalArgumentException("Input array was null");
        }

        int low = 0, high = input.length - 1, mid = 0;
        while(low <= high) {
            mid = low + (high - low)/2;
            if(input[mid] == target) {
                return mid;
            } else if (input[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
