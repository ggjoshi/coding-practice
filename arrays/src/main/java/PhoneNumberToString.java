import com.google.common.collect.ImmutableMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class PhoneNumberToString {
    private final static Map<Character, String> digitToCharsMap;

    static {
        digitToCharsMap = new HashMap<Character, String>();
        digitToCharsMap.put('2', "ABC");
        digitToCharsMap.put('3', "DEF");
        digitToCharsMap.put('4', "GHI");
        digitToCharsMap.put('5', "JKL");
        digitToCharsMap.put('6', "MNO");
        digitToCharsMap.put('7', "PQRS");
        digitToCharsMap.put('8', "TUV");
        digitToCharsMap.put('9', "WXYZ");
    }

    public static void printAllStringsForPhoneNumber(String phoneNumber) {
        printAllStringsForPhoneNumberHelper(phoneNumber, 0, new StringBuilder(phoneNumber.length()));
    }

    private static void printAllStringsForPhoneNumberHelper(String phoneNumber,
                                                            int index,
                                                            StringBuilder output) {
        if(index == phoneNumber.length()) {
            System.out.println(output.toString());
            return;
        }
        char currentDigit = phoneNumber.charAt(index);
        if(!digitToCharsMap.containsKey(currentDigit)) {
            throw new IllegalArgumentException("Phone number contains invalid digits");
        }
        String possibleChars = digitToCharsMap.get(currentDigit);
        for (int i = 0; i < possibleChars.length(); i++) {
            output.append(possibleChars.charAt(i));
            printAllStringsForPhoneNumberHelper(phoneNumber, index + 1, output);
            output.deleteCharAt(output.length() - 1);
        }
    }

    public static void main(String args[]) {
        printAllStringsForPhoneNumber("2276696");
    }
}