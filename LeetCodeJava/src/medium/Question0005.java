package medium;

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
     * Runtime: 1520 ms, faster than 5.01% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 159.4 MB, less than 5.76% of Java online submissions for Longest Palindromic Substring.
     */
    boolean[][] dp;
    public String longestPalindrome(String s) {
        String maxString = "";

        dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if(maxString.length() >= s.length() - i){
                    return maxString;
                }

                if (i == j) {
                    dp[i][j] = true;
                    if (1 > maxString.length()) {
                        maxString = s.substring(i, j+1);
                    }
                }
                char start = s.charAt(i);
                char end = s.charAt(j);
                if (start == end) {
                    String str = s.substring(i, j + 1);
                    if (j - i < 3) {
                        dp[i][j] = true;
                        if (str.length() > maxString.length()){
                            maxString = str;
                        }
                        continue;
                    }

                    if (dp[i+1][j-1] || isPalindromic(str)){
                        if (str.length() > maxString.length()){
                            maxString = str;
                        }
                        continue;
                    }
                }
            }
        }
        return maxString;
    }

    private boolean isPalindromic(String str) {
        for (int i = 0; i <= str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
