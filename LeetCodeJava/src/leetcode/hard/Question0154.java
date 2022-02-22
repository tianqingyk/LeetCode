package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0154 {

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
     *
     * [4,5,6,7,0,1,4] if it was rotated 4 times.
     * [0,1,4,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     *
     * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
     *
     * You must decrease the overall operation steps as much as possible.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array II.
     * Memory Usage: 38.9 MB, less than 43.34% of Java online submissions for Find Minimum in Rotated Sorted Array II.
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[i-1]){
                return nums[i];
            }
        }

        return nums[0];
    }
}
