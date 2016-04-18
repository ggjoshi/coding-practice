/**
 * Solver for tower of honoi problem
 * Transfer pegs from pole A to pole C using pole B as intermediary.
 */
public class TowerOfHonoi {
    public static void solveTOH(int n,
                                String from,
                                String to,
                                String using) {
        if(n == 0) {
            return;
        }
        solveTOH(n - 1, from, using, to);
        System.out.println("Move " + n +
            " from " + from +
            " to " + to);
        solveTOH(n - 1, using, to, from);
    }

    public static void main(String [] args) {
        solveTOH(2, "A", "C", "B");
    }
}
