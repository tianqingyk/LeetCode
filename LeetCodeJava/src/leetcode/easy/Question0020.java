package leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0020 {
    /**
     * 20. Valid Parentheses
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     */


    /**
     * Solution 1
     * Runtime: 3 ms, faster than 56.81% of Java online submissions for Valid Parentheses.
     * Memory Usage: 42 MB, less than 26.45% of Java online submissions for Valid Parentheses.
     */
    public final static Map<Character, int[]> map = new HashMap<>();
    static {
        map.put('(', new int[]{0, 1});
        map.put(')', new int[]{0, -1});
        map.put('[', new int[]{1, 1});
        map.put(']', new int[]{1, -1});
        map.put('{', new int[]{2, 1});
        map.put('}', new int[]{2, -1});
    }
    public boolean isValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int[] array = map.get(c);
            if (array[1] > 0) {
                stack.push(array[0]);
            }
            if (array[1] < 0) {
                if(stack.isEmpty()) return false;
                int v = stack.pop();
                if (v != array[0]) return false;
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }
}
