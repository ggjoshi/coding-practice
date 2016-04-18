import java.util.Random;

/*
Find kth largest element in n element array.
<3, 2, 1, 5, 4> => 1st largest is 5, 5th largest is 1.
1. sort descending and take the (k - 1)st element. O(nlogn)
2. partition the array using random pivot such that all elements greater than pivot are on left and all less are on right.
see pivot's index; if it is < k-1 then go right, 
if pivot index > k-1 go left and if equal to k-1 then 
that is the result
*/
public class KthLargestFinder {
    private void swap(int [] array, int low, int high) {
        int temp = array[high];
        array[high] = array[low];
        array[low] = temp;
    }
    /*
     * Moves elements > a randomly chosen pivot to its left and rest to its right
     * Returns the pivot index
     */
    private int randomPartition(int [] array, int low, int high) {
        Random random = new Random();
        int pivotIndex = low + random.nextInt(high - low + 1);
        swap(array, pivotIndex, high);
        int pivot = array[high];
        int greaterBoundary = low;
        for(int current = low; current < high; current++) {
            if(array[current] > pivot) {
                swap(array, current, greaterBoundary);
                greaterBoundary++;
            }
        }
        swap(array, greaterBoundary, high);
        return greaterBoundary;
    }

    public int findKthLargestHelper(int [] array, int low, int high, int k) {
        int pivotIndex = randomPartition(array, low, high);
        int pivotRank = pivotIndex - low;
        if(pivotRank == k) {
            return array[pivotIndex];
        } else if(pivotRank < k)  {
            return findKthLargestHelper(array, pivotIndex + 1, high, k - pivotRank - 1);
        } else {
            return findKthLargestHelper(array, low, pivotIndex - 1, k);
        }
    }
    /*
    * k is 1 based; 1st largest is the largest element
    */
    public int findKthLargest(int [] array, int k) {
        if(array == null || array.length < k || k < 1) {
            throw new IllegalArgumentException("Null array or k greater than array length or k less than 1");
        }
        return findKthLargestHelper(array, 0, array.length - 1, k - 1);
    }

    public static void main(String [] args) {
        int [] values = new int[] {1, 2, 3, 4, 5};
        int k = -1;
        KthLargestFinder kthLargestFinder = new KthLargestFinder();
        System.out.println("k = " + k + " largest element is " +
            kthLargestFinder.findKthLargest(values, k));
    }
}
