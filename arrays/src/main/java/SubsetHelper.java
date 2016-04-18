import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Class for Generating subsets of various types.
 */
public class SubsetHelper {
    /*
     * Generate all subsets of size k from n elements
     */
    public void printAllSubsetsOfSizeK(int k, List<Integer> list) {
        printAllSubsetsOfSizeKHelper(0, k, list, new HashSet<Integer>());
    }

    /*
     * List
     * {1, 3, 5}
     * Subsets
     * {1, 3}
     * {1, 5}
     * {3, 5}
     *
     */
    private void printAllSubsetsOfSizeKHelper(int currentStart,
                                              int k,
                                              List<Integer> list,
                                              Set<Integer> subset) {
        int remainingFromK = k - subset.size();
        if(remainingFromK == 0) {
            System.out.println(subset);
            return;
        }

        // not enough elements left in the array
        if(remainingFromK > list.size() - currentStart) {
            return;
        }

        for(int i = currentStart;
            i < list.size();
            i++) {
            subset.add(list.get(i));
            printAllSubsetsOfSizeKHelper(i + 1, k, list, subset);
            subset.remove(list.get(i));
        }
    }

    // 1, 2, 1, 5, 1, 2, 5, 4
    public List<Integer> maxPalindromSubset(int [] array) {
        System.out.println("Array for palindrome subset = " + Arrays.toString(array));
        List<Integer> palindromSubset = new ArrayList<Integer>(array.length);
        if(array == null || array.length == 0) {
            return palindromSubset;
        }
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for(int element : array) {
            Integer currentValue = countMap.get(element);
            countMap.put(element, currentValue == null ? 1 : currentValue + 1);
        }

        boolean addedOneOddEntry = false;
        for(Map.Entry<Integer, Integer> mapEntry : countMap.entrySet()) {
            boolean isOddEntry = mapEntry.getValue() % 2 != 0;
            if(!isOddEntry || !addedOneOddEntry) {
                if(isOddEntry) {
                    addedOneOddEntry = true;
                }
                for(int i = 0; i < mapEntry.getValue(); i++) {
                    palindromSubset.add(mapEntry.getKey());
                }
            }
        }
        return palindromSubset;
    }

    public static void main(String [] args) {
        SubsetHelper subsetHelper = new SubsetHelper();
        subsetHelper.printAllSubsetsOfSizeK(2, Arrays.asList(1, 2, 3, 4, 5));

        System.out.println("Max palindrome subset = " + subsetHelper.maxPalindromSubset(new int []{1, 2, 1, 1, 2, 4}));
    }
}
