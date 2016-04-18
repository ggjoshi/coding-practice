import java.util.Arrays;

/**
 * matching paranthesis string finder
 */
public class ParenMatchingStringFinder {
    /*
     * Prints all paren matching strings in which there are n pairs of paren.
     * rules:-
     * 1. empty string is paren matching.
     * 2. Enclosing open and close paren to an existing paren matching string is a paren matching string
     * 3. Concatenating paren matching strings generates paren matching string.
     */
    public void printParenMatchingStrings(int n) {
        StringBuilder output = new StringBuilder();
        printParenMatchingStringsHelper(n, n, output);
    }

    public void printParenMatchingStringsHelper(int startingParenCount,
                                                int closingParenCount,
                                                StringBuilder output) {
        if(startingParenCount == 0 &&
            closingParenCount == 0) {
            System.out.println(output.toString());
            return;
        }

        if(startingParenCount > 0) {
            output.append("(");
            printParenMatchingStringsHelper(startingParenCount - 1, closingParenCount, output);
            output.deleteCharAt(output.length() - 1);
        }

        if(closingParenCount > startingParenCount) {
            output.append(")");
            printParenMatchingStringsHelper(startingParenCount, closingParenCount - 1, output);
            output.deleteCharAt(output.length() - 1);
        }
    }


    public static void main(String [] args) {
        ParenMatchingStringFinder parenMatchingStringFinder = new ParenMatchingStringFinder();
        parenMatchingStringFinder.printParenMatchingStrings(3);
    }
}
