import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 */
public class Combinations {
    public void printCombinationsAddingUpToN(int score, int [] possibleSubScores) {
        Arrays.sort(possibleSubScores);
        Deque<Integer> solution = new ArrayDeque<Integer>();
        int totalCount = printCombinationsAddingUpToNHelper(score, possibleSubScores, 0, solution);
        System.out.println("Total of " + totalCount + " solutions.");
    }

    public int printCombinationsAddingUpToNHelper(int score,
                                                   int [] possibleSubScores,
                                                   int startIndex,
                                                   Deque<Integer> solution) {
        if(score == 0) {
            for(Integer value : solution) {
                System.out.print(value + " ");
            }
            System.out.println();
            return 1;
        } else if(score < 0) {
            return 0;
        }

        int solutionCount = 0;
        for(int i = startIndex; i < possibleSubScores.length; i++) {
            solution.push(possibleSubScores[i]);
            solutionCount += printCombinationsAddingUpToNHelper(score - possibleSubScores[i],
                possibleSubScores,
                i,
                solution);
            solution.pop();
        }
        return solutionCount;
    }

    public static void main(String [] args) {
        Combinations combinations = new Combinations();
        combinations.printCombinationsAddingUpToN(12, new int[]{2, 3, 7});
    }
}
