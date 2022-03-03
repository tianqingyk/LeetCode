package google.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0202 {

    /**
     * 202. Happy Number
     */

    /**
     * Solution
     * Runtime: 2 ms, faster than 62.96% of Java online submissions for Happy Number.
     * Memory Usage: 40.2 MB, less than 46.26% of Java online submissions for Happy Number.
     */
    Set<Integer> calculated = new HashSet<>();
    public boolean isHappy(int n) {
        calculated.add(n);
        String str = n +"";
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            int digit = Integer.valueOf(str.charAt(i) + "");
            result +=  digit * digit;
        }
        if (result == 1) return true;
        else {
            if (calculated.contains(result)) return false;
            return isHappy(result);
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
