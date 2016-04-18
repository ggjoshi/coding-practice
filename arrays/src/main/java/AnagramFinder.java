import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given a list of strings, find all anagrams.
 */
public class AnagramFinder {
    private String sortString(String s) {
        char [] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
    public List<List<String>> findAnagrams(List<String> strs) {
        if(strs == null || strs.size() <= 1) {
            return new ArrayList();
        }
        Map<String, List<String>> anagramMap = new HashMap<String, List<String>>();

        for(String str : strs) {
            String sorted = sortString(str);
            List<String> strList;
            if(!anagramMap.containsKey(sorted)) {
                strList = new ArrayList<String>();
                anagramMap.put(sorted, strList);
            }
            strList = anagramMap.get(sorted);
            strList.add(str);
        }
        List<List<String>> listOfAnagrams = new ArrayList<List<String>>();
        for(List<String> anagramList : anagramMap.values()) {
            if(anagramList.size() > 1) {
                listOfAnagrams.add(anagramList);
            }
        }
        return listOfAnagrams;
    }

    /*
     * "hello world" on ' '(space)
     * when you encounter space or end of string;
     * take substring before that till the last start of substring after space or 0th character in the string.
     * also advance the counter for beginning of last substring
     */
    public static String [] splitOnCharacter(String value, char ch) {
        if(value == null || value.length() == 0) {
            return new String [0];
        }
        int beginIndex = 0, current = 0;
        List<String> result = new ArrayList<String>();
        while(current <= value.length()) {
            if(current == value.length() ||
                value.charAt(current) == ch) {
                result.add(value.substring(beginIndex, current));
                beginIndex = current + 1;
            }
            current++;
        }
        return result.toArray(new String[result.size()]);
    }

    public static void main(String [] args) {
        List<String> strings = Arrays.asList("abc", "cba", "dbc", "db", "cdb");
        List<List<String>> anagrams = new AnagramFinder().findAnagrams(strings);
        for(List<String> anagramList : anagrams) {
            System.out.println(anagramList);
        }
        String test = "hello..i";
        String [] testSplit = test.split("\\.");
        System.out.println(Arrays.toString(testSplit));

        String withSpaces = "helloworld";
        String [] withSpacesSplit = withSpaces.split(" ");
        System.out.println(Arrays.toString(withSpacesSplit));

        String [] customSplit = splitOnCharacter(withSpaces, ' ');
        System.out.println(Arrays.toString(customSplit));
    }
}