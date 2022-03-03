package google.easy;

import java.util.LinkedList;
import java.util.List;

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

}
