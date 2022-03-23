package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0026 {

    /**
     * 26. Remove Duplicates from Sorted Array
     * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
     * <p>
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
     * <p>
     * Return k after placing the final result in the first k slots of nums.
     * <p>
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * Custom Judge:
     * <p>
     * The judge will test your solution with the following code:
     * <p>
     * int[] nums = [...]; // Input array
     * int[] expectedNums = [...]; // The expected answer with correct length
     * <p>
     * int k = removeDuplicates(nums); // Calls your implementation
     * <p>
     * assert k == expectedNums.length;
     * for (int i = 0; i < k; i++) {
     * assert nums[i] == expectedNums[i];
     * }
     * If all assertions pass, then your solution will be accepted.
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 88.89% of Java online submissions for Remove Duplicates from Sorted Array.
     * Memory Usage: 47.8 MB, less than 20.09% of Java online submissions for Remove Duplicates from Sorted Array.
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                nums[count] = nums[i];
                count++;
            }
        }

        return count;
    }
}
