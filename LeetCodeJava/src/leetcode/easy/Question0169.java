package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0169 {

    /**
     * 169. Majority Element
     */

    /**
     * Solution
     * Runtime: 10 ms, faster than 32.15% of Java online submissions for Majority Element.
     * Memory Usage: 56.1 MB, less than 35.91% of Java online submissions for Majority Element.
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int val = map.compute(nums[i], (k, v) -> v == null ? 1 : v+1);
            if (val > len/2) return nums[i];
        }
        return 0;
    }

}
