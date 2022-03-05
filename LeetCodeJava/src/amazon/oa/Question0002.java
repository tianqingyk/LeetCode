package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/4
 */
public class Question0002 {

    /**
     * Given a string s, and count the number of substring of s which is '010' or '101';
     */

    public int countSubstring(String s) {
        int[][] dp = new int[2][s.length()];
        char c0 = s.charAt(0);
        dp[0][0] = c0 == '0' ? 1 : 0;
        dp[1][0] = c0 == '1' ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                dp[1][i] = dp[1][i - 1] + 1;
                dp[0][i] = dp[0][i - 1];
                continue;
            }
            if (c == '0') {
                dp[0][i] = dp[0][i - 1] + 1;
                dp[1][i] = dp[1][i - 1];
            }
        }

        int result = 0;
        for (int i = 1; i < s.length()-1; i++) {
            char c = s.charAt(i);
            if (c == '0'){
                result += getNumber(dp[1], 0, i-1) * getNumber(dp[1], i+1, s.length()-1);
                continue;
            }
            if (c == '1') {
                result += getNumber(dp[0], 0, i-1) * getNumber(dp[0], i+1, s.length()-1);
            }
        }

        return result;
    }

    private int getNumber(int[] dp, int start, int end) {
        if (start == 0) return dp[end];
        return dp[end] - dp[start - 1];
    }

    public static void main(String[] args) {
        Question0002 q = new Question0002();
        int result = q.countSubstring("01001");
        System.out.println(result);
    }
}
