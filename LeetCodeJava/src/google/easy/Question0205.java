package google.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0205 {

    /**
     * 205. Isomorphic Strings
     */

    /**
     * Solution
     * Runtime: 17 ms, faster than 36.31% of Java online submissions for Isomorphic Strings.
     * Memory Usage: 42.9 MB, less than 35.82% of Java online submissions for Isomorphic Strings.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() == 0 && t.length() == 0) return true;
        if (s.length() != t.length()) return false;

        Map<Character, Character> cache = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (cache.containsKey(sc)){
                if (cache.get(sc) != tc) return false;
            }else {
                if (cache.values().contains(tc)) return false;
                cache.put(sc, tc);
            }

        }
        return true;
    }
}
