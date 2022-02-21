package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/21
 */
public class Question0003 {

    /**
     * 3. Longest Substring Without Repeating Characters
     *
     * Given a string s, find the length of the longest substring without repeating characters.
     */

    /**
     * Solution 1
     * Runtime: 4 ms, faster than 95.09% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 42.4 MB, less than 54.89% of Java online submissions for Longest Substring Without Repeating Characters.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()){
            return 0;
        }
        char[] array = s.toCharArray();
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        Map<Character, Integer> cache = new HashMap<>();

        for (; end < s.length(); end++) {
            char c = array[end];
            if (cache.containsKey(c)) {
                int index = cache.get(c);
                if (index >= start) {
                    maxLen = Math.max(maxLen, end - start);
                    start = index+1;
                }
            }
            cache.put(c, end);
        }

        maxLen = Math.max(maxLen, end - start);
        return maxLen;
    }

    public static void main(String[] args) {

    }
}
