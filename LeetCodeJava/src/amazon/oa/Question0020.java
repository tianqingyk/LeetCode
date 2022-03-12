package amazon.oa;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0020 {

    /**
     * 20. Trees Height
     * <p>
     * There are N trees in Jon's backyard and height of tree i is h[i]. Jon doesn't like the appearance of it
     * and is planning to increase and decrease the height of trees such that the new heights are in strictly increasing
     * order. Every day he can pick one tree and increase or decrease the height by 1 unit. Heights can be 0 or even negative(it's a magical word)
     * <p>
     * Jon has guests coming after X days, hence he wants to know if it is possible to make heights strictly increasing
     * in no more than X days?
     */

    public boolean treesHeight(int x, int[] h) {
        int n = h.length;
        int min = Arrays.stream(h).min().getAsInt();

        for (int i = 0; i < n; i++) {
            h[i] -= min;
        }
        int max = Arrays.stream(h).max().getAsInt();

        int[][] dp = new int[n][max + 1];

        for (int i = 0; i <= max; i++)
            dp[0][i] = Math.abs(h[0] - i);

        for (int i = 1; i < n; i++) {
            int minSoFar = dp[i - 1][i - 1];
            for (int j = i; j <= max; j++) {
                dp[i][j] = Math.abs(h[i] - j) + minSoFar;
                minSoFar = Math.min(minSoFar, dp[i - 1][j]);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = n - 1; i <= max; i++) {
            result = Math.min(result, dp[n-1][i]);
        }
        System.out.println(result);
        return result <= x;
    }

    public static void main(String[] args) {
        Question0020 q = new Question0020();
        q.treesHeight(13, new int[]{7, 1, 4, 10 ,5, 8, 12}); // 13
        q.treesHeight(13, new int[]{5, 4, 3, 2, 1}); // 13
    }
}
