import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    public int searchInSortedRotatedArray(int [] array, int target) {
        if(array == null || array.length == 0) {
            return -1;
        }
        return searchInSortedRotatedArray(array, 0, array.length - 1, target);
    }

    private boolean isInBetween(int target, int [] array, int pos1, int pos2) {
        return (target >= array[pos1] && target <= array[pos2]);
    }

    // Find  5 in {11, 18 19, 20, 25, 1, 2, 4, 5}
    // Find  11 in {11, 18 19, 20, 25, 1, 2, 4, 5}
    // find 4 in {3, 4, 3, 3, 3}
    public int searchInSortedRotatedArray(int [] array, int low, int high, int target) {
        System.out.println("Searching for " + target);
        if(low > high) {
            return -1;
        }

        while(low <= high) {
            int mid = low + (high - low)/2;
            if(array[mid] == target) {
                return mid;
            } else if(array[mid] < array[high]) {
                if(isInBetween(target, array, mid, high)) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else if(array[low] < array[mid]) {
                if(isInBetween(target, array, low, mid)) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // Search in both arrays.
                int found = searchInSortedRotatedArray(array, low, mid - 1, target);
                if(found != -1) {
                    return found;
                }
                return searchInSortedRotatedArray(array, mid + 1, high, target);
            }
        }
        return -1;
    }

    /*
     * List with no bounds;
     * Contains positive sorted integers; returns -1 if
     * there is no integer at index i otherwise returns the value at index i
     */
    public static class Listy {
        public int getElementAt(long i) {
            return (i <= Integer.MAX_VALUE ? (int)i : -1);
        }
    }

    public long searchListy(Listy listy, long target) {
        System.out.println("Searching in listy for " + target);
        // find range for search
        long low = 0, high = 1;
        while(true) {
            int valueAtHigh = listy.getElementAt(high);
            if(valueAtHigh == -1) {
                break;
            } else if(valueAtHigh < target) {
                low = high;
                high = high * 2;
            } else {
                if(valueAtHigh == target) {
                    return high;
                } else {
                    break;
                }
            }
        }

        while(low <= high) {
            long mid = low + (high - low)/2;
            int midValue = listy.getElementAt(mid);
            if(midValue == target) {
                return mid;
            } else if((midValue == -1) || (midValue > target)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public int findNextNonEmptyString(String [] strings, int current, int low, int high) {
        int start = current;
        while(start <= high) {
            if(!strings[start++].equals("")) {
                return start - 1;
            }
        }
        start = current - 1;
        while(start >= low) {
            if(!strings[start--].equals("")) {
                return start + 1;
            }
        }
        return -1;
    }

    public int binarySearchSparseArray(String [] strings, String target) {
        System.out.println("Sparse binary search for " + target);
        if(strings == null || strings.length == 0) {
            return -1;
        }
        if(target == null) {
            return -1;
        }

        int low = 0, high = strings.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            int nonEmptyMid = findNextNonEmptyString(strings, mid, low, high);
            if(nonEmptyMid == -1) {
                break;
            }
            int compareResult = target.compareTo(strings[nonEmptyMid]);
            if(compareResult == 0) {
                return nonEmptyMid;
            } else if(compareResult < 0) {
                high = nonEmptyMid - 1;
            } else {
                low = nonEmptyMid + 1;
            }
        }
        return -1;
    }

    public static void main(String [] args) {
        Set set = new TreeSet();
        set.add(new Integer(1));
        List list = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        SearchUtil searchUtil = new SearchUtil();
        int [] arr = {11, 18, 19, 20, 25, 1, 2, 4, 5};
        System.out.println(searchUtil.searchInSortedRotatedArray(arr, 5));
        System.out.println(searchUtil.searchInSortedRotatedArray(arr, 11));
        System.out.println(searchUtil.searchInSortedRotatedArray(arr, 19));
        System.out.println(searchUtil.searchInSortedRotatedArray(arr, 29));

        System.out.println(searchUtil.searchListy(new Listy(), 0));
        System.out.println(searchUtil.searchListy(new Listy(), 29));
        System.out.println(searchUtil.searchListy(new Listy(), (long)Integer.MAX_VALUE + 1l));
        System.out.println(searchUtil.searchListy(new Listy(), -1));

        // {"a", "", "", "b", "", "", "", "c"} find "c" in this array.
        // {"a", "", "", "b", "", "", "", "c"} find "d" in this array.
        // {"b", "", "", "b", "", "", "", "d"} find "a" in this array.

        System.out.println(searchUtil.binarySearchSparseArray(new String[]{"a", "", "", "b", "", "", "", "c"}, "c" ));
        System.out.println(searchUtil.binarySearchSparseArray(new String[]{"a", "", "", "b", "", "", "", "c"}, "d"));
        System.out.println(searchUtil.binarySearchSparseArray(new String[]{"b", "", "", "b", "", "", "", "d"}, "a"));
        System.out.println(searchUtil.binarySearchSparseArray(new String[]{"b", "", "", "b", "", "", "", "d"}, "b"));
    }
}
