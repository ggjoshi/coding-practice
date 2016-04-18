import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Utilities for 2D array
 */
public class TwoDArrayUtilities {

    /**
     * Find number of ways to get from top left to bottom right in (m by n) matrix.
     * @param m num of rows
     * @param n num of cols
     * @return number of ways to get from (0, 0) to (m - 1, n - 1)
     */
    public static int numWaysToGetFromTopToBottom(int m, int n, boolean [][] obstacles) {
        int [][] result = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(obstacles[i][j]) {
                    result[i][j] = 0;
                } else {
                    if(i == 0 && j == 0) {
                        result[i][j] = 1;
                    }
                    if(i >= 1) {
                        result[i][j] += result[i - 1][j];
                    }
                    if(j >= 1) {
                        result[i][j] += result[i][j - 1];
                    }
                }
            }
        }
        return result[m - 1][n - 1];
    }

    /*
     * Two d array:-
     * 1 2 3
     * 3 4 5
     * 5 6 7
     * Sequence is <1, 3, 4, 6>
     * It exists.
     * You can traverse only in four neighbours of a cell above, left, below and right
     * sequenceExists[i][j] = (twoDArray[i][j] == element at index k in sequence) &&
     *                      ((sequenceExists[i - 1][j] && twoDArray[i - 1][j] == element at index k - 1 in sequence) ||
     *                      (sequenceExists[i][j - 1] && twoDArray[i][j - 1] == element at index k - 1 in sequence))
     */
    public static boolean doesSequenceExist(int [][] twoDArray, int [] sequence) {
        if(twoDArray == null ||
            twoDArray.length == 0 ||
            sequence == null) {
            throw new IllegalArgumentException("input is null.");
        }
        int colLength = twoDArray[0].length;
        boolean [][] sequenceExists = new boolean[twoDArray.length][];
        for(int i = 0; i < twoDArray.length; i++) {
            sequenceExists[i] = new boolean[colLength];
        }
        Map<Integer, Integer> sequenceMap = new HashMap<Integer, Integer>(sequence.length);
        for(int i = 0; i < sequence.length; i++) {
            sequenceMap.put(sequence[i], i);
        }

        /**
         * 1 2 3
         * 3 4 5
         * 5 6 7
         * Sequence is <1, 3, 4, 6>
         */

        for(int i = 0; i < twoDArray.length; i++) {
            for(int j = 0; j < colLength; j++) {
                Integer index = sequenceMap.get(twoDArray[i][j]);
                if(index == null) {
                    continue;
                }
                if(i == 0 ||
                    sequenceExists[i - 1][j]) {
                    Integer iMinus1J = (i == 0) ? -1 : sequenceMap.get(twoDArray[i - 1][j]);
                    if(iMinus1J != null &&
                        iMinus1J == index - 1) {
                        sequenceExists[i][j] = true;
                    }
                }
                if(j == 0 ||
                    sequenceExists[i][j - 1]) {
                    Integer iJMinus1 = (j == 0) ? -1 : sequenceMap.get(twoDArray[i][j - 1]);
                    if(iJMinus1 != null &&
                        iJMinus1 == index - 1) {
                        sequenceExists[i][j] = true;
                    }
                }
                if(index == sequence.length - 1 &&
                    sequenceExists[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class SequenceExistsCacheEntry {
        private int i, j, offset;
        public SequenceExistsCacheEntry(int i, int j, int offset) {
            this.i = i;
            this.j = j;
            this.offset = offset;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null ||
                obj.getClass() != getClass()) {
                return false;
            }
            SequenceExistsCacheEntry sequenceExistsCacheEntry =
                (SequenceExistsCacheEntry) obj;
            return (i == sequenceExistsCacheEntry.i &&
                (j == sequenceExistsCacheEntry.j) &&
            offset == sequenceExistsCacheEntry.offset);
        }

        @Override
        public int hashCode() {
            return (i + j + offset);
        }
    }

    public static boolean doesSequenceExistRecursive(int [][] twoDArray, int [] sequence) {
        if(twoDArray == null || twoDArray.length == 0 ||
            sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Input not valid");
        }
        Set<SequenceExistsCacheEntry> cacheEntries = new HashSet<SequenceExistsCacheEntry>();

        for(int i = 0; i < twoDArray.length; i++) {
            for(int j = 0; j < twoDArray[i].length; j++) {
                if(doesSequenceExistRecursiveHelper(twoDArray,
                    sequence,
                    0,
                    i,
                    j,
                    cacheEntries)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean doesSequenceExistRecursiveHelper(int [][] twoDArray,
                                                           int [] sequence,
                                                           int offset,
                                                           int row,
                                                           int col,
                                                           Set<SequenceExistsCacheEntry> cacheEntries) {
        if(offset == sequence.length) {
            return true;
        }

        if(row < 0 || row >= twoDArray.length ||
            col < 0 || col >= twoDArray[row].length) {
            return false;
        }
        if(cacheEntries.contains(new SequenceExistsCacheEntry(row, col, offset))) {
            return false;
        }

        if(sequence[offset] != twoDArray[row][col]) {
            return false;
        }

        if(doesSequenceExistRecursiveHelper(twoDArray, sequence, offset + 1, row, col + 1, cacheEntries) ||
            doesSequenceExistRecursiveHelper(twoDArray, sequence, offset + 1, row, col - 1, cacheEntries) ||
            doesSequenceExistRecursiveHelper(twoDArray, sequence, offset + 1, row + 1, col, cacheEntries) ||
            doesSequenceExistRecursiveHelper(twoDArray, sequence, offset + 1, row - 1, col, cacheEntries)) {
            return true;
        }
        cacheEntries.add(new SequenceExistsCacheEntry(row, col, offset));
        return false;
    }

    /*
     * Rotate matrix layer by layer.
     * In each layer; you rotate 4 elements one by one.
     * Go to the inner layer once outer layer is done.
     */
    public static void rotateSquareMatrix(int [][] squareMatrix) {
        if(squareMatrix == null || squareMatrix.length == 0) {
            return;
        }

        /*
         * 1 2 3
         * 4 5 6
         * 7 8 9
         */
        int matrixLength = squareMatrix.length;
        int layer = 0, numElementsInLayer = matrixLength - 2*layer - 1;
        while(numElementsInLayer > 0) {
            // rotate this layer
            for(int i = 0; i < numElementsInLayer; i++) {
                int temp =
                    squareMatrix[layer][layer + i];
                squareMatrix[layer][layer + i] =
                    squareMatrix[layer + numElementsInLayer - i][layer];
                squareMatrix[layer + numElementsInLayer - i][layer] =
                    squareMatrix[layer + numElementsInLayer][layer + numElementsInLayer - i];
                squareMatrix[layer + numElementsInLayer][layer + numElementsInLayer - i] =
                    squareMatrix[layer + i][layer + numElementsInLayer];
                squareMatrix[layer + i][layer + numElementsInLayer] = temp;
            }
            // move to next layer
            layer++;
            numElementsInLayer = matrixLength - 2*layer - 1;
        }
    }

    public static void print2DMatrix(int [][] twoDMatrix) {
        for (int i = 0; i < twoDMatrix.length; i++) {
            for(int j = 0; j < twoDMatrix[i].length; j++) {
                System.out.print(twoDMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        boolean [][] obstacles = new boolean[][] {{false, false, false},
                                                  {true, false, false},
                                                  {true, false, false}};
        System.out.println("numWaysToGetFromTopToBottom(3, 3) = " + numWaysToGetFromTopToBottom(3, 3, obstacles));
        int [][] twoDArray = new int[][] {{1, 2, 3},
            {3, 4, 5},
            {5, 6, 7}};
        int [] validSequence = {1, 3, 4, 6};
        int [] inValidSequence = {1, 2, 1, 2};
        System.out.println(doesSequenceExist(twoDArray, validSequence));
        System.out.println(doesSequenceExist(twoDArray, inValidSequence));
        System.out.println(doesSequenceExistRecursive(twoDArray, validSequence));
        System.out.println(doesSequenceExistRecursive(twoDArray, inValidSequence));

        int [][] matrixToRotate = new int[][] {{1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}};
        print2DMatrix(matrixToRotate);
        rotateSquareMatrix(matrixToRotate);
        print2DMatrix(matrixToRotate);
    }
}
