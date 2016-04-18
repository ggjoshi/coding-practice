import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PermutationFinder {
    /*
    S = abc
    B = bacbdecabjkabc
    1. Find all permutations of S and look them up in B. O(S! * B)
    2. sort S, traverse B and for each s.length portion of b, sort it and see if it matches the sorted S O(SlogS + SlogS * B)
    3. Count the chars in S in hash map of key = char in S and value = count. Iterate through B with S characters at a time and see if that is present in the hash map with same counts.
    */
    public List<String> findPermutation(String big, String small) {
        List<String> result = new LinkedList<String>();
        if(small == null || big == null) {
            return result;
        }
        // Count the chars in S in a map
        Map<Character, Integer> charCountMap = new HashMap<Character, Integer>(small.length());
        for(int i = 0; i < small.length(); i++) {
            Integer currentCount = charCountMap.get(small.charAt(i));
            charCountMap.put(small.charAt(i), currentCount == null ? 1 : currentCount + 1);
        }
        // Slide a window of size of small string over B
        // Each time, see if current string can be a permutation, if yes; then add it to result; 
        // Any time you find a character not present in S or too many to be in S, then slide the starting pointer towards right 
        // so that you get back to a workable window
        // S = abc
        // B = bacbdecabjkabc
        int bCharStart = 0, bCharCurrent = 0, currentSize = 0;
        while(bCharCurrent < big.length()) {
            Integer currentCharCount = charCountMap.get(big.charAt(bCharCurrent));
            if((currentCharCount == null) || (currentCharCount == 0)) {
                if(bCharStart < bCharCurrent) {
                    charCountMap.put(big.charAt(bCharStart),
                        charCountMap.get(big.charAt(bCharStart)) + 1);
                    currentSize--;
                } else {
                    bCharCurrent++;
                }
                bCharStart++;
            } else {
                charCountMap.put(big.charAt(bCharCurrent), currentCharCount - 1);
                bCharCurrent++;
                currentSize++;
            }
            if(currentSize == small.length()) {
                result.add(big.substring(bCharStart, bCharCurrent));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PermutationFinder permutationFinder = new PermutationFinder();
        System.out.println(permutationFinder.findPermutation("bacbdecabjkabc", "abc"));
    }
}