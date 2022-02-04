package hard;

import java.io.FileReader;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0239 {

    /**
     * 239. Sliding Window Maximum
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of
     * the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
     * by one position.
     *
     * Return the max sliding window.
     */

    /**
     * Solution 1
     * Runtime: 38 ms, faster than 64.45% of Java online submissions for Sliding Window Maximum.
     * Memory Usage: 140.6 MB, less than 6.59% of Java online submissions for Sliding Window Maximum.
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        if (k <= 1) {
            return nums;
        }

        int halfK = k/2;
        int[] maxHalfK = maxSlidingWindow(nums, halfK);

        for (int i = 0; i < result.length; i++) {
            result[i] = Math.max(maxHalfK[i], maxHalfK[i+halfK]);
            if (k > 2 * halfK) {
                result[i] = Math.max(result[i], nums[k+i-1]);
            }
        }

        return result;
    }
}
