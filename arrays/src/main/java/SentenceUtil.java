import java.util.Arrays;

/**
 * Util functions for a sentence.
 */
public class SentenceUtil {
    // Consider whitespace as the delimiter
    // reverse words in a sentence.
    // e.g. i am a boy
    // boy a am i
    public String reverseSentence(String input) {
        if(input == null || input.length() == 0) {
            return input;
        }
        StringBuilder output = new StringBuilder(input.length());
        int currentEnd = input.length() - 1;
        for(int i = input.length() - 1; i >= -1; i--) {
            if((i == -1) ||
                (input.charAt(i) == ' ')) {
                for(int j = i + 1; j <= currentEnd; j++) {
                    output.append(input.charAt(j));
                }
                currentEnd = i - 1;
                if(i != -1) {
                    output.append(' ');
                }
            }
        }
        return output.toString();
    }

    public static void main(String [] args) {
        SentenceUtil sentenceUtil = new SentenceUtil();
        System.out.println("***" + sentenceUtil.reverseSentence(" i am a  boy ") + "***");
        String text = "I am a boy..I am a boy.";
        String [] sentences = text.split("\\.");
        System.out.println(sentences.length);
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
}