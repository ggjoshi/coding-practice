/**
 *
 */
public class LookAndSaySequence {
    /*
     * this sequence starts with 1; and then goes like
     * 11, 21, 1211, 111221, 312211
     * basically count the number of consecutive digits and
     * write the count followed by the number to generate next number.
     * generate nth such number
     */
    public static String generateLookAndSaySequence(int n) {
        String previous = "1";
        for (int i = 2; i <= n; i++) {
            System.out.println(previous);
            previous = generateLookAndSaySequence(previous);
        }
        return previous;
    }

    public static String generateLookAndSaySequence(String previous) {
        StringBuilder stringBuilder = new StringBuilder(previous.length());
        int currentCharCount = 1;
        for(int i = 1; i < previous.length(); i++) {
            if(previous.charAt(i - 1) == previous.charAt(i)) {
                currentCharCount++;
            } else {
                stringBuilder.append(currentCharCount).append(previous.charAt(i - 1));
                currentCharCount = 1;
            }
        }
        stringBuilder.append(currentCharCount).append(previous.charAt(previous.length() - 1));
        return stringBuilder.toString();
    }

    public static void main(String args[]) {
        generateLookAndSaySequence(10);
    }
}
