package leetcode.hard;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/21/2022
 */
public class Question0730 {

    /**
     * 730. Count Different Palindromic Subsequences
     */


    /**
     * Solution 1
     * Recursive + Memo
     * Time Limit Exceeded
     */
    Set<String> calculated = new HashSet<>();
    Set<String> visited = new HashSet<>();
    public int countPalindromicSubsequences(String s) {
        recursive(0, new StringBuilder(s));
        return (int) (calculated.size() % (Math.pow(10, 9) + 7));
    }

    public void recursive(int index, StringBuilder sb) {
        String curStr = sb.toString();
        if (visited.contains(curStr)) return;

        int len = sb.length();
        if(len == 0) return;
        if (index > len/2){
            calculated.add(sb.toString());
            return;
        }

        char cur = sb.charAt(index);
        char tail = sb.charAt(len - 1 - index);
        if (cur == tail) recursive(index + 1, sb);

        recursive(index, sb.deleteCharAt(len - 1 - index));
        sb.insert(len - 1 - index, tail);
        recursive(index, sb.deleteCharAt(index));
        sb.insert(index, cur);
        visited.add(curStr);
    }

    /**
     * Solution 2
     * DP
     * Runtime: 88 ms, faster than 52.36% of Java online submissions for Count Different Palindromic Subsequences.
     * Memory Usage: 56.3 MB, less than 36.57% of Java online submissions for Count Different Palindromic Subsequences.
     */
    public int countPalindromicSubsequences2(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;   // Consider the test case "a", "b" "c"...
        }

        for(int distance = 1; distance < len; distance++){
            for(int i = 0; i < len - distance; i++){
                int j = i + distance;
                if(chs[i] == chs[j]){
                    int low = i + 1;
                    int high = j - 1;

                    /* Variable low and high here are used to get rid of the duplicate*/

                    while(low <= high && chs[low] != chs[j]){
                        low++;
                    }
                    while(low <= high && chs[high] != chs[j]){
                        high--;
                    }
                    if(low > high){
                        // consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"},
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"},
                         and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}.
                         So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}.
                         */

                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    }
                    else if(low == high){
                        // consider the string from i to j is "a...a...a" where there is only one character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"},
                         and additional time as {"aaa"}. the reason why 1 counted is that
                         we also count {"aa"} that the first 'a' come from index i and the second come from index j. So totally dp[i][j] records {"a", "aa", "aaa"}
                        */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    }
                    else{
                        // consider the string from i to j is "a...a...a... a" where there are at least two character 'a' close to leftmost and rightmost 'a'
                       /* eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}.
                          the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"},
                          and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"},
                          which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
                          */
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1];
                    }
                }
                else{
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }

}
