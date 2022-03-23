package leetcode.fmedium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/22
 */
public class Question0012 {

    /**
     * 12. Integer to Roman
     *
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
     * Given an integer, convert it to a roman numeral.
     */

    /**
     * Solution 1
     * Runtime: 4 ms, faster than 91.34% of Java online submissions for Integer to Roman.
     * Memory Usage: 41.8 MB, less than 74.31% of Java online submissions for Integer to Roman.
     */
    Map<Integer, char[]> cache = new HashMap<>();
    public String intToRoman(int num) {
        cache.put(1, new char[]{'I', 'V', 'X'});
        cache.put(10, new char[]{'X', 'L', 'C'});
        cache.put(100, new char[]{'C', 'D', 'M'});
        StringBuilder sb = new StringBuilder();

        if (num >= 1000){
            for (int i = 0; i < num/1000; i++) {
                sb.append('M');
            }
            num %= 1000;
        }
        getRoman(num/100, cache.get(100), sb);
        num %= 100;
        getRoman(num/10, cache.get(10), sb);
        num %= 10;
        getRoman(num, cache.get(1), sb);
        return sb.toString();
    }

    private void getRoman(int num, char[] romans,StringBuilder sb) {
        if (num < 1) {
            return;
        }
        if (num < 4) {
            for (int i = 0; i < num; i++) {
                sb.append(romans[0]);
            }
        }else if (num == 4) {
            sb.append(romans[0]).append(romans[1]);
        }else if (num == 9) {
            sb.append(romans[0]).append(romans[2]);
        }else {
            sb.append(romans[1]);
            for (int i = 0; i < num - 5; i++) {
                sb.append(romans[0]);
            }
        }
    }

    /**
     * Solution 2 Copy from solution
     */
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num > 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
