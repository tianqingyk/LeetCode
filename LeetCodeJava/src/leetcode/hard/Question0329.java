package leetcode.hard;

import amazon.oa.Question0030;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0329 {

    /**
     * 329. Longest Increasing Path in a Matrix
     */

    /**
     * Solution 1
     * Runtime: 72 ms, faster than 10.42% of Java online submissions for Longest Increasing Path in a Matrix.
     * Memory Usage: 75.3 MB, less than 5.13% of Java online submissions for Longest Increasing Path in a Matrix
     */
    int[][] dp;
    int[][] matrix;
    int m, n;
    int longest = Integer.MIN_VALUE;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        this.dp = new int[m][n];
        recursive(0, 0, new LinkedList<>());
        return longest;
    }

    private void recursive(int i, int j, LinkedList<Integer> cache) {
        if (cache.isEmpty() && dp[i][j] > 0 || dp[i][j] > cache.size() + 1) return;
        int value = matrix[i][j];
        cache.add(value);
        dp[i][j] = Math.max(dp[i][j], cache.size());
        List<int[]> nextList = new ArrayList<>();
        if (i > 0 && matrix[i - 1][j] > value) nextList.add(new int[]{matrix[i - 1][j], i-1, j});
        if (i < m - 1 && matrix[i + 1][j] > value) nextList.add(new int[]{matrix[i + 1][j], i+1, j});
        if (j > 0 && matrix[i][j - 1] > value) nextList.add(new int[]{matrix[i][j-1], i, j-1});
        if (j < n - 1 && matrix[i][j + 1] > value) nextList.add(new int[]{matrix[i][j+1], i, j+1});

        nextList.sort((a,b) -> Integer.compare(a[0], b[0]));
        for (int k = 0; k < nextList.size(); k++) {
            int[] next = nextList.get(k);
            recursive(next[1], next[2], cache);
        }
        cache.removeLast();
        if (nextList.isEmpty())
            longest = Math.max(longest, dp[i][j]);
        recursiveFourDirections(i, j, new LinkedList<>());
    }

    private void recursiveFourDirections(int i, int j, LinkedList<Integer> cache){
        if (i < m - 1) recursive(i+1,j, cache);
        if (i > 0) recursive(i-1,j, cache);
        if (j < n - 1) recursive(i,j+1, cache);
        if (j > 0) recursive(i,j-1, cache);
    }
}
