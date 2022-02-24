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
     * Memory Usage: 41.9 MB, less than 39.54% of Java online submissions for Search in Rotated Sorted Array.
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len < 2) {
            if (target == nums[0]) return 0;
            return -1;
        }
        return searchHelper(nums, target, 0, len - 1);
    }

    private int searchHelper(int[] nums, int target, int start, int end) {
        if (target < nums[start] && target > nums[end]) return -1;
        int mid = (start + end) / 2;

        if (start == end) {
            if (target == nums[start]) return start;
            return -1;
        }
        if (start == mid){
            if (target == nums[start]) return start;
            if (target == nums[end]) return end;
            return -1;
        }

        if (nums[start] < nums[mid]) {
            if (target >= nums[start] && target <= nums[mid]) {
                return searchHelper(nums, target, start, mid);
            }
            return searchHelper(nums, target, mid + 1, end);
        }

        if (target >= nums[mid + 1] && target <= nums[end]) {
            return searchHelper(nums, target, mid + 1, end);
        }
        return searchHelper(nums, target, start, mid);

    }
}
