package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/22
 */
public class Question0009 {

    /**
     * 9. Palindrome Number
     *
     * Given an integer x, return true if x is palindrome integer.
     *
     * An integer is a palindrome when it reads the same backward as forward.
     *      - For example, 121 is a palindrome while 123 is not.
     */

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int l = s.length();
        for (int i = 0; i < l/2; i++) {
            if (s.charAt(i) != s.charAt(l - 1 - i)){
                return false;
            }
        }
        return true;
    }
}
