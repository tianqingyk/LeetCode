package leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/27/2022
 */
public class Question0828 {

    /**
     * 828. Count Unique Characters of All Substrings of a Given String
     */

    /**
     * Solution 1
     * Time Limit Exceeded
     */

    int[][] dp;
    String s;
    int result = 0;
    Set<String> visited = new HashSet<>();
    public int uniqueLetterString(String s) {
        if(s == null || s.length() == 0) return 0;
        this.s = s;
        int len = s.length();
        dp = new int[len+1][26];

        for(int i =0; i < len; i++) {
            char c = s.charAt(i);
            for(int j =0; j < 26;j++){
                dp[i+1][j] = dp[i][j];
            }
            dp[i+1][c-'A'] += 1;
        }


        for(int i = 0; i < len; i++){
            for(int j = i+1; j < len+1; j++){
                for(int k = 0; k < 26;k++){
                    result += dp[j][k] - dp[i][k] == 1 ?1:0;
                }
            }
        }
        return result;
    }

    /**
     * Solution 2 Copy from discussion
     * One Pass
     */

    public int uniqueLetterString2(String S) {
        int[][] index = new int[26][2];
        for (int i = 0; i < 26; ++i) Arrays.fill(index[i], -1);
        int res = 0, N = S.length();
        for (int i = 0; i < N; ++i) {
            int c = S.charAt(i) - 'A';
            res = res + (i - index[c][1]) * (index[c][1] - index[c][0]);
            index[c] = new int[] {index[c][1], i};
        }
        for (int c = 0; c < 26; ++c)
            res = res + (N - index[c][1]) * (index[c][1] - index[c][0]);
        return res;
    }

}
