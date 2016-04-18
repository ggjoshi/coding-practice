import java.util.Arrays;
import java.util.Random;

public class Sorter {
    public void bubbleSort(int [] a) {
        System.out.println("Bubblesort " + Arrays.toString(a));
        if(a == null || a.length <= 1) {
            return;
        }

        for(int i = 0; i < a.length - 1; i++) {
            for(int j = 0; j < a.length - i - 1; j++) {
                if(a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("Bubblesort " + Arrays.toString(a));
    }

    // Insertion sort: select the next smallest element and put it in the front.
    // 5, 4, 3, 2, 1
    //
    //
    public void selectionSort(int [] array) {
        System.out.println("SelectionSort " + Arrays.toString(array));
        if(array == null || array.length <= 1) {
            return;
        }
        for(int i = 0; i < array.length - 1; i++) {
            // Find min element in remaining array
            int minValue = Integer.MAX_VALUE, minIndex = i;
            for(int j = i; j < array.length; j++) {
                if(array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            // Swap it with front element of remaining array
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }

        System.out.println("SelectionSort " + Arrays.toString(array));
    }

    // Merge sort
    // 5 4 3 2 1
    // 5 4  3 2 1
    // 5  4  3  2 1
    // 4 5 3 2 1
    // 4 5 3 1 2
    // 4 5 1 2 3
    // 1 2 3 4 5
    public void mergeSort(int [] array) {
        System.out.println("MergeSort " + Arrays.toString(array));
        if(array == null || array.length <= 1) {
            return;
        }
        int [] tempArray = new int[array.length];
        mergeSortHelper(array, 0, array.length - 1, tempArray);
        System.out.println("MergeSort " + Arrays.toString(array));
    }

    private void mergeSortHelper(int [] array, int low, int high, int [] tempArray) {
        if(low >= high) {
            return;
        }

        int mid = low + (high - low)/2;
        mergeSortHelper(array, low, mid, tempArray);
        mergeSortHelper(array, mid + 1, high, tempArray);
        merge(array, low, mid, high, tempArray);
    }

    // low = 2, mid = 4, high = 6
    // first array from 2 to 3
    // second array from 4 to 6
    //
    private void merge(int [] array, int low, int mid, int high, int [] tempArray) {
        int first = low, second = mid + 1, tempIndex = low;
        while(first <= mid || second <= high) {
            if((first <= mid) && (second <= high)) {
                if(array[first] < array[second]) {
                    tempArray[tempIndex] = array[first++];
                } else {
                    tempArray[tempIndex] = array[second++];
                }
            } else if(first <= mid) {
                tempArray[tempIndex] = array[first++];
            } else {
                tempArray[tempIndex] = array[second++];
            }
            tempIndex++;
        }

        tempIndex = low;
        while(tempIndex <= high) {
            array[tempIndex] = tempArray[tempIndex];
            tempIndex++;
        }
    }

    // Quick sort : Pickup a pivot element, partition the array around the pivot.
    // Then call QuickSort on both right and left part.
    // Random pivot is picked and it is expected that each time the pivot will be median
    // If that happens then the total complexity will be O(nlogn) since the array will become one element in logn iterations and
    // each iteration will be moving n elements. In worst case it will be O(n^2)
    public void quickSort(int [] array) {
        System.out.println("QuickSort " + Arrays.toString(array));
        if(array == null || array.length <= 1) {
            return;
        }

        quickSortHelper(array, 0, array.length - 1);
        System.out.println("QuickSort " + Arrays.toString(array));
    }

    public void swap(int [] array, int pos1, int pos2) {
        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    // 5 4 3 2 1
    public int randomizedPartition(int [] array, int low, int high) {
        Random random = new Random();
        int randomPivotIndex = low + random.nextInt(high - low + 1);
        swap(array, randomPivotIndex, high);
        int pivot = array[high];
        int greaterBoundary = low;
        for(int readFrom = low; readFrom < high; readFrom++) {
            if(array[readFrom] < pivot) {
                swap(array, greaterBoundary, readFrom);
                greaterBoundary++;
            }
        }
        swap(array, greaterBoundary, high);
        return greaterBoundary;
    }

    // 5 4 3 2 1
    // 1 4 5 2 3
    // 1 2 4 5 3
    // 1 2 3 5 4
    public void quickSortHelper(int [] array, int low, int high) {
        if(low >= high) {
            return;
        }

        int pivotIndex = randomizedPartition(array, low, high);

        quickSortHelper(array, low, pivotIndex - 1);
        quickSortHelper(array, pivotIndex + 1, high);
    }

    // Radix sort:
    // For integers; do sorting in multiple passes starting from the least significant digit to most significant digits.
    // What we will need is an implementation of countSort which is a stable O(n + k) time O(k + n) space sort.

    private static class RadixCountSortable {
        private int value;
        private int divideBy;
        private int modBy;

        public RadixCountSortable(int value, int divideBy, int modBy) {
            this.value = value;
            this.divideBy = divideBy;
            this.modBy = modBy;
        }

        public int getValue() {
            return (value / divideBy) % modBy;
        }

        public int getMaxValue() {
            return modBy - 1;
        }

        public int getOriginalValue() {
            return value;
        }

        public void setDivideBy(int divideBy) {
            this.divideBy = divideBy;
        }
    }

    public void countSort(RadixCountSortable [] array) {
        if(array == null || array.length <= 1) {
            return;
        }

        // count individual values.
        int [] countArray = new int [array[0].getMaxValue() + 1];
        for(int i = 0; i < array.length; i++) {
            countArray[array[i].getValue()] += 1;
        }

        // Accummulate counts.
        for(int j = 1; j < countArray.length; j++) {
            countArray[j] += countArray[j - 1];
        }

        RadixCountSortable[] resultArray = new RadixCountSortable[array.length];
        for(int i = array.length - 1; i >= 0; i--) {
            resultArray[countArray[array[i].getValue()] - 1] = array[i];
            countArray[array[i].getValue()] -= 1;
        }

        for(int i = 0; i < array.length; i++) {
            array[i] = resultArray[i];
        }
    }

    public void radixSort(int [] array) {
        System.out.println("RadixSort " + Arrays.toString(array));
        if(array == null || array.length <= 1) {
            return;
        }

        int divideBy = 1, modBy = 10;
        RadixCountSortable [] radixArray = new RadixCountSortable[array.length];
        for(int i = 0; i < array.length; i++) {
            radixArray[i] = new RadixCountSortable(array[i], divideBy, modBy);
        }

        while(true) {
            countSort(radixArray);
            divideBy = divideBy * 10;
            boolean allZeroes = true;
            for(int i = 0; i < radixArray.length; i++) {
                radixArray[i].setDivideBy(divideBy);
                if(allZeroes && (radixArray[i].getValue() != 0)) {
                    allZeroes = false;
                }
            }
            if(allZeroes) {
                break;
            }
        }

        for(int i = 0; i < array.length; i++) {
            array[i] = radixArray[i].getOriginalValue();
        }
        System.out.println("RadixSort " + Arrays.toString(array));
    }

    // For each element; find the right place and put it there.
    public void insertionSort(int [] array) {
        System.out.println("InsertionSort " + Arrays.toString(array));
        if(array == null || array.length <= 1) {
            return;
        }
        // For each element, put it in right place.
        for(int i = 1; i < array.length; i++) {
            int j = i - 1; int current = array[i];
            // 4, 5, 3, 2, 1
            while(j >= 0 && (current < array[j])) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        System.out.println("InsertionSort " + Arrays.toString(array));
    }

    /*
    * Given sorted arrays A and B; A has enough space to hold B; merge B into A.
    * A = 1 5 8 19 . . .
    * B = 2 3 16
    * result A = 1 2 3 5 8 9 16
    */
    void mergeSortedArrays(int [] A, int aLength, int [] B) {
        System.out.println("A = " + Arrays.toString(A));
        System.out.println("B = " + Arrays.toString(B));
        if(A == null || B == null ||
            A.length < aLength + B.length) {
            throw new IllegalArgumentException("Arrays are not valid.");
        }

        int outIndex = aLength + B.length - 1;
        int aIndex = aLength - 1, bIndex = B.length - 1;
        while(outIndex >= 0) {
            if((aIndex >= 0) && (bIndex >= 0)) {
                if(A[aIndex] > B[bIndex]) {
                    A[outIndex--] = A[aIndex--];
                } else {
                    A[outIndex--] = B[bIndex--];
                }
            } else if(bIndex >= 0) {
                A[outIndex--] = B[bIndex--];
            } else {
                break;
            }
        }
        System.out.println("A = " + Arrays.toString(A));
    }

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        int [] array = {5, 1, 4, 3, 2, 1};
        sorter.insertionSort(array);
        array = new int[]{1, 2, 3, 4};
        sorter.insertionSort(array);
        array = new int[]{1, 5, 3, 2};
        sorter.insertionSort(array);
        array = new int[]{7, 6, 5, 4, 3, 2, 1};
        sorter.insertionSort(array);
        array = new int[]{71, 16, 25, 94, 113, 12342, 1};
        sorter.insertionSort(array);

        sorter.radixSort(array);
        sorter.quickSort(array);
        sorter.mergeSort(array);
        sorter.selectionSort(array);
        sorter.bubbleSort(array);

        int [] A = {1, 5, 9, 17, 0, 0, 0}, B = {12, 19, 21};
        sorter.mergeSortedArrays(A, A.length - B.length, B);
        A = new int[]{4, 5, 9, 17, 0, 0, 0}; B = new int[]{1, 2, 3};
        sorter.mergeSortedArrays(A, A.length - B.length, B);
        A = new int[]{4, 5, 9, 10, 0, 0, 0}; B = new int[]{12, 14, 19};
        sorter.mergeSortedArrays(A, A.length - B.length, B);

    }
}
