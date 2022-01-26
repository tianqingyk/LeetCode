package hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0188 {

    /**
     * 188. Best Time to Buy and Sell Stock IV
     *
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
     *
     * Find the maximum profit you can achieve. You may complete at most k transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously(i.e., you must sell the stock before you buy again).
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 99.82% of Java online submissions for Best Time to Buy and Sell Stock IV.
     * Memory Usage: 36.7 MB, less than 92.73% of Java online submissions for Best Time to Buy and Sell Stock IV.
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices){
        if (k < 1){
            return 0;
        }
        int[] dp = new int[2*k];
        for (int i = 0; i < k; i++) {
            dp[i] = Integer.MIN_VALUE;
            dp[i+1] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            for (int j = 0; j < k; j++) {
                dp[2*j] = j > 0 ? Math.max(dp[2*j], dp[2*j-1] -price) : Math.max(dp[2*j], -price);
                dp[2*j+1] = Math.max(price + dp[2*j], dp[2*j+1]);
            }
        }
        return dp[2*k-1];
    }

    public static void main(String[] args) {
        Question0188 q = new Question0188();
        q.maxProfit(2, new int[]{2,4,1});
    }
}
