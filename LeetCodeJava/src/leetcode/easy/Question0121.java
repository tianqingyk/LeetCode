package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0121 {

    /**
     * 121. Best Time to Buy and Sell Stock
     */

    /**
     * Solution 1
     * Runtime: 2 ms, faster than 86.91% of Java online submissions for Best Time to Buy and Sell Stock.
     * Memory Usage: 83.3 MB, less than 65.02% of Java online submissions for Best Time to Buy and Sell Stock.
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE;
        int sell = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            buy = Math.max(buy, -prices[i]);
            sell = Math.max(sell, prices[i] + buy);
        }
        return sell;
    }
}
