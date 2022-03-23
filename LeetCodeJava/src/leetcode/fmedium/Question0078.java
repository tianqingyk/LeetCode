package leetcode.fmedium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0078 {

    /**
     * 78. Subsets
     *
     * Given an integer array nums of unique elements, return all possible subsets (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     */

    /**
     * Solution 1
     * Runtime: 2 ms, faster than 40.14% of Java online submissions for Subsets.
     * Memory Usage: 44.1 MB, less than 5.82% of Java online submissions for Subsets.
     */

    List<List<Integer>> result = new ArrayList<>();
    int[] nums;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        recurse(0, new LinkedList<Integer>());
        return result;
    }

    private void recurse(int i, LinkedList<Integer> cache) {
        if (i > nums.length - 1) {
            result.add(new LinkedList<>(cache));
            return;
        }
        recurse(i+1, cache);

        cache.add(nums[i]);
        recurse(i+1, cache);
        cache.removeLast();
    }


}
