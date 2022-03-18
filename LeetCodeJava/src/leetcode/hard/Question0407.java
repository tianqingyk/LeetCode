package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0407 {

    /**
     * 407. Trapping Rain Water II
     */

    /**
     * Solution
     * Runtime: 1304 ms, faster than 5.12% of Java online submissions for Trapping Rain Water II.
     * Memory Usage: 56.5 MB, less than 7.33% of Java online submissions for Trapping Rain Water II.
     */
    int m, n;
    int[][] heightMap;
    int height = 0;
    int sumRain = 0;
    int rain = 0;
    int min = Integer.MAX_VALUE;
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) return 0;
        this.heightMap = heightMap;
        m = heightMap.length;
        n = heightMap[0].length;

        while (true) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (heightMap[i][j] > height) {
                        min = Math.min(min, heightMap[i][j]);
                        continue;
                    } else {
                        heightMap[i][j] = 0;
                        rain++;
                    }
                }
            }
            if (rain >= m * n - 3 || min == Integer.MAX_VALUE) break;

            for (int i = 0; i < m; i++) {
                bfs(i, 0);
                bfs(i, n - 1);
            }
            for (int i = 0; i < n; i++) {
                bfs(0, i);
                bfs(m - 1, i);
            }
            sumRain = sumRain + rain * (min - height);
            rain = 0;
            height = min;
            min = Integer.MAX_VALUE;
        }
        return sumRain;
    }

    private void bfs(int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (heightMap[i][j] != 0) return;
        rain--;
        heightMap[i][j] = height;
        bfs(i - 1, j);
        bfs(i + 1, j);
        bfs(i, j - 1);
        bfs(i, j + 1);
        return;
    }
}
