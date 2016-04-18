package gcj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 A jamcoin is a string of N ≥ 2 digits with the following properties:

 Every digit is either 0 or 1.
 The first digit is 1 and the last digit is 1.
 If you interpret the string in any base between 2 and 10, inclusive, the resulting number is not prime.

 Not every string of 0s and 1s is a jamcoin. For example, 101 is not a jamcoin; its interpretation in base 2 is 5, which is prime. But the string 1001 is a jamcoin: in bases 2 through 10, its interpretation is 9, 28, 65, 126, 217, 344, 513, 730, and 1001, respectively, and none of those is prime.

 We hear that there may be communities that use jamcoins as a form of currency. When sending someone a jamcoin, it is polite to prove that the jamcoin is legitimate by including a nontrivial divisor of that jamcoin's interpretation in each base from 2 to 10. (A nontrivial divisor for a positive integer K is some positive integer other than 1 or K that evenly divides K.) For convenience, these divisors must be expressed in base 10.

 For example, for the jamcoin 1001 mentioned above, a possible set of nontrivial divisors for the base 2 through 10 interpretations of the jamcoin would be: 3, 7, 5, 6, 31, 8, 27, 5, and 77, respectively.

 Can you produce J different jamcoins of length N, along with proof that they are legitimate?
 Input

 The first line of the input gives the number of test cases, T. T test cases follow; each consists of one line with two integers N and J.
 Output

 For each test case, output J+1 lines. The first line must consist of only Case #x:, where x is the test case number (starting from 1). Each of the last J lines must consist of a jamcoin of length N followed by nine integers. The i-th of those nine integers (counting starting from 1) must be a nontrivial divisor of the jamcoin when the jamcoin is interpreted in base i+1.

 All of these jamcoins must be different. You cannot submit the same jamcoin in two different lines, even if you use a different set of divisors each time.
 */
public class JamCoins {
    public static void main(String[] args) {
        JamCoins jamCoins = new JamCoins();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int J = in.nextInt();
            System.out.println("Case #" + i + ":");
            jamCoins.printJamCoins(N, J);
        }
    }

    // Uses binary search to get a value closer to sqrt of the given BigInteger.
    private BigInteger getApproxSqrt(BigInteger value) {
        BigInteger low = BigInteger.ZERO, high = value;
        while (low.compareTo(high) < 0) {
            BigInteger mid = low.add(
                (high.subtract(low)).divide(BigInteger.valueOf(2))
            );
            BigInteger squareOfMid = mid.multiply(mid);
            if(squareOfMid.compareTo(value) > 0) {
                high = mid.subtract(BigInteger.ONE);
            } else if(squareOfMid.compareTo(value) == 0){
                return mid;
            } else {
                low = mid.add(BigInteger.ONE);
            }
        }
        return low;
    }

    // Determines if it is prime; if yes; it returns 0
    // If not prime; then returns first exact divisor
    // To determine if it is prime;
    // it uses number from 2 till approximate sqrt to find if there is an exact divisor
    private BigInteger isPrime(BigInteger value) {
        BigInteger approxSqrt = getApproxSqrt(value);
        BigInteger current = BigInteger.valueOf(2);
        while(current.compareTo(approxSqrt) <= 0) {
            BigInteger [] divisionResult = value.divideAndRemainder(current);
            if(divisionResult[1].compareTo(BigInteger.ZERO) == 0) {
                return current;
            }
            current = current.add(BigInteger.ONE);
        }
        return BigInteger.ZERO;
    }

    // Converts a number containing 0s and 1s in String value
    // to BigInteger by converting to given base
    private BigInteger convertToBase(String value, int base) {
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < value.length(); i++) {
            result = result.multiply(BigInteger.valueOf(base));
            if(value.charAt(i) == '1') {
                result = result.add(BigInteger.ONE);
            }
        }
        return result;
    }

    // This prints the given string of 0s and 1s if it is a jam coin and return true
    // if it is not a jam coin then it returns false
    // To find out if it is jam coin; it converts it to base 2 to 10 and finds out if each result is prime.
    private boolean printIfJamCoin(String value) {
        List<BigInteger> exactDivisors = new ArrayList<BigInteger>(9);
        for(int base = 2; base <= 10; base++) {
            BigInteger valueInGivenBase = convertToBase(value, base);
            BigInteger exactDivisor = isPrime(valueInGivenBase);
            if(exactDivisor.compareTo(BigInteger.ZERO) == 0) {
                // this is prime number; so this is not jam coin.
                return false;
            }
            exactDivisors.add(exactDivisor);
        }
        System.out.print(value + " ");
        for(BigInteger bigInteger : exactDivisors) {
            System.out.print(bigInteger.toString() + " ");
        }
        System.out.println();
        return true;
    }

    private String getPaddedBinaryString(int value, int totalLength) {
        StringBuilder result = new StringBuilder(Integer.toBinaryString(value));
        if(result.length() != totalLength) {
            StringBuilder modified = new StringBuilder(totalLength - result.length());
            for(int i = 0; i < (totalLength - result.length()); i++) {
                modified.append('0');
            }
            modified.append(result);
            result = modified;
        }
        return result.toString();
    }

    public void printJamCoins(int N, int J) {
        int jamCoinCount = 0;
        for(int i = 0; i < 1 << (N - 2); i++) {
            StringBuilder current = new StringBuilder(N);
            current.append('1');
            current.append(getPaddedBinaryString(i, N - 2));
            current.append('1');
            if(printIfJamCoin(current.toString())) {
                jamCoinCount++;
            }
            if(jamCoinCount == J) {
                break;
            }
        }
    }
}
