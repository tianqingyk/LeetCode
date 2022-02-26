package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0063 {

    /**
     * 63. Unique Paths II
     * <p>
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * <p>
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * <p>
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * <p>
     * An obstacle and space is marked as 1 and 0 respectively in the grid.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
     * Memory Usage: 40.5 MB, less than 41.26% of Java online submissions for Unique Paths II.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) dp[i][0] = 0;
            else dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) dp[0][i] = 0;
            else dp[0][i] = dp[0][i-1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
