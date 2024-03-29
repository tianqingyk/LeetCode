package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/21
 */
public class Question0008 {

    /**
     * 8. String to Integer(atoi)
     *
     * Implement the myAtoi(String s) function, which converts a string to a 32.bit signed integer (similar to C/C++'s atoi function).
     *
     * The algorithm for myAtoi(String s) is as follows:
     *
     * 1. Read in and ignore any leading whitespace.
     * 2. Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * 3. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * 4. Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * 5. If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * 6. Return the integer as the final result.
     *
     * Note:
     *
     * Only the space character ' ' is considered a whitespace character.
     * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for String to Integer (atoi).
     * Memory Usage: 41.9 MB, less than 27.69% of Java online submissions for String to Integer (atoi).
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int maxDivide10 = Integer.MAX_VALUE / 10;
        int maxMode10 = Integer.MAX_VALUE % 10;
        int minDivide10 = Integer.MIN_VALUE / 10;
        int minMode10 = Integer.MIN_VALUE % 10;
        s = s.trim();
        int op = 1;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && (c == '-' || c == '+')) {
                if (c == '-') op = -1;
                continue;
            }

            if (c >= '0' && c <= '9'){
                int v = Integer.valueOf(String.valueOf(c));
                int tmp = op * result;
                if (tmp > maxDivide10 || (tmp == maxDivide10 && v > maxMode10)) {
                    return Integer.MAX_VALUE;
                }

                if (tmp < minDivide10 || (tmp == minDivide10 && op * v < minMode10)) {
                    return Integer.MIN_VALUE;
                }
                result = 10 * result + v;
                continue;
            }
            break;
        }
        return result * op;
    }

    public static void main(String[] args) {
        Question0008 q = new Question0008();

        System.out.println(q.myAtoi(" -123123with djlfjalsdff "));
    }

}
