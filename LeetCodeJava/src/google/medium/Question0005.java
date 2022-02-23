package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/21
 */
public class Question0005 {

    /**
     * 5. Longest Palindromic Substring
     * <p>
     * Given a string, return the longest palindromic substring in s.
     */

    /**
     * Solution 1
     * Dynamic Programing
     * Runtime: 435 ms, faster than 22.79% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 80.8 MB, less than 23.86% of Java online submissions for Longest Palindromic Substring.
     */
    boolean[][] dp;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        dp = new boolean[s.length()][s.length()];

        for (int i = s.length() - 2; i >= 0; i--) {
            dp[i][i] = true;
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (1 > end - start) {
                    start = i;
                    end = i + 1;
                }
            }
            for (int j = i + 2; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (j - i > end - start) {
                            start = i;
                            end = j;
                        }
                    }
                }

            }
        }
        return s.substring(start, end + 1);
    }

    private boolean isPalindromic(String str) {
        for (int i = 0; i <= str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Solution 2 Copy from Solution
     * Expand Around Center
     * Runtime: 32 ms, faster than 78.83% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 43.1 MB, less than 50.60% of Java online submissions for Longest Palindromic Substring.
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * Solution 3
     * Manacher's algorithm
     * Runtime: 37 ms, faster than 75.75% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 54.4 MB, less than 27.67% of Java online submissions for Longest Palindromic Substring.
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.isEmpty()){
            return "";
        }

        if (s.length() == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append('*').append(s.charAt(i));
        }
        sb.append('*');

        Map<Integer, int[]> maxExtend = new HashMap<>();
        int mostRight = 0;
        int[] mostRightRange = new int[2];

        int[] maxRange = new int[2];

        for (int i = 1; i < sb.length() - 1; i++) {
            int[] iRange = new int[]{i, i};


            // Case 1:
            if (i >= mostRightRange[1]) {
                iRange = bruteForce(sb, iRange);
            }
            // Case 2:
            if (i < mostRightRange[1]) {
                int i1 = 2 * mostRight - i;
                if(maxExtend.containsKey(i1)){
                    int[] i1Range = maxExtend.get(i1);
                    if (i1Range[0] > mostRightRange[0]) {
                        iRange[0] = 2 * mostRight - i1Range[1];
                        iRange[1] = 2 * mostRight - i1Range[0];
                    }

                    if (i1Range[0] <= mostRightRange[0]) {
                        iRange[1] = mostRightRange[1];
                        iRange[0] = 2 * i - iRange[1];
                        iRange = bruteForce(sb, iRange);
                    }
                }
            }

            if (iRange[1] > mostRightRange[1]) {
                mostRight = i;
                mostRightRange  = iRange;
            }
            maxExtend.put(i, iRange);
            if (iRange[1] - iRange[0] > maxRange[1] - maxRange[0]){
                maxRange = iRange;
            }
        }

        return s.substring(maxRange[0]/2, (maxRange[1]-1)/2 + 1);
    }

    private int[] bruteForce(StringBuilder sb, int[] range) {
        range[0] -= 1;
        range[1] += 1;
        while (range[0] >= 0 && range[1] < sb.length()) {
            if (sb.charAt(range[0]) == sb.charAt(range[1])) {
                range[0] -= 1;
                range[1] += 1;
            } else {
                break;
            }
        }
        range[0] += 1;
        range[1] -= 1;
        return range;
    }

    public static void main(String[] args) {
        Question0005 q = new Question0005();
        q.longestPalindrome3("babadada");
    }
}
