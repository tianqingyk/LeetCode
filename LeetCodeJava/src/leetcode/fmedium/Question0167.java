package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0167 {

    /**
     * 167. Two Sum II - Input Array Is Sorted
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 88.55% of Java online submissions for Two Sum II - Input Array Is Sorted.
     * Memory Usage: 50.2 MB, less than 16.36% of Java online submissions for Two Sum II - Input Array Is Sorted.
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int pivot = (left + right) / 2;
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            if (sum > target) {
                if (numbers[left] + numbers[pivot] > target){
                    right = pivot;
                }else right--;
                continue;
            }
            if (sum < target) {
                if (numbers[pivot] + numbers[right] < target) left = pivot;
                else left++;
                continue;
            }
        }
        return new int[]{left + 1, right + 1};
    }
}
