package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0162 {

    /**
     * 162. Find Peak Element
     */

    /**
     * Solution 1
     * O(n)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Peak Element.
     * Memory Usage: 43.2 MB, less than 16.86% of Java online submissions for Find Peak Element.
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] > nums[left+1]) return left;
            if (nums[right] > nums[right-1]) return right;
            left++;
            right--;
        }
        return left;
    }

    /**
     * Solution 2
     * Binary Search (O(logn))
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
