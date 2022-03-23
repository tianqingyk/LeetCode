package leetcode.fmedium;


/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/24
 */
public class Question0043 {

    /**
     * 43. Multiply Strings
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
     * <p>
     * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
     */

    /**
     * Solution 1
     * Runtime: 11 ms, faster than 46.71% of Java online submissions for Multiply Strings.
     * Memory Usage: 42.5 MB, less than 46.11% of Java online submissions for Multiply Strings.
     */
    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length() + num2.length()];

        char[] array1 = num1.toCharArray();
        char[] array2 = num2.toCharArray();
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int v = (array1[num1.length() - 1 - i] - '0') * (array2[num2.length() - 1 - j] - '0');
                if (v >= 10) {
                    int index = i + j + 1;
                    result[index] += v / 10;
                    while (index < result.length - 1 && result[index] >= 10) {
                        result[index] %= 10;
                        result[index + 1] += 1;
                        index++;
                    }
                }
                int index = i + j;
                result[index] += v % 10;
                while (index < result.length - 1 && result[index] >= 10) {
                    result[index] %= 10;
                    result[index + 1] += 1;
                    index++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = result.length - 1; i >= 0; i--) {
            if (sb.length() > 0 || result[i] > 0)
                sb.append(result[i]);
        }
        return sb.toString();
    }
}
