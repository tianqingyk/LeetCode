package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/25
 */
public class Question0050 {

    /**
     * 50. Pow(x,n)
     * <p>
     * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
     */


    /**
     * 50. Pow(x, n)
     * Runtime: 1 ms, faster than 78.22% of Java online submissions for Pow(x, n).
     * Memory Usage: 43.3 MB, less than 18.55% of Java online submissions for Pow(x, n).
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n < 0) {
            return 1 / (x * myPow(x, -(n+1)));
        }
        if (x == 1) return 1;
        if (x == -1) return myPow(x, n % 2);
        double res = myPow(x, n / 2);
        return res * res * myPow(x, n % 2);
    }
}
