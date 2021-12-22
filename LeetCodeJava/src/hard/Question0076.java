package hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/22
 */
public class Question0076 {

    /**
     * Question 76 Minimum Window Substring
     * Given two strings  s and tt of lengths m and n respectively, return minimum window substring of s such that every character in t(
     * including duplicates) is included in the window. If there is no such substring, return the empty string "".
     * <p>
     * The testcases will be generated such that the answer is unique.
     * <p>
     * A substring is a contiguous sequence of characters within the string.
     */

    /**
     * Solution 1
     * Runtime: 17 ms, faster than 29.42% of Java online submissions for Minimum Window Substring.
     * Memory Usage: 39.6 MB, less than 60.52% of Java online submissions for Minimum Window Substring.
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        String result = null;

        Map<Character, Integer> tCountMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCountMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        int left = 0;
        int right = 0;

        Set requireChar = new HashSet(tCountMap.keySet());
        Map<Character, Integer> windowCountMap = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            windowCountMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
            if (requireChar.contains(c) && tCountMap.get(c) <= windowCountMap.get(c)) {
                requireChar.remove(c);
                if (requireChar.isEmpty()) {
                    String subString = s.substring(left, right + 1);
                    if (result == null) {
                        result = subString;
                    } else {
                        if (right - left + 1 < result.length()) {
                            result = subString;
                        }
                    }

                    while (left < right) {
                        left++;
                        c = s.charAt(left - 1);
                        windowCountMap.computeIfPresent(c, (k, v) -> v - 1);
                        if (tCountMap.containsKey(c) && tCountMap.get(c) > windowCountMap.get(c)) {
                            requireChar.add(c);
                            break;
                        }
                    }

                    subString = requireChar.isEmpty() ? s.substring(left, right + 1) : s.substring(left - 1, right + 1);

                    if (subString.length() < result.length()) {
                        result = subString;
                    }
                }
            }
            right++;
        }

        return result == null ? "" : result;
    }

    public static void main(String[] args) {
        Question0076 q = new Question0076();
        System.out.println(q.minWindow("ab", "b"));
    }

}
