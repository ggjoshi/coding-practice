import java.util.HashMap;
import java.util.Map;

/**
 * Palindrome checking functions.
 */
public class PalindromeChecker {
    public static boolean isPalindromeSkipNonAlphaNumeric(String input) {
        if(input == null) {
            return true;
        }
        int low = 0, high = input.length() - 1;
        while(low < high) {
            while (low < high) {
                if (!Character.isLetterOrDigit(input.charAt(low))) {
                    low++;
                } else if (!Character.isLetterOrDigit(input.charAt(high))) {
                    high--;
                } else {
                    break;
                }
            }

            if (Character.toUpperCase(input.charAt(low++)) != Character.toUpperCase(input.charAt(high--))) {
                return false;
            }
        }
        return true;
    }

    public static boolean canFormPalindrome(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }
        Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        int numberOfOddCounts = 0;
        for(int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if(!charCountMap.containsKey(current)) {
                charCountMap.put(current, 0);
            }
            int newCount = charCountMap.get(current) + 1;
            charCountMap.put(current, newCount);
            if(newCount % 2 == 0) {
                numberOfOddCounts--;
            } else {
                numberOfOddCounts++;
            }
        }

        return (numberOfOddCounts <= 1);
    }

    public static void main(String args[]) {
        String input = "A man, a plan, a canal, Panama.";
        System.out.println("Input - " + input + " - IsPalindrome - " +
            PalindromeChecker.isPalindromeSkipNonAlphaNumeric(input));
        String word = "abcdabc";
        System.out.println(PalindromeChecker.canFormPalindrome(word));
    }
}
