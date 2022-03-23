package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/22
 */
public class Question0014 {

    /**
     * 14. Longest Common Prefix
     * <p>
     * Write a function to find the longest common prefix string amongst an array of strings.
     * <p>
     * If there is no common prefix, return an empty string "".
     */

    /**
     * Solution 1
     * Runtime: 3 ms, faster than 38.39% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 42.4 MB, less than 19.01% of Java online submissions for Longest Common Prefix.
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }

        String str0 = strs[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str0.length(); i++) {
            char c = str0.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if (i > str.length() - 1 || c != str.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }

        return sb.toString();
    }
}
