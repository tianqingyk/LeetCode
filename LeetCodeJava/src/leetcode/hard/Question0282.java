package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/10
 */
public class Question0282 {

    /**
     * 282. Expression Add Operators
     * <p>
     * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary
     * operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target
     * value.
     * <p>
     * Note that operands in the returned expressions should not contain leading zeros.
     */

    public List<String> answer;
    public String digits;
    public long target;

    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) {
            return new ArrayList<String>();
        }

        this.target = target;
        this.digits = num;
        this.answer = new ArrayList<>();
        this.recurse(0, 0, 0, 0, new ArrayList<>());
        return this.answer;
    }

    public void recurse(int index, long previousOperand, long currentOperand, long value, List<String> ops) {
        String nums = this.digits;

        // Done processing all the digits in num
        if (index == nums.length()) {
            // If the final value == target expected AND
            // no operand is left unprocessed
            if (value == this.target && currentOperand == 0) {
                StringBuilder sb = new StringBuilder();
                ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                this.answer.add(sb.toString());
            }
            return;
        }

        //Extending the current operand by one digit
        currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
        String current_val_rep = Long.toString(currentOperand);
        int length = nums.length();

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a valid operand.
        if (currentOperand > 0) {
            // 1. NO OP recursion
            recurse(index + 1, previousOperand, currentOperand, value, ops);
        }

        // 2. ADDITION
        ops.add("+");
        ops.add(current_val_rep);
        recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        if (ops.size() > 0) { // Because it is the first number when ops.size is 0;
            //3. Subtraction
            ops.add("-");
            ops.add(current_val_rep);
            recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // 4. MULTIPLICATION
            ops.add("*");
            ops.add(current_val_rep);
            recurse(index + 1, currentOperand * previousOperand, 0, value - previousOperand + (currentOperand * previousOperand), ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }
    }

    public static void main(String[] args) {

    }
}
