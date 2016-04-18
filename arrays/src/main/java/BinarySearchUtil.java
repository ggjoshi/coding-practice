import java.util.Arrays;

/*
 * Find the largest value less than K in a sorted array of ints.
 * throw ValueNotFoundException() if not found;
 */
class BinarySearchUtil {
    /*
    * do binary search; 
    * if you find a value >= k, look at previous value to see if that is the one < k
    * return that.
    * 1 5 8 11 15
    * mid = low + (high - low)/2;
    * if(array[mid] < k) {
    *    if(mid == array.length - 1 ||
    *       array[mid + 1] >= k) {
    *       return mid;
    *    } else {
    *       low = mid + 1;
    *    }
    * } else {
    *   high = mid - 1;
    * }
    * return -1;
    */
    public static int findLargestValueLessThan(int [] array, int k)
        throws Exception {
        if(array == null || array.length == 0) {
            throw new Exception("Invalid array");
        }
        int low = 0, high = array.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(array[mid] < k) {
                if((mid == array.length - 1) ||
                    (array[mid + 1] >= k)){
                    return array[mid];
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        throw new Exception("Such value not found");
    }

    public static int findSmallestValueLargerThan(int [] array, int k)
        throws Exception {
        if(array == null || array.length == 0) {
            throw new Exception("Invalid array");
        }

        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if(array[mid] > k) {
                if(mid == 0 ||
                    array[mid - 1] <= k) {
                    return array[mid];
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        throw new Exception("Value not found");
    }

    // 1 5 5 5 9 17 20 target = 5 answer = smallestGreaterThan = 4, element = 9
    // 1 2 3 4
    public int findSmallestElementGreaterThan(int [] array, int target) {
        int low = 0, high = array.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(array[mid] > target) {
                if(mid == 0 || array[mid - 1] <= target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return array.length;
    }

    // 1 5 5 5 9 17 20 target = 5 answer = largestLessThan = 0, element = 1
    public int findLargestElementLessThan(int [] array, int target) {
        int low = 0, high = array.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(array[mid] < target) {
                if(mid == array.length - 1 ||
                    array[mid + 1] >= target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int countOccurences(int [] array, int target) {
        System.out.println("array = " + Arrays.toString(array) + " target = " + target);
        if(array == null || array.length == 0) {
            return 0;
        }
        int smallestGreaterThan = findSmallestElementGreaterThan(array, target);
        int largestLessThan = findLargestElementLessThan(array, target);
        // can not find the specific target
        if((smallestGreaterThan == 0) || array[smallestGreaterThan - 1] != target) {
            return 0;
        }
        return smallestGreaterThan - largestLessThan - 1;
    }

    public static void main(String [] args) {
        int [] array = {1, 5, 7, 9, 15};

        try {
            System.out.println("Value < " + 5 + " = " + findLargestValueLessThan(array, 5));
            System.out.println("Value < " + 16 + " = " + findLargestValueLessThan(array, 16));
            System.out.println("Value < " + 0 +  " = " + findLargestValueLessThan(array, 1));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }


        try {
            System.out.println("Value > " + 5 + " = " + findSmallestValueLargerThan(array, 5));
            System.out.println("Value > " + 12 + " = " + findSmallestValueLargerThan(array, 12));
            System.out.println("Value > " + 1 +  " = " + findSmallestValueLargerThan(array, 1));
            System.out.println("Value > " + 15 +  " = " + findSmallestValueLargerThan(array, 15));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        BinarySearchUtil binarySearchUtil = new BinarySearchUtil();
        System.out.println(binarySearchUtil.countOccurences(new int[]{5, 5, 9, 16, 17, 17}, 18));
    }
}