import java.util.HashSet;
import java.util.Set;

/*
 * Find recurrence in division of numerator and denominator
 */
public class RecurrenceInFractionFinder {
    public void findRecurrenceInDivision(int numerator, int denominator) {
        System.out.println("num = " + numerator + " denom = " + denominator);
        if(denominator == 0) {
            System.out.println("Division by 0");
            return;
        }
        int reminder = numerator % denominator;
        StringBuilder recurrence = new StringBuilder();
        Set<Integer> remindersSeenSoFar = new HashSet<Integer>();
        while(reminder != 0) {
            int currentDigit = (reminder * 10) / denominator;
            reminder = (reminder * 10) % denominator;
            if(remindersSeenSoFar.contains(reminder)) {
                break;
            }
            remindersSeenSoFar.add(reminder);
            recurrence.append(currentDigit);
        }
        if(reminder == 0) {
            System.out.println("No recurrence found; divides exactly");
        } else {
            System.out.println("Recurrence is : " + recurrence);
        }
    }

    public static void main(String[] args) {
        RecurrenceInFractionFinder recurrenceInFractionFinder =
            new RecurrenceInFractionFinder();
        recurrenceInFractionFinder.findRecurrenceInDivision(8, 5);
        recurrenceInFractionFinder.findRecurrenceInDivision(22, 7);
    }
}