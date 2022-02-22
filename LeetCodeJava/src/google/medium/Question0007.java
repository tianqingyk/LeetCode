package google.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/21
 */
public class Question0007 {

    /**
     * 7. Reverse Integer
     * <p>
     * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside
     * the signed 32-bit integer range [-2^31, 2^31 - 1]. then return 0.
     * <p>
     * Assume the environment does not allow you to store 64-bit integer(signed or unsigned).
     */

    /**
     * Solution 1
     * Runtime: 4 ms, faster than 19.45% of Java online submissions for Reverse Integer.
     * Memory Usage: 41.5 MB, less than 19.76% of Java online submissions for Reverse Integer.
     * @param x
     * @return
     */
    public int reverse(int x) {
        List<Integer> digits = new ArrayList<>();
        while (x >= 10 || x <= -10) {
            digits.add(x % 10);
            x /= 10;
        }
        digits.add(x);

        int result = 0;
        for (int i = 0; i < digits.size() - 1; i++) {
            result = 10 * result + digits.get(i);
        }

        int first = digits.get(digits.size() - 1);
        if (result > Integer.MAX_VALUE / 10
                || result < Integer.MIN_VALUE / 10
                || (result == Integer.MAX_VALUE / 10 &&  first > Integer.MAX_VALUE % 10)
                || (result == Integer.MIN_VALUE / 10 && first < Integer.MIN_VALUE % 10)) {
            return 0;
        }

        result = 10 * result + first;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(-12 % 10);
    }
}
