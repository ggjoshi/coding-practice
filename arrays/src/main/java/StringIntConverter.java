/**
 *
 */
public class StringIntConverter {
    public static interface NumberBase {
        boolean isCharValid(char currentChar);
        int getBase();
        int getIntValue(char currentChar);
    }
    public static class Base10 implements NumberBase {
        public boolean isCharValid(char currentChar) {
            return currentChar >= '0' &&
                currentChar <= '9';
        }

        public int getBase() {
            return 10;
        }

        public int getIntValue(char currentChar) {
            return currentChar - '0';
        }
    }
    public static int stringToInt(String input, NumberBase numberBase) {
        if(input == null || input.length() == 0) {
            throw new IllegalArgumentException("String empty");
        }

        int currentChar = 0;
        boolean isNegative = false;
        if(input.charAt(0) == '-') {
            isNegative = true;
            currentChar++;
        }
        if(currentChar == input.length()) {
            throw new IllegalArgumentException("String just contains a sign no number.");
        }

        long result = 0;
        while(currentChar < input.length()) {
            if(!numberBase.isCharValid(input.charAt(currentChar))) {
                throw new IllegalArgumentException("Contains a non-numeric value at position " + currentChar);
            }
            result = result * numberBase.getBase() + numberBase.getIntValue(input.charAt(currentChar));
            if((!isNegative && (result > Integer.MAX_VALUE)) ||
                (isNegative && ((-result) < Integer.MIN_VALUE))) {
                throw new IllegalArgumentException("Out of range for an integer");
            }
            currentChar++;
        }
        if(isNegative) {
            result = -result;
        }
        return (int)result;
    }

    /*
     123 => "123"
     -456 => "-456"
     0 => "0"
     */
    public static String intToString(int input) {
        StringBuilder outputBuilder = new StringBuilder();
        boolean isNegative = (input < 0);
        long absValue = (isNegative ? (-1 * (long)input) : input);
        do {
            outputBuilder.append((char)(((long)'0') + (absValue % 10)));
            absValue /= 10l;
        } while(absValue != 0);

        if(isNegative) {
            outputBuilder.append('-');
        }
        outputBuilder.reverse();
        return outputBuilder.toString();
    }

    public static void main(String [] args) {
        NumberBase numberBase = new Base10();
        System.out.println(stringToInt("-2147483646", numberBase));
        System.out.println(stringToInt("2147483647", numberBase));
        System.out.println("CA".equals(null));
        System.out.println(intToString(123));
        System.out.println(intToString(-456));
        System.out.println(intToString(0));
        System.out.println(intToString(Integer.MAX_VALUE));
        System.out.println(intToString(Integer.MIN_VALUE));
    }
}
