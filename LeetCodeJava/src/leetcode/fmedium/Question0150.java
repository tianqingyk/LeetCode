package leetcode.fmedium;

import java.util.*;
import java.util.function.BiFunction;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0150 {

    /**
     * 150. Evaluate Reverse Polish Notation
     */


    /**
     * Solution 1
     * Runtime: 8 ms, faster than 62.67% of Java online submissions for Evaluate Reverse Polish Notation.
     * Memory Usage: 45.6 MB, less than 5.15% of Java online submissions for Evaluate Reverse Polish Notation.
     */
    static Set<String> operators = new HashSet<>();

    static {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (!operators.contains(token)) {
                stack.add(token);
                continue;
            }

            int num2 = Integer.valueOf(stack.pop());
            int num1 = Integer.valueOf(stack.pop());

            if (tokens[i].equals("+")) {
                num1 += num2;

            } else if (tokens[i].equals("-")) {
                num1 -= num2;
            } else if (tokens[i].equals("*")) {
                num1 *= num2;
            } else {
                num1 /= num2;
            }

            stack.add(num1 + "");
        }

        return Integer.valueOf(stack.pop());
    }

    /**
     * Solution 2 Copy From Solution
     * Evaluate with Stack
     */

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    // Ensure this only gets done once for ALL test cases.
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN2(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();

            int result = 0;

            switch (token) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    result = number1 / number2;
                    break;
            }

            stack.push(result);

        }

        return stack.pop();
    }

}
