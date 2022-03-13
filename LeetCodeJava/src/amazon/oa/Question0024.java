package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/13/2022
 */
public class Question0024 {

    /**
     * 24. Max Profit
     * <p>
     * An Amazon seller is deciding which of their products to invest in for the next quarter to maximize their profits.
     * They have each of their products listed as segments of a circle. Due to varying market conditions, the products
     * do not sell consistently. The seller wants to achieve maximum profit using limited resources for investment.
     * The product list is segmented into a number of equal segments, and a projected profit is calculated for each segment.
     * The projected profit is the cost to invest versus the sale price of the product. The seller has chosen to invest
     * a number of contiguous segments along with those opposite. Determine the maximum profit the seller can achieve using
     * this approach.
     * <p>
     * For example, the product list is divided into n =6 sections and will select k = 2 contiguous sections and those
     * opposite to invest in. The profit estimates are profit = [1,5,1,3,7,-3] respectively. The diagrams below show
     * the possible choices with profits[0] at the 9 o'clock position and filling counterclockwise.
     * <p>
     * The profit levels, from left to right, are 1+5+7+13 = 16, 5+1+7+-3=10, and 1+3+-3+1=2. The maximum profit is 16.
     */

    public int maxProfit(int k, int[] profit) {
        int len = profit.length;
        int half = len / 2;

        int[] dp = new int[len];
        for (int i = 0; i < k; i++) {
            dp[0] += profit[i];
        }
        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] + profit[(i + k - 1) % len] - profit[i-1];
        }


        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < half; i++) {
            int prf = dp[i] + dp[i+k];
            maxProfit = Math.max(maxProfit, prf);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Question0024 q = new Question0024();
        System.out.println(q.maxProfit(2, new int[]{1, 5, 1, 3, 7, -3}));
        System.out.println(q.maxProfit(1, new int[]{3, -5}));
    }

}
