package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0198 {

    /**
     * 198. House Robber
     */

    /**
     * Solution
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
     * Memory Usage: 41.8 MB, less than 18.13% of Java online submissions for House Robber.
     */
    public int rob(int[] nums){
        if (nums == null || nums.length < 1) return 0;
        int len = nums.length;
        int[][] dp = new int[len][2]; // 1 is robbed;

        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0]= Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1]= dp[i-1][0] + nums[i];
        }
        return Math.max(dp[len-1][0], dp[len-1][1]);
    }
}
