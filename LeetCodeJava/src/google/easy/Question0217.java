package google.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/7/2022
 */
public class Question0217 {

    /**
     * 217.Contains Duplicate
     */

    /**
     * Solution
     * Runtime: 9 ms, faster than 81.09% of Java online submissions for Contains Duplicate.
     * Memory Usage: 70.4 MB, less than 40.71% of Java online submissions for Contains Duplicate.
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
