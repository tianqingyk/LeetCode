package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0151 {

    /**
     * 151. Reverse Words in a String
     */

    /**
     * Solution
     * Runtime: 4 ms, faster than 92.20% of Java online submissions for Reverse Words in a String.
     * Memory Usage: 43.6 MB, less than 38.11% of Java online submissions for Reverse Words in a String.
     */
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) return s;

        List<String> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (end > start) result.add(s.substring(start, end));
                start = i + 1;
            }
            end = i + 1;
        }
        if (end > start) result.add(s.substring(start, end));

        StringBuilder sb = new StringBuilder();
        for (int i = result.size() - 1; i >= 0 ; i--) {
            sb.append(result.get(i)).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
