import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ShareTradingTest {

    @Test
    public void testFindMaxProfit() throws Exception {
        assertEquals(ShareTrading.findMaxProfit(new int[]{10, 20, 15, 5, 10, 30}), 25);
        assertEquals(ShareTrading.findMaxProfit(new int[]{10, 9, 8, 7, 6, 2, 5}), 3);
        assertEquals(ShareTrading.findMaxProfit(new int[]{10, 9, 8, 7, 6, 5}), 0);
    }
}