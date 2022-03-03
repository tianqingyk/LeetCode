package google.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0155 {

    /**
     * 155. Min Stack
     */

    /**
     * Solution
     * Runtime: 7 ms, faster than 65.21% of Java online submissions for Min Stack.
     * Memory Usage: 43.9 MB, less than 85.89% of Java online submissions for Min Stack.
     */
    class MinStack {

        LinkedList<Integer> nums;
        int min = Integer.MAX_VALUE;
        public MinStack() {
            this.nums = new LinkedList<>();
        }

        public void push(int val) {
            min = Math.min(min, val);
            nums.add(val);
        }

        public void pop() {
            int removed = nums.removeLast();
            if (removed == min) {
                if (nums.isEmpty()) min = Integer.MAX_VALUE;
                else min = nums.stream().min(Integer::compare).get();
            }
            return;
        }

        public int top() {
            return nums.getLast();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * Solution 2 Copy From Solution
     */
    class MinStack2 {

        private Stack<int[]> stack = new Stack<>();

        public MinStack2() { }


        public void push(int x) {

            /* If the stack is empty, then the min value
             * must just be the first value we add. */
            if (stack.isEmpty()) {
                stack.push(new int[]{x, x});
                return;
            }

            int currentMin = stack.peek()[1];
            stack.push(new int[]{x, Math.min(x, currentMin)});
        }


        public void pop() {
            stack.pop();
        }


        public int top() {
            return stack.peek()[0];
        }


        public int getMin() {
            return stack.peek()[1];
        }
    }

}
