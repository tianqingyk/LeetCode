package leetcode.hard;

import java.util.HashMap;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0087 {

    /**
     * Question 87 Scramble String
     * <p>
     * We can scramble a string s to get a string t using the following algorithm:
     * <p>
     * If the length of the string is 1, stop.
     * If the length of the string is > 1, do the following:
     * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
     * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
     * Apply step 1 recursively on each of the two substrings x and y.
     * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
     */

    /**
     * Solution 1 time limit exceeded
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        int l = s1.length();
        if (s1.equals(s2)){
            return true;
        }

        HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < l; i++) {
            countMap.compute(s1.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        for (int i = 0; i < l; i++) {
            char c = s2.charAt(i);
            if(!countMap.containsKey(c)) {
                return false;
            }
            int val = countMap.compute(c, (k, v) -> v-1);
            if (val < 0){
                return false;
            }
        }

        if (l < 4){
            return true;
        }

        for (int i = 1; i < l; i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i))
                    && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i))
                    && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
        }
        return false;
    }

    /**
     * Solution 2 copy from Simple iterative DP Java solution with explanation
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble2(String s1, String s2) {
        int len = s1.length();
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         *
         * S1 [   x1    |         x2         ]
         *    i         i + q                i + k - 1
         *
         * here we have two possibilities:
         *
         * S2 [   y1    |         y2         ]
         *    j         j + q                j + k - 1
         *
         * or
         *
         * S2 [       y1        |     y2     ]
         *    j                 j + k - q    j + k - 1
         *
         * which in terms of F means:
         *
         * F(i, j, k) = for some 1 <= q < k we have:
         *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
         * */
        boolean [][][] F = new boolean[len][len][len + 1];
        for (int k = 1; k <= len; ++k)
            for (int i = 0; i + k <= len; ++i)
                for (int j = 0; j + k <= len; ++j)
                    if (k == 1)
                        F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                    else for (int q = 1; q < k && !F[i][j][k]; q++) {
                        F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                    }
        return F[0][0][len];
    }

    public static void main(String[] args) {
        Question0087 q = new Question0087();
        System.out.println(q.isScramble2("abcdbdacbdac", "bdacabcdbdac"));

        System.out.println(q.isScramble2( "abcde", "caebd"));
    }
}
