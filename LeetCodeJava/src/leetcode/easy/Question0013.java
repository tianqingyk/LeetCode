package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/22
 */
public class Question0013 {

    /**
     * 13. Roman to Integer
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given a roman numeral, convert it to an integer.
     */

    /**
     * Solution 1
     * Runtime: 11 ms, faster than 39.42% of Java online submissions for Roman to Integer.
     * Memory Usage: 46 MB, less than 26.96% of Java online submissions for Roman to Integer.
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        cache.put('I', 1);
        cache.put('V', 5);
        cache.put('X', 10);
        cache.put('L', 50);
        cache.put('C', 100);
        cache.put('D', 500);
        cache.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            result += cache.get(c);
            if (i < s.length() - 1){
                char nextC = s.charAt(i+1);
                if ((c == 'I' && (nextC == 'V' || nextC == 'X'))
                        || (c == 'X' && (nextC == 'L' || nextC == 'C'))
                        || (c == 'C' && (nextC == 'D' || nextC == 'M'))){
                    result -= 2 * cache.get(c);
                }
            }

        }
        return result;
    }
}
