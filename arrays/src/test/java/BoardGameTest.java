import org.junit.Assert;
import org.junit.Test;

public class BoardGameTest {

    @Test
    public void testCanAdvanceToEnd() throws Exception {
        int [] board = new int[]{3, 3, 1, 0, 2, 0, 1};
        BoardGame boardGame = new BoardGame();
        Assert.assertTrue(boardGame.canAdvanceToEnd(board));
        board = new int[]{2, 4, 1, 1, 0, 2, 3};
        Assert.assertTrue(boardGame.canAdvanceToEnd(board));
    }
}