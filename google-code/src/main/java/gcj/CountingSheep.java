package gcj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 Bleatrix Trotter the sheep has devised a strategy that helps her fall asleep faster. First, she picks a number N. Then she starts naming N, 2 × N, 3 × N, and so on. Whenever she names a number, she thinks about all of the digits in that number. She keeps track of which digits (0, 1, 2, 3, 4, 5, 6, 7, 8, and 9) she has seen at least once so far as part of any number she has named. Once she has seen each of the ten digits at least once, she will fall asleep.

 Bleatrix must start with N and must always name (i + 1) × N directly after i × N. For example, suppose that Bleatrix picks N = 1692. She would count as follows:

 N = 1692. Now she has seen the digits 1, 2, 6, and 9.
 2N = 3384. Now she has seen the digits 1, 2, 3, 4, 6, 8, and 9.
 3N = 5076. Now she has seen all ten digits, and falls asleep.
 */
public class CountingSheep {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            System.out.println("Case #" + i + ": " + whenToSleep(n));
        }
    }

    // Return a string INSOMNIA if no way to sleep.
    // Else return the string representation of the number to sleep after.
    // The rationale here is that if we take multiples of n till 100;
    // we will eventually find the number which will have all digits.
    private static String whenToSleep(int n) {
        String insomnia = "INSOMNIA";
        if(n == 0) {
            return insomnia;
        }
        Set<Character> digitsFound = new HashSet<Character>(10);
        for(int i = 1; i <= 100; i++) {
            BigInteger current = BigInteger.valueOf(n).multiply(BigInteger.valueOf(i));
            String currentString = current.toString();
            for(int j = 0; j < currentString.length(); j++) {
                digitsFound.add(currentString.charAt(j));
            }
            if(digitsFound.size() == 10) {
                return currentString;
            }
        }
        return insomnia;
    }
}
