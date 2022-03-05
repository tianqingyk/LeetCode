package google.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/4
 */
public class Question0215 {

    /**
     * 215. Kth Largest Element in an Array
     */


    /**
     * Solution
     * Runtime: 5 ms, faster than 66.44% of Java online submissions for Kth Largest Element in an Array.
     * Memory Usage: 45.3 MB, less than 13.06% of Java online submissions for Kth Largest Element in an Array.
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 1) return nums[0];

        Queue<Integer> queue = new PriorityQueue<>(k);

        for (int i = 0; i < nums.length; i++) {
            if (queue.size() < k) queue.add(nums[i]);
            else if (queue.peek() < nums[i]){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {

    }
}
