import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Decomposes a string into palindromes.
 */
public class PalindromicDecomposer {
    /*
     * e.g. for "0204451881", the decomposition is a set of strings {"020", "44", "5", "1881"}
     * Print such decompositions for a string
     */
    public void printPalindromicDecompositions(String str) {
        List<String> decompositionSoFar = new ArrayList<String>(str.length());
        printPalindromicDecompositionsHelper(str, 0, decompositionSoFar);
    }

    private boolean isPalindrome(String str, int start, int end) {
        while(start < end) {
            if(str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++; end--;
        }

        return true;
    }

    private void printPalindromicDecompositionsHelper(String str,
                                                      int fromOffset,
                                                      List<String> decompositionSoFar) {
        if(fromOffset == str.length()) {
            for(String decomposition : decompositionSoFar) {
                System.out.print(decomposition + " ");
            }
            System.out.println();
            return;
        }
        for(int toOffset = fromOffset; toOffset < str.length(); toOffset++) {
            String subStr = str.substring(fromOffset, toOffset + 1);
            if(!isPalindrome(subStr, 0, subStr.length() - 1)) {
                continue;
            }
            decompositionSoFar.add(subStr);
            printPalindromicDecompositionsHelper(str, toOffset + 1, decompositionSoFar);
            decompositionSoFar.remove(decompositionSoFar.size() - 1);
        }
    }

    public static void main(String [] args) {
        PalindromicDecomposer palindromicDecomposer =
            new PalindromicDecomposer();
        palindromicDecomposer.printPalindromicDecompositions("1234");
        palindromicDecomposer.printPalindromicDecompositions("0204451881");
    }
}
