package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0069 {

    /**
     * 69. Sqrt(x)
     * Given a non-negative integer x, compute and return the square root of x.
     * <p>
     * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
     * <p>
     * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
     */

    /**
     * Solution 1
     * Runtime: 2 ms, faster than 77.23% of Java online submissions for Sqrt(x).
     * Memory Usage: 41.6 MB, less than 18.71% of Java online submissions for Sqrt(x).
     */
    public int mySqrt(int x) {
        if (x <= 1) return x;

        int left = 0;
        int right = x;
        while (left < right) {
            int mid = (left + right) / 2;
            int compare = x / mid;
            if (compare == mid) return mid;
            if (compare > mid) {
                if (left == mid) return left;
                left = mid;
                continue;
            }
            right = mid;
        }
        return (left + right) / 2;
    }

    /**
     * Solution 2
     * Newton's Method
     */
    public int mySqrt2(int x) {
        if (x < 2) return x;

        double x0 = x;
        double x1 = 0.5 * (x0 + x / x0);
        while (Math.abs(x1 - x0) >= 1) {
            x0 = x1;
            x1 = 0.5 * (x0 + x / x0);
        }
        return (int)x1;
    }

    public static void main(String[] args) {
        Question0069 q = new Question0069();
        q.mySqrt2(82389);
    }
}
