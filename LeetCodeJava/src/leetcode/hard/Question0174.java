package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0174 {

    /**
     * 174. Dungeon Game
     * The demons had captured the princesss and imprisoned her in the bottom-right corner of a dungeon.
     * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the
     * top-left room and must fight his way through dungeon to rescue the princess.
     * <p>
     * The knight has an initial health point represented by a positive integer.
     * If at any point his health point drops to 0 or below, he dies immediately.
     * <p>
     * Some rooms are guarded by demons(represented by negative), so the knight loses health upon entering these rooms;
     * other rooms are either empty(represented as 0) or contain magic orbs that increase knight's health(represented by positive integers).
     * <p>
     * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
     * <p>
     * Return the knight;s minimum initial health so that he can rescue the princess.
     * <p>
     * Note that any room can contain threats or power-ups, event the first room the knight enters and the bottom-right room where the princess is imprisoned.
     */

    /**
     * Solution 1 dynamic programming
     * Runtime: 2 ms, faster than 65.61% of Java online submissions for Dungeon Game.
     * Memory Usage: 41.3 MB, less than 17.51% of Java online submissions for Dungeon Game.
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = calculateHp(dungeon[m - 1][n - 1]);

        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            dp[m-1][i] = Math.max(1, dp[m-1][i+1] - dungeon[m-1][i]);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--){
                int value = dungeon[i][j];
                dp[i][j] = Math.max(1, Math.min(dp[i][j+1], dp[i+1][j]) - value);
            }
        }

        return dp[0][0];
    }

    private int calculateHp(int value) {
        return 1 + Math.abs(Math.min(0, value));
    }

    public static void main(String[] args) {
        Question0174 q = new Question0174();
        int[][] dungeon = new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}};
        int[][] dungeon2 = new int[][]{
                {1, -3, 3},
                {0, -2, 0},
                {-3, -3, -3}};
        int[][] dungeon3 = new int[][]{
                {1, -4, 5, -99},
                {2, -2, -2, -1}
        };
        int result = q.calculateMinimumHP(dungeon);
        System.out.println(result);
    }
}
