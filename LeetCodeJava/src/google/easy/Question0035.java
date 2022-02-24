package google.easy;

import java.lang.invoke.LambdaForm$Holder;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/24
 */
public class Question0035 {

    /**
     * 35. Search Insert Position
     * <p>
     * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * <p>
     * You must write an algorithm with O(log n) runtime complexity.
     */


    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
     * Memory Usage: 43.7 MB, less than 12.52% of Java online submissions for Search Insert Position.
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) return 0;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            if (mid == left){
                if (target > nums[right]) return  right + 1;
                if (target == nums[right]) return right;
                if (target > nums[left]) return right;
                return left;
            }

            if (target < nums[mid]){
                right = mid;
                continue;
            }
            left = mid;
            continue;
        }
        return 0;
    }
}
