import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds intersection of sorted arrays with duplicates.
 */
public class IntersectionOfSortedArrays {
    /*
     * first = 1, 1, 2, 3
     * second = 2, 2, 3, 4
     * intersection = 2, 3
     */
    public List<Integer> findIntersectionOfSortedArrays(int [] first, int [] second) {
        if(first == null || second == null) {
            return new ArrayList<Integer>(0);
        }
        List<Integer> intersection = new ArrayList(Math.min(first.length, second.length));
        int firstIndex = 0, secondIndex = 0;
        while(firstIndex < first.length &&
            secondIndex < second.length) {
            if(first[firstIndex] == second[secondIndex] &&
                (firstIndex == 0 || first[firstIndex] != first[firstIndex - 1])) {
                intersection.add(first[firstIndex]);
                firstIndex++;secondIndex++;
            } else if(first[firstIndex] < second[secondIndex]) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }
        return intersection;
    }

    /*
     * Two sorted arrays a1, a2 of data lengths n1, n2
     * One array has enough space to hold the combined array
     * Merge the sorted arrays.
     */
    public void mergeSortedArraysInPlace(int [] bigger, int biggerDataLength, int [] smaller) {
        if(bigger == null || smaller == null) {
            throw new IllegalArgumentException("Array is null.");
        }
        if(bigger.length < biggerDataLength + smaller.length) {
            throw new IllegalArgumentException("Bigger array does not have enough space.");
        }
        int smallerLength = smaller.length;
        int i1 = biggerDataLength - 1, i2 = smallerLength - 1, result = biggerDataLength + smallerLength - 1;
        while(i1 >= 0 && i2 >= 0) {
            if(i1 >= 0 && i2 >= 0) {
                if(bigger[i1] > smaller[i2]) {
                    bigger[result--] = bigger[i1--];
                } else {
                    bigger[result--] = smaller[i2--];
                }
            } else if(i1 >= 0) {
                bigger[result--] = bigger[i1--];
            } else {
                bigger[result--] = smaller[i2--];
            }
        }
    }

    /*
     * 1, 2, 1, 3, 2
     * 1 => 2, 2 => 2, 3 => 1
     * 0, 2, 4, 5
     */
    public int [] countingSort(int [] array, int maxValue) {
        if(array == null || maxValue < 0) {
            throw new IllegalArgumentException("Params invalid.");
        }
        // Generate count array.
        int [] countArray = new int[maxValue + 1];
        for (int i = 0; i < array.length; i++) {
            if(array[i] < 0 ||
                array[i] > maxValue) {
                throw new IllegalArgumentException("Array element " + array[i] + " not in range");
            }
            countArray[array[i]] += 1;
        }

        // Generate cumulative sums.
        for(int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        int [] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            result[countArray[array[i]] - 1] = array[i];
            countArray[array[i]] -= 1;
        }
        return result;
    }

    private static class Event implements Comparable<Event> {
        public Event(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        public int startTime;
        public int endTime;
        public int compareTo(Event other) {
            if(other == null) {
                return 1;
            }
            int diff = startTime - other.startTime;
            if(diff != 0) {
                return diff;
            }
            return endTime - other.endTime;
        }
    }

    public static void main(String [] args) {
        int [] first = new int[] {2,3,3,5,5,6,7,7,8,12};
        int [] second = new int[] {5,5,6,8,8,9,10,10};
        IntersectionOfSortedArrays intersectionOfSortedArrays =
            new IntersectionOfSortedArrays();
        System.out.println(intersectionOfSortedArrays.findIntersectionOfSortedArrays(first, second));

        int [] bigger = new int [] {1, 5, 7, 9, 0, 0, 0};
        int [] smaller = new int [] {2, 8, 15};
        intersectionOfSortedArrays.mergeSortedArraysInPlace(bigger, 4, smaller);
        System.out.println(Arrays.toString(bigger));

        int [] forCountSort = new int [] {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(intersectionOfSortedArrays.countingSort(forCountSort, 5)));

        Event [] events = new Event[] {new Event(1, 5),
                                    new Event(2, 7),
                                    new Event(4, 5),
                                    new Event(6, 10),
                                    new Event(8, 9),
                                    new Event(9, 17),
                                    new Event(11, 13),
                                    new Event(12, 15),
                                    new Event(14, 15)};
    }
}
