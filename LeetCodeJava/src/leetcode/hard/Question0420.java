package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0420 {

    /**
     * 420. Strong Password Checker
     * 6-20
     * 1 lowercase  1 uppercase 1 digit
     * not contain 3 repeating characters
     */


    /**
     * Solution
     * Runtime: 14 ms, faster than 6.45% of Java online submissions for Strong Password Checker.
     * Memory Usage: 42.2 MB, less than 17.34% of Java online submissions for Strong Password Checker.
     */
    String password;
    int len = 0;
    int minResult = Integer.MAX_VALUE;
    public int strongPasswordChecker(String password) {
        if (password.length() < 3) return 6 - password.length();
        this.password = password;
        this.len = password.length();
        recursive(0, null, 0, 0, 0, 0);
        return minResult;
    }

    public void recursive(int index, Character pre, int count, int replace, int remove, int insert) {
        if (index >= password.length()) {
            if (len + insert < 6) insert = Math.max(insert, 6 - len);
            else if (len - remove > 20) remove = Math.max(remove, len - 20);

            if (replace + insert >= 3) {
                minResult = Math.min(minResult, replace + insert + remove);
                return;
            }

            int isDigit = 1;
            int isLower = 1;
            int isUpper = 1;
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isDigit(c)) isDigit = 0;
                if (Character.isLowerCase(c)) isLower = 0;
                if (Character.isUpperCase(c)) isUpper = 0;
            }
            minResult = Math.min(minResult, remove + Math.max(isDigit + isLower + isUpper, replace + insert));
            return;
        }
        char cur = password.charAt(index);
        if (pre == null || cur != pre) {
            recursive(index + 1, cur, 1, replace, remove, insert);
            return;
        }
        count++;
        if (count == 3) {
            if (len - remove > 20) {
                //Remove
                recursive(index + 1, cur, count - 1, replace, remove + 1, insert);
                //Replace
                recursive(index + 1, null, 0, replace + 1, remove, insert);
            } else if (len + insert < 6) {
                //Insert
                recursive(index + 1, null, 0, replace, remove, insert + 1);
            } else {
                //Replace
                recursive(index + 1, null, 0, replace + 1, remove, insert);
            }
        }else {
            recursive(index+1, cur, count, replace, remove, insert);
        }
    }

    public static void main(String[] args) {
        Question0420 q = new Question0420();
        System.out.println(q.strongPasswordChecker("aaaabbbbccccddeeddeeddeedd"));
    }
}
