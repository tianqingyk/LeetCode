package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/24
 */
public class Question0031 {

    /**
     * 31. Next Permutation
     * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
     *
     * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
     * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
     *
     * For example, the next permutation of arr = [1,2,3] is [1,3,2].
     * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
     * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
     * Given an array of integers nums, find the next permutation of nums.
     *
     * The replacement must be in place and use only constant extra memory.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Permutation.
     * Memory Usage: 42.4 MB, less than 42.08% of Java online submissions for Next Permutation.
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len < 2) return;
        nextPermutation(nums, len - 1);
        return;
    }

    private void nextPermutation(int[] nums, int index) {
        if (index == 0) {
            reverseNums(nums, index);
            return;
        }
        int before = nums[index - 1];
        if (before >= nums[index]) {
            nextPermutation(nums, index - 1);
            return;
        }

        for (int i = index + 1; i < nums.length; i++) {
            if (before >= nums[i]) {
                nums[index - 1] = nums[i - 1];
                nums[i - 1] = before;
                reverseNums(nums, index);
                return;
            }
        }
        nums[index - 1] = nums[nums.length - 1];
        nums[nums.length - 1] = before;
        reverseNums(nums, index);
        return;
    }

    private void reverseNums(int[] nums, int start) {
        for (int i = start; i < start + (nums.length - start) / 2; i++) {
            int reverseI = nums.length - 1 - i + start;
            int tmp = nums[i];
            nums[i] = nums[reverseI];
            nums[reverseI] = tmp;
        }
    }

    /**
     * Solution 2 Copy From Solution
     */

    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
