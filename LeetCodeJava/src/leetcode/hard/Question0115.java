package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0115 {

    /**
     * Question 115 Distinct Subsequences
     * <p>
     * Given two strings s and t, return the number of distinct subsequences of s which equals t.
     * <p>
     * A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
     * <p>
     * The test cases are generated so that the answer fits on a 32-bit signed integer.
     */

    /**
     * Solution 1
     * Runtime: 9 ms, faster than 88.72% of Java online submissions for Distinct Subsequences.
     * Memory Usage: 45.9 MB, less than 78.75% of Java online submissions for Distinct Subsequences.
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];

        char t0 = t.charAt(0);
        dp[0][0] = s.charAt(0) ==  t0? 1 : 0;

        for (int i = 1; i < s.length(); i++) {
            if (t0 == s.charAt(i)) {
                dp[i][0] = dp[i-1][0] + 1;
            }else {
                dp[i][0] = dp[i-1][0];
            }
        }

        for (int i = 1; i < t.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == t.charAt(i)){
                    dp[j][i] = dp[j-1][i-1] + dp[j-1][i];
                }else {
                    dp[j][i] = dp[j-1][i];
                }
            }
        }

        return dp[s.length()-1][t.length()-1];
    }
}
