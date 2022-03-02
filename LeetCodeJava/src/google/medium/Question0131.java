package google.medium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0131 {

    /**
     * 131. Palindrome Partitioning
     */


    /**
     * Solution 1
     * Runtime: 44 ms, faster than 7.97% of Java online submissions for Palindrome Partitioning.
     * Memory Usage: 233.7 MB, less than 5.02% of Java online submissions for Palindrome Partitioning.
     */
    char[] chars;
    List<List<String>> result = new ArrayList<>();
    Set<String> palindromes = new HashSet<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() < 1) return result;
        this.chars = s.toCharArray();
        recursive(0, new StringBuilder(), new LinkedList<>());
        return result;
    }

    private void recursive(int index, StringBuilder sb, LinkedList<String> cache) {
        if (index > chars.length - 1) {
            if (sb.length() == 0) result.add(new LinkedList<>(cache));
            return;
        }
        sb.append(chars[index]);
        if (isPalindrome(sb)) {
            cache.add(sb.toString());
            recursive(index + 1, new StringBuilder(), cache);
            cache.removeLast();
        }
        recursive(index + 1, sb, cache);
    }

    private boolean isPalindrome(StringBuilder sb) {
        String str = sb.toString();
        if (palindromes.contains(str)) return true;
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) return false;
        }
        palindromes.add(str);
        return true;
    }

    /**
     * Solution 2 Copy From Solution
     * Backtracking
     */

    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }

    /**
     * Solution 3 Copy From Solution
     * Backtracking with Dynamic programming
     */

    public List<List<String>> partition3(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }


}
