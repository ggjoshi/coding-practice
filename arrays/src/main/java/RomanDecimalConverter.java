import java.util.HashMap;
import java.util.Map;

/**
 * Has functions to convert between roman and decimal number system.
 */
public class RomanDecimalConverter {
    private static class RomanDecimalMapping {
        public RomanDecimalMapping(int decimal, String roman) {
            this.decimal = decimal;
            this.roman = roman;
        }
        public int decimal;
        public String roman;
    }

    private static RomanDecimalMapping [] romanDecimalMappings =
        new RomanDecimalMapping[] {
            new RomanDecimalMapping(1, "I"),
            new RomanDecimalMapping(4, "IV"),
            new RomanDecimalMapping(5, "V"),
            new RomanDecimalMapping(9, "IX"),
            new RomanDecimalMapping(10, "X"),
            new RomanDecimalMapping(40, "XL"),
            new RomanDecimalMapping(50, "L"),
            new RomanDecimalMapping(100, "C"),
            new RomanDecimalMapping(400, "CD"),
            new RomanDecimalMapping(500, "D"),
            new RomanDecimalMapping(900, "CM"),
            new RomanDecimalMapping(1000, "M"),
        };

    public static String decimalToRoman(int value) {
        if(value == 0) {
            return "";
        }
        if(value > 3999) {
            throw new IllegalArgumentException("Argument bigger than 3999 not supported for conversion.");
        }
        int absValue = (value < 0) ? (value) * (-1) : value;
        StringBuilder result = new StringBuilder();
        if(value < 0) {
            result.append("-");
        }
        int lastMappingIndex = romanDecimalMappings.length - 1;
        while (absValue != 0) {
            if(lastMappingIndex < 0) {
                throw new IllegalArgumentException("Could not convert the number");
            }
            while(lastMappingIndex >= 0) {
                int currentDecimal = romanDecimalMappings[lastMappingIndex].decimal;
                if (absValue >= currentDecimal) {
                    long multiplier = absValue / currentDecimal;
                    for (int j = 0; j < multiplier; j++) {
                        result.append(romanDecimalMappings[lastMappingIndex].roman);
                    }
                    absValue = absValue % currentDecimal;
                }
                lastMappingIndex--;
            }
        }
        return result.toString();
    }

    public static void main(String [] args) {
        int number = 2014;
        System.out.println("Decimal to roman for " + number
            + " is = " + decimalToRoman(number));
    }
}
