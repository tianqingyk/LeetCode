package google.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0091 {

    /**
     * 91. Decode Ways
     * https://leetcode.com/problems/decode-ways/
     */


    /**
     * Solution 1
     * So Ugly
     * Runtime: 24 ms, faster than 5.15% of Java online submissions for Decode Ways.
     * Memory Usage: 42 MB, less than 38.91% of Java online submissions for Decode Ways.
     */
    String s;
    int[][] dp;
    public int numDecodings(String s) {
        this.s = s;
        dp = new int[s.length()][2];
        return recurse(0,null);
    }

    private int recurse(int index, Character previous) {
        int result = 0;
        if (index > s.length() - 1) {
            if (previous == null) return 1;
            return 0;
        }else {
            if (dp[index][previous == null ? 0 : 1] > 0) return dp[index][previous == null ? 0 : 1];

            char current = s.charAt(index);
            if (previous != null) {
                if (previous != '2' || current <= '6'){
                    result = recurse(index + 1, null);
                }
            }else {
                if (current == '0') result = 0;
                else {
                    if (current < '3') {
                        result += recurse(index + 1, current);
                    }

                    result += recurse(index + 1, null);
                }
            }
        }

        dp[index][previous == null ? 0 : 1] = result;
        return result;
    }

    /**
     * Solution 2 Copy From Solution
     * Recursive Approach with Memoization
     */
    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings2(String s) {
        return recursiveWithMemo(0, s);
    }

    private int recursiveWithMemo(int index, String str) {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length() - 1) {
            return 1;
        }


        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }

        // Save for memoization
        memo.put(index, ans);

        return ans;
    }

    /**
     * Solution 3
     * Iterative Approach
     */
    public int numDecodings3(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for(int i = 2; i < dp.length; i++) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }

    /**
     * Solution 4
     * Iterative, Constant Space
     */

    public int numDecodings4(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }

            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

}
