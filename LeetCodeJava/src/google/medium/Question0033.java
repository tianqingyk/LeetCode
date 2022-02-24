package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/24
 */
public class Question0033 {

    /**
     * 33. Search in Rotated Sorted Array
     *
     * There is an integer array nums sorted in ascending order (with distinct values).
     *
     * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     *
     * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 63.09% of Java online submissions for Search in Rotated Sorted Array.
     * Memory Usage: 42.4 MB, less than 31.49% of Java online submissions for Search in Rotated Sorted Array.
     */
    public int search(int[] nums, int target) {
        if (nums.length < 2){
            if(target == nums[0]) return 0;
            return -1;
        }
        int first = nums[0];
        int end = nums[nums.length - 1];
        if (target < first && target > end) return -1;

        if (target < first) {
            for (int i = nums.length - 1; i > 0 ; i--) {
                if (target == nums[i]) return i;
                if (target > nums[i] || nums[i] < nums[i-1]) return -1;
            }
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) return i;
            if (target < nums[i] || nums[i] > nums[i+1]) return -1;
        }
        return -1;
    }
}
