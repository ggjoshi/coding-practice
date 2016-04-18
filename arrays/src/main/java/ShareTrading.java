/**
 * Problems related to share prices, profit made etc.
 */
public class ShareTrading {
    /*
     * Given an array of share prices over some period of time,
     * find out the maximum profit that can be made by buying/selling the share.
     */
    public static int findMaxProfit(int [] input) {
        if(input == null || input.length < 2)
            return 0;
        int maxProfitSoFar = 0;
        int minElementSoFar = input[0];
        for(int i = 1; i < input.length; i++) {
            int currentProfit = (input[i] - minElementSoFar);
            maxProfitSoFar = Math.max(currentProfit, maxProfitSoFar);
            minElementSoFar = Math.min(input[i], minElementSoFar);
        }
        return maxProfitSoFar;
    }
}
