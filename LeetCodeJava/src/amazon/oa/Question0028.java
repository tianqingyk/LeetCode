package amazon.oa;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/13/2022
 */
public class Question0028 {

    /**
     * 28. Valid Discount Coupons
     *
     * At Amazon's annual sale, employees are tasked with generating valid discount coupons for loyal customers.
     * However, there are some used/invalid coupons in the mix, and the challenge in this task is to determine
     * whether a given discount coupon is valid or not.
     *
     * The validity of discount coupon is determined as follows.
     *
     *  1.An empty discount coupon is valid.
     *  2.If a discount coupon A is valid, then a discount coupon C made by adding one character xto both the beginning of A
     *    and the end of A is also valid (i.e the discount coupon C = xAx is valid).
     *  3. If two discount coupons A and B are valid, then the concatenation of B and A is also valid (i.e the coupons
     *    AB and BA are both valid).
     *
     *  Given n discount coupons, each coupon consisting of only lowercase English characters,
     *  where the i-th discount coupon is denoted discounts[i], determine if each discount coupon is valid or not.
     *  A valid coupon is denoted by 1 in the answer array while an invalid coupon is denoted by 0.
     */

    public List<Integer> findDiscounts(List<String> discount) {

        List<Integer> result = new ArrayList<>();
        for (String s : discount) {
            if (s.equals("")) result.add(1);
            int left = 0;
            int right = 2;
            boolean isValid = false;
            while (right <= s.length()){
                if (isPalindrome(s.substring(left, right))) {
                    if (right == s.length()) isValid = true;
                    left = right;
                    right = left + 2;
                    continue;
                }
                right += 2;
            }
            if (isValid) result.add(1);
            else result.add(0);
        }
        return result;
    }

    private boolean isPalindrome(String s) {
        if (s.length()%2 != 0) return false;
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Question0028 q = new Question0028();
        List<Integer> result = q.findDiscounts(new ArrayList<>() {{
            add("abbaabcba");
            add("abca");
        }});
        System.out.println(result);
    }
}
