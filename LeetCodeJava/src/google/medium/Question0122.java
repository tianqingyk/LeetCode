package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0122 {

    /**
     * 122. Best Time to Buy and Sell Stock II
     */

    /**
     * Solution 1
     * Runtime: 2 ms, faster than 35.45% of Java online submissions for Best Time to Buy and Sell Stock II.
     * Memory Usage: 41.9 MB, less than 53.27% of Java online submissions for Best Time to Buy and Sell Stock II.
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /**
     * Solution 2 Copy From Solution
     * Simple One Pass
     */
    public int maxProfit2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
