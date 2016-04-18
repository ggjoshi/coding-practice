import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * NQueens placer class.
 */
public class NQueensPlacer {
    private static class QueenNode {
        public QueenNode(int row, int col, int n) {
            this.row = row;
            this.col = col;
            this.n = n;
        }

        public int row, col, n;
    }

    private static boolean doQueensThreatenEachOther(int row1, int col1, int row2, int col2) {
        return ((row1 == row2) ||
            (col1 == col2) ||
            (Math.abs(row1 - row2) == Math.abs(col1 - col2)));
    }

    private static boolean canPlaceQueen(int currentRow, Deque<QueenNode> solution, int col) {
        for(QueenNode queenNode : solution) {
            if(doQueensThreatenEachOther(currentRow, col, queenNode.row, queenNode.col)) {
                return false;
            }
        }
        return true;
    }

    private static void printNQueenSolution(Deque<QueenNode> solution, String suffix) {
        System.out.println("N Queen solution " + suffix + " :-");
        for(QueenNode queenNode : solution) {
            for(int i = 0; i < solution.size(); i++) {
                if(i == queenNode.col) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    private static void nQueensSolverRecursiveHelper(int currentRow, int n, Deque<QueenNode> solution) {
        if(currentRow == n) {
            printNQueenSolution(solution, "recursive");
            return;
        }

        for(int col = 0; col < n; col++) {
            if(canPlaceQueen(currentRow, solution, col)) {
                QueenNode queenNode = new QueenNode(currentRow, col, n);
                solution.push(queenNode);
                nQueensSolverRecursiveHelper(currentRow + 1, n, solution);
                solution.pop();
            }
        }
    }

    public static void nQueensSolverRecursive(int n) {
        Deque<QueenNode> solution = new ArrayDeque(n);
        nQueensSolverRecursiveHelper(0, n, solution);
    }


    public static void nQueensSolverIterative(int n) {
        Deque<QueenNode> solution = new ArrayDeque(n);
        int row = 0, col = 0;
        // keep trying out next row/col and
        // backtrack till you go to row < 0
        while(row >= 0) {
            while((col < n) &&
               !canPlaceQueen(row, solution, col)) {
               col++;
            }

            if(col < n) {
                QueenNode queenNode = new QueenNode(row, col, n);
                solution.push(queenNode);
                row++;
                col = 0;
            }

            if(row == n || col == n) {
                if(row == n) {
                    printNQueenSolution(solution, "iterative");
                }
                row--;
                if(row >= 0) {
                    QueenNode lastNode = solution.pop();
                    col = lastNode.col + 1;
                }
            }
        }
    }

    public static void main(String [] args) {
        int n = 6;
        nQueensSolverIterative(n);
        nQueensSolverRecursive(n);
    }
}
