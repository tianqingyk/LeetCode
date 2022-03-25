package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/24/2022
 */
public class Question0081 {

    /**
     * 81. Search in Rotated Sorted Array II
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array II.
     * Memory Usage: 41.8 MB, less than 87.20% of Java online submissions for Search in Rotated Sorted Array II.
     */
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end) {
            int pivot = (begin + end) / 2;
            if (nums[pivot] == target || nums[begin] == target || nums[end] == target) return true;

            if (nums[pivot] == nums[begin] && nums[pivot] == nums[end]) {
                begin++;
                end--;
                continue;
            }

            // begin -> pivot -> end

            // case 1 pivot > target
            if (nums[pivot] > target) {
                if (target > nums[end] && target < nums[begin]) return false;

                if (nums[begin] < target) {
                    end = pivot - 1;
                    continue;
                }

                begin++;
                end--;
                continue;

            }
            // case 2 pivot < target
            if (nums[begin] > target && target > nums[end]) return false;

            if (nums[end] > target) {
                begin = pivot + 1;
                continue;
            }
            begin++;
            end--;
            continue;

        }

        return false;
    }

    /**
     * Solution 2 Copy From Solution
     */

    public boolean search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        int end = n - 1;
        int start = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (!isBinarySearchHelpful(nums, start, nums[mid])) {
                start++;
                continue;
            }
            // which array does pivot belong to.
            boolean pivotArray = existsInFirst(nums, start, nums[mid]);

            // which array does target belong to.
            boolean targetArray = existsInFirst(nums, start, target);
            if (pivotArray ^ targetArray) { // If pivot and target exist in different sorted arrays, recall that xor is true when both operands are distinct
                if (pivotArray) {
                    start = mid + 1; // pivot in the first, target in the second
                } else {
                    end = mid - 1; // target in the first, pivot in the second
                }
            } else { // If pivot and target exist in same sorted array
                if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    // returns true if we can reduce the search space in current binary search space
    private boolean isBinarySearchHelpful(int[] arr, int start, int element) {
        return arr[start] != element;
    }

    // returns true if element exists in first array, false if it exists in second
    private boolean existsInFirst(int[] arr, int start, int element) {
        return arr[start] <= element;
    }

}
