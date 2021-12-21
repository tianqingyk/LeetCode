package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/21
 */
public class Question0072 {

    /**
     * QUESTION 72 Edit Distance
     *
     * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
     *
     * You have the following three operations permitted on a word:
     *      Insert a character
     *      Delete a character
     *      Replace a character
     */

    /**
     * Solution 1
     * Dynamic programming
     * Runtime: 4 ms, faster than 91.35% of Java online submissions for Edit Distance.
     * Memory Usage: 38.8 MB, less than 88.12% of Java online submissions for Edit Distance.
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m <= 0){
            return n;
        }

        if (n <= 0){
            return m;
        }

        int[][] count = new int[m][n];

        char a1 = word1.charAt(0);
        char a2 = word2.charAt(0);

        if (a1 != a2){
            count[0][0] = 1;
        }

        for (int i = 1; i < n; i++){
            if (a1 == word2.charAt(i)){
                count[0][i] = i;
            }else {
                count[0][i] = count[0][i-1] +1;
            }
        }

        for (int i = 1; i < m; i++){
            if (a2 == word1.charAt(i)){
                count[i][0] = i;
            }else {
                count[i][0] = count[i-1][0] +1;
            }
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)){
                    count[i][j] = count[i-1][j-1];
                }else {
                    count[i][j] = 1+ Math.min(count[i-1][j-1], Math.min(count[i-1][j], count[i][j-1]));
                }
            }
        }
        return count[m-1][n-1];
    }

    public static void main(String[] args) {
        Question0072 q = new Question0072();
        System.out.println(q.minDistance("horse", "ros"));
        System.out.println(q.minDistance("intention", "executions"));
    }
}
