import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * In this board game, players have to try to advance through a sequence of positions.
 * Each position has a non-negative integer associated with it representing the maximum you can advance from there.
 * e.g. A = {3, 3, 1, 0, 2, 0, 1} is the board game then sequence leading to last position is {1, 3, 2}
 * Given a board game, find out if you can advance to last position from beginning of array.
 */
public class BoardGame {

    /*
     * Our approach will use exhaustive search or backtracking to
     * analyze all possibilities and find out which one works.
     */
    // Complexity -
    public boolean canAdvanceToEnd(int [] boardGame) {
        Deque<Integer> solutionSteps = new LinkedList<Integer>();
        return canAdvanceToEndHelper(boardGame, 0, solutionSteps);
    }
    /*
     * e.g. A = {3, 3, 1, 0, 2, 0, 1}
     * O(product of non-zero values in each element of board)
     */
    private boolean canAdvanceToEndHelper(int [] boardGame,
                                          int currentPosition,
                                          Deque<Integer> solutionSteps) {
        if(currentPosition >= boardGame.length - 1) {
            System.out.println("Solution is - " + solutionSteps.toString());
            return true;
        }

        for (int possibleMove = 1; possibleMove <= boardGame[currentPosition]; possibleMove++) {
            solutionSteps.addLast(possibleMove);
            if(canAdvanceToEndHelper(boardGame, currentPosition + possibleMove, solutionSteps)) {
                return true;
            }
            solutionSteps.removeLast();
        }
        return false;
    }

    public boolean canAdvanceToEndLinear(int [] boardGame) {
        int furthestReach = 0;
        for (int i = 0; i <= furthestReach && i <= boardGame.length - 1; i++) {
            furthestReach = Math.max(furthestReach, boardGame[i] + i);
        }
        return furthestReach >= boardGame.length - 1;
    }
}
