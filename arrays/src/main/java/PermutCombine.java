/**
 * Permutation and combination generation.
 */
public class PermutCombine {
    public static void printCombinations(String input) {
        System.out.println("Combinations:-");
        StringBuilder output = new StringBuilder(input.length());
        printCombinationsHelper(output, input, 0);
    }

    /* abc
     * a
     * ab
     * abc
     * ac
     * b
     * bc
     * c
     */
    private static void printCombinationsHelper(StringBuilder output,
                                                String input,
                                                int index) {
        for (int i = index; i < input.length(); i++) {
            output.append(input.charAt(i));
            System.out.println(output);
            printCombinationsHelper(output, input, i + 1);
            output.deleteCharAt(output.length() - 1);
        }
    }

    public static void printPermutations(String input) {
        System.out.println("Permutations:-");
        StringBuilder output = new StringBuilder(input);
        printPermutationsHelper(output, 0);
    }

    /*
     * abc
     * acb
     */
    private static void printPermutationsHelper(StringBuilder output, int index) {
        if(index == output.length()) {
            System.out.println(output);
        }
        for(int i = index; i < output.length(); i++) {
            swap(output, i, index);
            printPermutationsHelper(output, index + 1);
            swap(output, i, index);
        }
    }

    private static void swap(StringBuilder output, int pos1, int pos2) {
        char temp = output.charAt(pos1);
        output.setCharAt(pos1, output.charAt(pos2));
        output.setCharAt(pos2, temp);
    }

    public static void main(String [] args) {
        printCombinations("abc");
        printPermutations("abc");
    }
}
