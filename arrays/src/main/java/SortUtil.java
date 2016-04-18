import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class SortUtil {


/*
* Write a method to sort an array of strings so that anagrams are next to each other.
* e.g. {"abc", "abd", "cab", "abe"}
* e.g. output {"abc","cab", "abd", "abe"}
* Comparator should be such that if s1 and s2 are anagrams, mark them as equal otherwise, just do normal string compare.
*/
    private static class AnagramComparator implements Comparator<String> {
        private String sortString(String s) {
            char [] charArray = s.toCharArray();
            Arrays.sort(charArray);
            return new String(charArray);
        }

        public int compare(String s1, String s2) {
            return sortString(s1).compareTo(sortString(s2));
        }
    }

    public void sortStringArrayForAnagrams(String [] strings) {
        System.out.println(Arrays.toString(strings));
        Arrays.sort(strings, new AnagramComparator());
        System.out.println(Arrays.toString(strings));
    }

    public static void main(String[] args) {
        String [] strings = {"abc", "abd", "abe", "xyz", "cab", "eba"};
        SortUtil sortUtil = new SortUtil();
        sortUtil.sortStringArrayForAnagrams(strings);

        Map<String, List<String>> hashMapList = new HashMap<String, List<String>>();
        List<String> strings1 = new ArrayList<String>();

    }
}
