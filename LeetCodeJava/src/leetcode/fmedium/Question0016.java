package leetcode.fmedium;

import java.util.Arrays;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0016 {

    /**
     * 16. 3Sum Closet
     *
     * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
     *
     * Return the sum of the three integers.
     *
     * You may assume that each input would have exactly one solution.
     */

    /**
     * Solution 1
     * Runtime: 12 ms, faster than 40.14% of Java online submissions for 3Sum Closest.
     * Memory Usage: 43.2 MB, less than 15.47% of Java online submissions for 3Sum Closest.
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3){
            return 0;
        }

        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i-1] != nums[i]){
                int v = twoSum(nums, i, target);
                if (Math.abs(result - target) > Math.abs(v - target))
                    result = v;
            }
        }
        return result;
    }

    private int twoSum(int[] nums, int i, int target) {
        int lo = i + 1, hi = nums.length - 1;
        int result = nums[0] + nums[1] + nums[2];
        while (lo < hi) {
            int v = nums[i] + nums[lo] + nums[hi];
            if (v == target) return v;
            if (v > target){
                hi--;
            }
            if (v < target){
                lo++;
            }
            if (Math.abs(result - target) > Math.abs(v - target))
                result = v;
        }
        return result;
    }

    public static void main(String[] args) {
        Question0016 q = new Question0016();
        q.threeSumClosest(new int[]{1,-1,-1,3}, -1);
    }
}
