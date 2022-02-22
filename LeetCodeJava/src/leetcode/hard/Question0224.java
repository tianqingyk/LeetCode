package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0224 {

    /**
     * Basic Calculator
     * <p>
     * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return
     * result of the evaluation.
     * <p>
     * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions,
     * such as eval();
     */

    /**
     * Solution 1
     * Runtime: 395 ms, faster than 5.50% of Java online submissions for Basic Calculator.
     * Memory Usage: 50.2 MB, less than 12.53% of Java online submissions for Basic Calculator.
     * @param s
     * @return
     */
    public int calculate(String s) {
        return calculate(s, 0, s.length());
    }

    public int calculate(String s, int start, int end) {
        int result = 0;
        int operation = 1;

        int pStart = start;
        int pOperation = 1;
        int cal = 0;

        int num = 0;
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);


            if (c == '(') {
                if(cal == 0) {
                    pOperation = operation;
                    pStart = i;
                }
                cal++;
                continue;
            }

            if (c == ')') {
                cal--;
                if (cal == 0) {
                    int res = calculate(s, pStart + 1, i);
                    result += pOperation * res;
                }
                continue;
            }

            if(cal > 0) {
                continue;
            }

            if (c == '+') {
                operation = 1;
            } else if (c == '-') {
                operation = -1;
            }


            if(c >= '0' && c <= '9') {
                int v = Integer.valueOf(String.valueOf(c));
                if (num != 0) {
                    num = num * 10 + v;
                }else {
                    num = v;
                }
                if(i >= end - 1 || (s.charAt(i+1) < '0' || s.charAt(i+1) > '9')){
                    result += operation * num;
                    num = 0;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Question0224 q = new Question0224();
        q.calculate("1+(4+5+2)");
    }
}
