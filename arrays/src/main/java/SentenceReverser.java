/**
 * Reverses word in a sentence
 */
public class SentenceReverser {
    public static String reverseWordsInSentenceAlternate(String input) {
        if(input == null || input.length() == 0) {
            return input;
        }

        StringBuilder outputBuilder = new StringBuilder(input.length());
        int wordEnd = input.length() - 1, wordStart;
        for(int i = input.length() - 1; i >= -1; i--) {
            if(i == -1 || !Character.isLetterOrDigit(input.charAt(i))) {
                wordStart = i + 1;
                for(int j = wordStart; j <= wordEnd; j++) {
                    outputBuilder.append(input.charAt(j));
                }
                if(i >= 0) {
                    outputBuilder.append(input.charAt(i));
                    wordEnd = i - 1;
                }
            }
        }
        return outputBuilder.toString();
    }

    /*
     * Words separated by whitespace.
     * e.g. input = Alice likes Bob
     * output = Bob likes Alice
     */
    public static String reverseWordsInSentence(String input) {
        if(input == null || input.length() == 0) {
            return input;
        }

        StringBuilder outputBuilder = new StringBuilder(input.length());
        int wordEnd = input.length() - 1;
        int wordStart;
        boolean wordInProgress = false;
        //Alice likes Bob
        for(int i = input.length() - 1; i >= -1; i--) {
            if((i == -1) || !Character.isLetterOrDigit(input.charAt(i))) {
                if(wordInProgress) {
                    wordStart = i + 1;
                    for (int j = wordStart; j <= wordEnd; j++) {
                        outputBuilder.append(input.charAt(j));
                    }
                    wordInProgress = false;
                }
                if(i >= 0) {
                    outputBuilder.append(input.charAt(i));
                }

            } else {
                if(!wordInProgress) {
                    wordEnd = i;
                    wordInProgress = true;
                }
            }
        }
        return outputBuilder.toString();
    }

    public static void main(String [] args) {
        String input = "===";
        System.out.println(input + " Reverse := " +
            SentenceReverser.reverseWordsInSentenceAlternate(input));
    }
}
