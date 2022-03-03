package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0153 {

    /**
     * 153. Find minimum in Rotated Sorted Array
      */

    /**
     * Solution
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
     * Memory Usage: 42.8 MB, less than 18.23% of Java online submissions for Find Minimum in Rotated Sorted Array.
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int left = 0;
        int right = nums.length -1;
        while (left < right) {
            if (left + 1 == right) return Math.min(nums[left], nums[right]);
            int pivot = (left + right)/2;
            if (nums[right] > nums[left]){
                right = pivot;
                continue;
            }

            if (nums[pivot] > nums[left]){
                left = pivot;
                continue;
            }

            right = pivot;
            continue;

        }
        return Math.min(nums[left], nums[right]);
    }
}
