package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0233 {

    /**
     * 233. Number of Digit One
     *
     * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Digit One.
     * Memory Usage: 39 MB, less than 9.40% of Java online submissions for
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n <= 9){
            return 1;
        }

        int n1 = n/10;
        int re = n%10;

        int countN1 = 0;
        int tmpN1 = n1;
        while (tmpN1 >= 10) {
            int r = tmpN1%10;
            tmpN1 = tmpN1/10;
            if (r == 1){
                countN1++;
            }
        }
        if (tmpN1 == 1) {
            countN1++;
        }

        return countDigitOne(n1-1) * 10 + n1 + countDigitOne(re) + (re + 1) * countN1;
    }

    public static void main(String[] args) {
        Question0233 q = new Question0233();
        System.out.println(q.countDigitOne(10));
        System.out.println(q.countDigitOne(100));
        System.out.println(q.countDigitOne(1000));
    }
}
