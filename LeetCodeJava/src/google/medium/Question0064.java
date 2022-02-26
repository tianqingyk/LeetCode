package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0064 {

    /**
     * 64. Minimum Path Sum
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     */

    /**
     * Solution 1
     * Runtime: 3 ms, faster than 60.98% of Java online submissions for Minimum Path Sum.
     * Memory Usage: 46.6 MB, less than 9.54% of Java online submissions for Minimum Path Sum
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i-1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[m-1][n-1];
    }

}
