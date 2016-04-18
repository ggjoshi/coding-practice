import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Code for manipulating contents of an array.
 */
public class ArrayManipulation {
    /*
     * Remove all occurences of an element from the array.
     * e.g. A = [5, 3, 7, 11, 3] element = 3
     * output = [5, 7, 11, ., .] return value = 3
     */
    public int removeElement(int [] array, int element) {
        int read = 0, write = 0;
        while(read < array.length) {
            if(array[read] != element) {
                array[write++] = array[read];
            }
            read++;
        }
        return write;
    }

    /**
     * This approach minimizes the movement of elements.
     * @return
     */
    public int efficientRemoveElement(int [] array, int element) {
        int current = 0, matchingBoundary = array.length - 1;
        while (current <= matchingBoundary) {
            if(array[current] == element) {
                swap(array, matchingBoundary, current);
                matchingBoundary--;
            }
            current++;
        }
        return matchingBoundary + 1;
    }

    /*
     * Remove duplicates from an array(first occurrence should be kept as is);
     * elements are not ordered in specific manner.
     * Return value is the length of remaining array.
     * array = [5, 7, 9, 5, 3]
     * output = [5, 7, 9, 3, 3]
     * return value = 4
     */
    public int removeDuplicates(int [] array) {
        Set<Integer> presentSet = new HashSet<Integer>(array.length);
        int write = 0;
        for(int read = 0; read < array.length; read++) {
            if(!presentSet.contains(array[read])) {
                array[write++] = array[read];
                presentSet.add(array[read]);
            }
        }
        return write;
    }

    /*
     * Update the given sorted array so that an element appears m times,
     * it will appear exactly min(2, m) times for a given m.
     * (Essentially reduce the occurrences to <=2)
     * Return the length of given array.
     * [5, 5, 5, 3, 9, 6, 7]
     *
     * {3, 5, 5, 7, 9}
     */
    public int updateArrayForFrequency(int [] array, int m) {
        int write = 1, currentElementCount = 1;
        for(int read = 1; read < array.length; read++) {
            if(array[read] == array[write - 1]) {
                currentElementCount++;
            } else {
                currentElementCount = 1;
            }
            if(currentElementCount <= 2) {
                array[write++] = array[read];
            }
        }
        return write;
    }

    /*
     * Arrange the array elements around given element at index partitionIndex
     * {5, 9, 2, 4, 11, 6, 3, 10, 2} partitionIndex = 3 pivot = 2
     * < lessBoundary => < pivot
     * > greaterBoundary => > pivot
     * current
     * between lessBoundary and current is = pivot
     * between current and greaterBoundary is unexplored
     */
    /*
    {5, 9, 2, 4, 11, 6, 3, 10, 2} pivot = 2
     */
    private static void dutchFlagAlgo(int [] array, int partitionIndex) {
        if(array.length - 1 < partitionIndex) {
            throw new IllegalArgumentException("The partition index does not fit in the array");
        }
        int pivot = array[partitionIndex];
        System.out.println(pivot);
        int lessBoundary = 0, greaterBoundary = array.length - 1, current = 0;
        while(current <= greaterBoundary) {
            int currentElement = array[current];
            if(currentElement < pivot) {
                swap(array, lessBoundary, current);
                lessBoundary++;
                current++;
            } else if(currentElement == pivot) {
                current++;
            } else {
                swap(array, greaterBoundary, current);
                greaterBoundary--;
            }
        }
    }

    private static void swap(int [] array, int low, int high) {
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }

    public static int [] getRandomSample(int [] array, int k) {
        if(array == null || array.length == 0 || k > array.length) {
            return null;
        }
        int elementsToSample = Math.min(k, array.length - k);
        int [] sampleArray = new int[k];
        Random random = new Random();
        for(int i = 0; i < elementsToSample; i++) {
            int sampleLocation = random.nextInt(array.length - i);
            swap(array, sampleLocation, array.length - i - 1);
        }
        int startCopy = elementsToSample == k ? array.length - k : 0;
        int endCopy = elementsToSample == k ? array.length - 1 : k - 1;

        for (int i = startCopy; i <= endCopy; i++) {
            sampleArray[i - startCopy] = array[i];
        }
        return sampleArray;
    }

    public static void main(String [] args) {
        int [] array = new int[] {5, 9, 2, 4, 11, 6, 3, 10};

        System.out.println(Arrays.toString(array));
        System.out.println("Random sample of 2 - " + Arrays.toString(getRandomSample(array, 2)));
        System.out.println("Random sample of 5 - " + Arrays.toString(getRandomSample(array, 5)));

        System.out.println(Arrays.toString(array));
        dutchFlagAlgo(array, 2);
        System.out.println(Arrays.toString(array));

        ArrayManipulation arrayManipulation = new ArrayManipulation();
        array = new int[] {1, 3, 6, 5, 6, 9};
        System.out.println(Arrays.toString(array));
        System.out.println(arrayManipulation.efficientRemoveElement(array, 6));
        System.out.println(Arrays.toString(array));
    }
}
