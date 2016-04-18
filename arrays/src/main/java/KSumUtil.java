import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * K Sum finding examples.
 */
public class KSumUtil {
    public static boolean findPairWithSumK(List<Integer> numbers,
                                 int k,
                                 List<Integer> pair) {
        if(numbers == null || numbers.size() <= 1) {
            return false;
        }
        // Key = element, Value = index in the array
        Map<Integer, Integer> presenceMap = new HashMap<Integer, Integer>(numbers.size());
        for(int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if(presenceMap.containsKey(k - number)) {
                pair.add(number); pair.add(k - number);
                return true;
            }
            presenceMap.put(number, i);
        }
        return false;
    }

    public static boolean findPairWithSumKInSortedList(List<Integer> numbers,
                                                int low, int high,
                                                int k,
                                                List<Integer> pair) {
        while(low < high) {
            int sum = numbers.get(low) + numbers.get(high);
            if(sum == k) {
                pair.add(numbers.get(low));pair.add(numbers.get(high));
                return true;
            } else if(sum < k) {
                low++;
            } else {
                high--;
            }
        }
        return false;
    }

    public static boolean findTripletWithSumK(List<Integer> numbers, int k,
                                       List<Integer> triplet) {
        if(numbers == null ||
            numbers.size() <= 2) {
            return false;
        }
        Collections.sort(numbers);
        for(int i = 0; i < numbers.size(); i++) {
            swap(numbers, 0, i);
            if(findPairWithSumKInSortedList(numbers,
                1,
                numbers.size() - 1,
                k - numbers.get(0),
                triplet)) {
                triplet.add(numbers.get(0));
                return true;
            }
        }
        return false;
    }

    private static void swap(List<Integer> numbers, int pos1, int pos2) {
        Integer temp = numbers.get(pos1);
        numbers.set(pos1, numbers.get(pos2));
        numbers.set(pos2, temp);
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(9, 4, 1, 7, 5);
        List<Integer> result = new ArrayList<Integer>(3);
        int k = 21;
        if(findTripletWithSumK(numbers, k, result)) {
            System.out.println(k + " : " + result);
        }
        result.clear();
        k = 14;
        if(findPairWithSumK(numbers, k, result)) {
            System.out.println(k + " : " + result);
        }
    }
}
