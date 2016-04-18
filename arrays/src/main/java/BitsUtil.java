/*
 * Bunch of utility functions doing bit manipulation.
 */
public class BitsUtil {
    public static int swapBits(int givenInt, byte firstBit, byte secondBit) {
        if(((givenInt & (1 << firstBit)) != 0) != ((givenInt & (1 << secondBit)) != 0)) {
            int mask = (1 << firstBit) | (1 << secondBit);
            givenInt ^= mask;
        }
        return givenInt;
    }

    // e.g. 1 => 1000..00
    public static int reverseBits(int givenInt) {
        final int NO_OF_BITS_IN_INT = 32;
        int start = 0, end = NO_OF_BITS_IN_INT - 1;
        while(start < end) {
            givenInt = swapBits(givenInt, (byte)start, (byte)end);
            start++;
            end--;
        }
        return givenInt;
    }

    /*
     * Write a function to convert a decimal number between 0 and 1 to binary representation.
     * 0.25
     * Till the number is != 0
     * Multiply by 2; take the integer part and append to the result.
     * after the given number of iterations if the result does not become 0; error out
     */
    public static String doubleToBinaryString(double value) {
        System.out.println("Value converted to string = " + value);
        if(value == 0 || value == 1) {
            return (value == 0) ? "0" : "1";
        }
        StringBuilder stringBuilder = new StringBuilder("0.");
        int maxIterations = 32, currentIteration = 0;
        while(value != 0) {
            if(currentIteration == maxIterations) {
                break;
            }
            value = value * 2;
            int intPart = (int)(value);
            value = value - intPart;
            stringBuilder.append(intPart);
            currentIteration++;
        }
        return stringBuilder.toString();
    }

    /*
     * Find which bit should be flipped to get a number having longest sequence of consecutive 1s.
     * Approach:-
     * Find out the pair of consecutive 1 sequences and the gap between them.
     */
    public int findBitIndexToFlipToGetLongestConsecutiveOne(int value) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(5));
        System.out.println(doubleToBinaryString(0.25));
        System.out.println(doubleToBinaryString(0.72));
        System.out.println(doubleToBinaryString(0.33));
    }
}
