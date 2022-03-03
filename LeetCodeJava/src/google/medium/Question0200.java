package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0200 {

    /**
     * 200. Number of Islands
     */

    /**
     * Solution
     * Runtime: 3 ms, faster than 89.53% of Java online submissions for Number of Islands.
     * Memory Usage: 57.3 MB, less than 32.46% of Java online submissions for Number of Islands.
     */
    char[][] gird;
    int m, n;
    int count;
    public int numIslands(char[][] gird) {
        m = gird.length;
        n = gird[0].length;
        this.gird = gird;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(gird[i][j] == '1') {
                    count++;
                    recursive(i,j);
                }
            }
        }
        return count;
    }

    private void recursive(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (gird[i][j] != '1') return;
        gird[i][j] = 'O';
        recursive(i - 1, j);
        recursive(i + 1, j);
        recursive(i, j - 1);
        recursive(i, j + 1);
    }

}
