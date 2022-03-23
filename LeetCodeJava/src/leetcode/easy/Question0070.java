package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0070 {

    /**
     * 70. Climbing Stairs
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
     * Memory Usage: 40.5 MB, less than 34.89% of Java online submissions for Climbing Stairs.
     */
    public int climbStairs(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }
}
