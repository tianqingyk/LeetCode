package amazon.oa;

import java.util.Arrays;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/13/2022
 */
public class Question0030 {

    /**
     * 30. Minimum Coin Flips
     *
     * Given the initial sequence of a coins, find the minimum number of coins that can be flipped to obtain a beautiful sequence.
     * All head facing coins or tails facing coins sequence is also valid.
     */

    public int minimumCoinFlips(String coins){
        int[][] dp = new int[coins.length()][4]; //0 H H 1 H T 2 T T 3 T H
        char c = coins.charAt(0);
        dp[0][0] = c == 'H'? 0 : 1;
        dp[0][1] = c == 'H'? 0 : 1;
        dp[0][2] = c == 'T'? 0 : 1;
        dp[0][3] = c == 'T'? 0 : 1;

        for (int i = 1; i < coins.length(); i++) {
            c = coins.charAt(i);
            dp[i][0] = dp[i-1][0] + (c == 'H' ? 0 : 1);
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + ((c == 'T' ? 0 : 1));
            dp[i][2] = dp[i-1][2] + (c == 'T'? 0 : 1);
            dp[i][3] = Math.min(dp[i-1][2], dp[i-1][3]) + ((c == 'H' ? 0 : 1));
        }

        return Arrays.stream(dp[coins.length()-1]).min().getAsInt();
    }

    public static void main(String[] args) {
        Question0030 q = new Question0030();
        System.out.println(q.minimumCoinFlips("HHHTTH"));
    }
}
