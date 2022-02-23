package google.medium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0017 {
    /**
     * 17. Letter Combinations of a Phone Number
     * <p>
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
     * <p>
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     */

    /**
     * Solution 1
     * Runtime: 8 ms, faster than 27.77% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 43.4 MB, less than 6.70% of Java online submissions for Letter Combinations of a Phone
     */
    public static final Map<Character, char[]> digitsMap = new HashMap<>();
    static {
        digitsMap.put('2', new char[]{'a', 'b', 'c'});
        digitsMap.put('3', new char[]{'d', 'e', 'f'});
        digitsMap.put('4', new char[]{'g', 'h', 'i'});
        digitsMap.put('5', new char[]{'j', 'k', 'l'});
        digitsMap.put('6', new char[]{'m', 'n', 'o'});
        digitsMap.put('7', new char[]{'p', 'q', 'r', 's'});
        digitsMap.put('8', new char[]{'t', 'u', 'v'});
        digitsMap.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        return letterCombinationsHelper(digits, 0);
    }

    private List<String> letterCombinationsHelper(String digits, int start) {
        List<String> result = new ArrayList<>();
        if (start > digits.length() - 1) return result;

        char cur = digits.charAt(start);
        if (!digitsMap.containsKey(cur)) return result;

        char[] letters = digitsMap.get(cur);
        if (start == digits.length() - 1) {
            for (char c : letters) {
                result.add(String.valueOf(c));
            }
            return result;
        }

        List<String> strs = letterCombinationsHelper(digits, start + 1);
        for (char c : letters) {
            for (String str : strs) {
                result.add(c + str);
            }
        }
        return result;
    }
}
