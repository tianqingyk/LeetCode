package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/7/2022
 */
public class Question0221 {

    /**
     * 221. Maximal Square
     */

    /**
     * Solution
     * Runtime: 1950 ms, faster than 5.02% of Java online submissions for Maximal Square.
     * Memory Usage: 57.2 MB, less than 46.87% of Java online submissions for Maximal Square.
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i][j];
                if (c == '0') continue;
                int k = 0;
                while (true) {
                    k++;
                    if (i + k > m - 1 || j + k > n - 1) {
                        k--;
                        break;
                    }
                    boolean isSquare = true;
                    for (int l = 0; l <= k; l++) {
                        if (matrix[i + k][j + l] == '0' || matrix[i + l][j + k] == '0') {
                            isSquare = false;
                            break;
                        }
                    }
                    if (!isSquare) {
                        k--;
                        break;
                    }

                }
                max = Math.max(max, (k + 1) * (k + 1));

            }
        }

        return max;
    }

    /**
     * Solution 2 Copy From Solution
     * Dynamic Programming
     */
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    /**
     * Solution 3 Copy From Solution
     * Better Dynamic Programming
     */
    public int maximalSquare3(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }

}