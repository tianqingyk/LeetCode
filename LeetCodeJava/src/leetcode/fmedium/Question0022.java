package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0022 {

    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     */


    /**
     * Solution 1
     * Runtime: 2 ms, faster than 62.74% of Java online submissions for Generate Parentheses.
     * Memory Usage: 43.2 MB, less than 34.58% of Java online submissions for Generate Parentheses.
     */
    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generateParenthesisHelper(n, 0 , new StringBuilder());
        return result;
    }


    public void generateParenthesisHelper(int n, int count, StringBuilder sb) {
        if (2 * n == sb.length()) {
            if (count == 0){
                result.add(sb.toString());
            }
            return;
        }
        if (2 * n < sb.length() + count){
            return;
        }
        if (count <= 0) {
            sb.append('(');
            generateParenthesisHelper(n, count + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        if (count > 0) {
            sb.append('(');
            generateParenthesisHelper(n, count + 1, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append(')');
            generateParenthesisHelper(n, count - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
    }
}
