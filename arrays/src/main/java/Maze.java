import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maze related fun problems.
 */
public class Maze {
    private static class Point {
        private int row, col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point neighbour(int rowOffset, int colOffset) {
            return new Point(row + rowOffset, col + colOffset);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Point other = (Point) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public int hashCode() {
            return row + col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }

    private static class PathFinder {
        private final Maze maze;
        private final Point start, end;

        // temporary data for computation
        private List<Point> path;
        private boolean [][] visited;

        public PathFinder(Maze maze, Point start, Point end) {
            this.maze = maze;
            this.start = start;
            this.end = end;
            this.path = new ArrayList<Point>();
            visited = new boolean[maze.twoDArray.length][];
            for(int i = 0; i < maze.twoDArray.length; i++) {
                visited[i] = new boolean[maze.twoDArray[i].length];
            }
        }

        private boolean isNeighbourInTheMaze(Point neighbor) {
            return ((neighbor.row >= 0 && neighbor.row < maze.twoDArray.length) &&
                (neighbor.col >= 0 && neighbor.col < maze.twoDArray[0].length));
        }

        private void findPathHelper(Point current) {
            visited[current.row][current.col] = true;
            path.add(current);

            if(current.equals(end)) {
                System.out.println(path);
                return;
            }

            for(int i = -1; i <= 1; i++) {
                for(int j = -1; j <= 1; j++) {
                    Point next = current.neighbour(i, j);
                    if(!isNeighbourInTheMaze(next) ||
                        !maze.twoDArray[next.row][next.col] ||
                        visited[next.row][next.col]) {
                        continue;
                    }
                    findPathHelper(next);
                }
            }
            path.remove(path.size() - 1);
        }

        private void findPath() {
            findPathHelper(start);
        }
    }

    private boolean [][] twoDArray;

    public Maze(boolean [][] twoDArray) {
        this.twoDArray = twoDArray;
    }


    public void findPath(Point start, Point end) {
        PathFinder pathFinder = new PathFinder(this, start, end);
        pathFinder.findPath();
    }

    public static void main(String[] args) {
        Maze maze = new Maze(new boolean[][] {{true, true, true, true},
                                              {false, true, false, false},
                                              {false, true, false, true},
                                              {true, true, false, true}});

        maze.findPath(new Point(3, 0), new Point(0, 3));
    }
}
