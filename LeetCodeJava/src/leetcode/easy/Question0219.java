package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/7/2022
 */
public class Question0219 {

    /**
     * 219. Contians Duplicate II
     */

    /**
     * Solution
     * Runtime: 40 ms, faster than 50.64% of Java online submissions for Contains Duplicate II.
     * Memory Usage: 94.6 MB, less than 41.12% of Java online submissions for Contains Duplicate II
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (cache.containsKey(num)) {
                int index = cache.get(num);
                if (i - index <= k) return true;
            }else cache.put(num,i);
        }
        return false;
    }
}
