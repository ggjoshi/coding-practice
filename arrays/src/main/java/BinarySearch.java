import java.util.Arrays;
import java.util.List;

/**
 * Binary search utilities
 */
public class BinarySearch {
    public static int searchFirstOccurence(List<Integer> values, int target) {
        int low = 0, high = values.size() - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(values.get(mid) < target) {
                low = mid + 1;
            } else if(values.get(mid) > target) {
                high = mid - 1;
            } else {
                if(mid == 0 ||
                    values.get(mid - 1) != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String [] args) {
        List<Integer> values = Arrays.asList(-14, -10, 2, 108, 108, 243, 285, 285, 285, 401);
        System.out.println(BinarySearch.searchFirstOccurence(values, -14));
        System.out.println(BinarySearch.searchFirstOccurence(values, 401));
        System.out.println(BinarySearch.searchFirstOccurence(values, -15));
        System.out.println(BinarySearch.searchFirstOccurence(values, 450));
        System.out.println(BinarySearch.searchFirstOccurence(values, 108));
        System.out.println(BinarySearch.searchFirstOccurence(values, 285));
    }
}
