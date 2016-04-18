/**
 * Bit operation utilities.
 */
public class BitOperations {
    private static byte [] parityArray = new byte[] {
        0, 1, 1, 0,
        1, 0, 0, 1,
        1, 0, 0, 1,
        0, 1, 1, 0
    };

    public static int getParity(long input) {
        long parity = input;
        parity ^= parity >> 32;
        parity ^= parity >> 16;
        parity ^= parity >> 8;
        parity ^= parity >> 4;
        parity ^= parity >> 2;
        parity ^= parity >> 1;
        return (int)(parity & 1);
    }

    public static int fitSmallerNumberInBiggerNumberBitwise(int small, int big, int i, int j) {
        if(i > j) {
            throw new IllegalArgumentException("i should be <= j");
        }
        System.out.println("small = " + small + ",big = " + big + ",i = " + i + ", j = " + j);
        small = small << i;
        // all 1s from 0 to i
        // all 1s from msb to j
        int mask = ((1 << i) - 1) | (~((1 << j) - 1));
        big = big & mask;
        small = small & (~mask);
        return big | small;
    }

    // function for gcd(x, y)
    // 6, 10 => 2
    //gcd(x, y) = gcd(abs(x - y), x);
    // gcd(6, 10) = gcd(4, 6) = gcd(2, 4) = gcd(2, 2) = gcd(0, 2)

    // lcm = x * y / gcd
    // lcm(6, 10) = (60/2) = 30


    public static void main(String [] args) {
        System.out.println(getParity(15l));
        System.out.println(getParity(3l));
        System.out.println(powerRecursive(2, 6));
        System.out.println(powerRecursive(2, -2));
        System.out.println(powerRecursive(-2, 4));
        System.out.println(powerRecursive(-3, 3));

        System.out.println(powerIterative(2, 6));
        System.out.println(powerIterative(2, -2));
        System.out.println(powerIterative(-2, 4));
        System.out.println(powerIterative(-3, 3));


        System.out.println("Shift 32 bit int by 31 bits and see the number = " + Integer.toHexString(0x60000000 >> 30));

        System.out.println(fitSmallerNumberInBiggerNumberBitwise(2, 15, 1, 2));
    }

    /*
     * 2 ^ 5 = 2 ^ 2 * 2 ^ 2 * 2
     */
    public static double powerRecursive(int x, int y) {
        if(y == 0) {
            return 1;
        } else if(y == 1) {
            return x;
        } else if(y == -1) {
            return 1.0/x;
        }

        double halfPower = powerRecursive(x, y/2);
        return halfPower * halfPower * powerRecursive(x, y%2);
    }

    public static double powerIterative(int x, int y) {
        boolean isNegativeExponent = (y < 0);
        int reminderExponent = 0;
        int unsignedExponent = y;
        if(isNegativeExponent) {
            if(y == Integer.MIN_VALUE) {
                unsignedExponent = -(y + 1);
                reminderExponent = 1;
            } else {
                unsignedExponent = -y;
            }
        }
        double result = 1;
        double currentPower = x;
        while(unsignedExponent != 0) {
            if((unsignedExponent & 1) != 0) {
                result *= currentPower;
            }
            currentPower *= currentPower;
            unsignedExponent >>>= 1;
        }

        if(reminderExponent != 0) {
            result = result * x;
        }
        if(isNegativeExponent) {
            result = 1.0/result;
        }

        return result;
    }
}
