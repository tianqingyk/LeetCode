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
        if (i > 0 && matrix[i - 1][j] > value) nextList.add(new int[]{matrix[i - 1][j], i - 1, j});
        if (i < m - 1 && matrix[i + 1][j] > value) nextList.add(new int[]{matrix[i + 1][j], i + 1, j});
        if (j > 0 && matrix[i][j - 1] > value) nextList.add(new int[]{matrix[i][j - 1], i, j - 1});
        if (j < n - 1 && matrix[i][j + 1] > value) nextList.add(new int[]{matrix[i][j + 1], i, j + 1});

        nextList.sort((a, b) -> Integer.compare(a[0], b[0]));
        for (int k = 0; k < nextList.size(); k++) {
            int[] next = nextList.get(k);
            recursive(next[1], next[2], cache);
        }
        cache.removeLast();
        if (nextList.isEmpty())
            longest = Math.max(longest, dp[i][j]);
        recursiveFourDirections(i, j, new LinkedList<>());
    }

    private void recursiveFourDirections(int i, int j, LinkedList<Integer> cache) {
        if (i < m - 1) recursive(i + 1, j, cache);
        if (i > 0) recursive(i - 1, j, cache);
        if (j < n - 1) recursive(i, j + 1, cache);
        if (j > 0) recursive(i, j - 1, cache);
    }

    /**
     * Solution 2 Copy From Solution
     * DFS + Memoization Solution;
     * Runtime: 14 ms, faster than 48.39% of Java online submissions for Longest Increasing Path in a Matrix.
     * Memory Usage: 54.1 MB, less than 41.02% of Java online submissions for Longest Increasing Path in a Matrix.
     */
    // DFS + Memoization Solution
    // Accepted and Recommended

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
        }
        return ++cache[i][j];
    }

    /**
     * Solution 3 Copy From Solution
     * Peeling Onion
     */

    // Topological Sort Based Solution
// An Alternative Solution

    private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath3(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i)
            System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);

        // calculate outdegrees
        int[][] outdegree = new int[m + 2][n + 2];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                for (int[] d : dir)
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]])
                        outdegree[i][j]++;

        // find leaves who have zero out degree as the initial level
        n += 2;
        m += 2;
        List<int[]> leaves = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i)
            for (int j = 1; j < n - 1; ++j)
                if (outdegree[i][j] == 0) leaves.add(new int[]{i, j});

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                for (int[] d : dir) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (matrix[node[0]][node[1]] > matrix[x][y])
                        if (--outdegree[x][y] == 0)
                            newLeaves.add(new int[]{x, y});
                }
            }
            leaves = newLeaves;
        }
        return height;
    }
}
