package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/26
 */
public class Question0085 {

    /**
     * Question 85 Maximal Rectangle
     * Given a rows X cols binary matrix filled with 0's and 1's, find the largest rectangle containing
     * only 1's and return its area.
     */

    /**
     * solution 1
     * Runtime: 5 ms, faster than 76.71% of Java online submissions for Maximal Rectangle.
     * Memory Usage: 42.7 MB, less than 27.37% of Java online submissions for Maximal Rectangle.
     * @param matrix
     * @return
     */

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;

        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i][j];
                if (c == '1'){
                    dp[j][i] = j == 0 ? 1: dp[j-1][i] +1;
                }else if (c == '0'){
                    dp[j][i] = 0;
                }
            }
        }

        for (int j = 0; j < n; j++){
            maxArea = Math.max(maxArea, largestRectangleArea(dp[j]));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length);
    }

    private int largestRectangleArea(int[] heights, int begin, int end) {
        if (begin == end) {
            return 0;
        }
        if (begin + 1 == end) {
            return heights[begin];
        }
        int max = heights[begin];
        int min = heights[begin];
        int minIndex = begin;
        for (int i = begin; i < end; i++) {
            int height = heights[i];
            if (max < height) {
                max = height;
            } else if (min > height) {
                min = height;
                minIndex = i;
            }
        }
        int max1 = Math.max(max, min * (end - begin));
        int left = 0;
        int right = 0;
        if ((minIndex - begin) * max > max1) {
            left = largestRectangleArea(heights, begin, minIndex);
        }
        if ((end - minIndex - 1) * max > max1) {
            right = largestRectangleArea(heights, minIndex + 1, end);
        }
        int max2 = Math.max(left, right);

        return Math.max(max1, max2);
    }
}
