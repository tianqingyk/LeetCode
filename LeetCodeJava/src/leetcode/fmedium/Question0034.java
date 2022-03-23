package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/24
 */
public class Question0034 {

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
     * <p>
     * If target is not found in the array, return [-1, -1].
     * <p>
     * You must write an algorithm with O(log n) runtime complexity.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
     * Memory Usage: 47.4 MB, less than 14.59% of Java online submissions for Find First and Last Position of Element in Sorted Array.
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) return new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        int key = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                key = mid;
                break;
            }

            if (left == mid) {
                if (nums[right] == target) {
                    key = right;
                }
                break;
            }

            if (nums[mid] > target) {
                right = mid;
                continue;
            }
            left = mid;
            continue;
        }
        if (key == -1) return new int[]{key, key};

        left = key;
        while (left > 0 && nums[left - 1] == target)
            left--;

        right = key;
        while (right < nums.length - 1 && nums[right + 1] == target)
            right++;
        return new int[]{left, right};
    }

    public static void main(String[] args) {
        Question0034 q = new Question0034();
        q.searchRange(new int[]{1}, 1);
    }
}
