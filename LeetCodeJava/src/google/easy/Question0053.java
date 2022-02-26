package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/25
 */
public class Question0053 {

    /**
     * 53. Maximum Subarray
     * <p>
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * A subarray is a contiguous part of an array.
     */

    /**
     * Solution 1
     * Runtime: 4 ms, faster than 11.77% of Java online submissions for Maximum Subarray.
     * Memory Usage: 74.7 MB, less than 52.16% of Java online submissions for Maximum Subarray.
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) dp[i] = nums[i];
            else dp[i] = dp[i - 1] + nums[i];

            if (max <= dp[i]) {
                max = dp[i];
                maxIndex = i;
            }

            if (min > dp[i]) {
                min = dp[i];
                minIndex = i;
            }
        }

        if (maxIndex > minIndex) return Math.max(max - min, max);

        int minM = Integer.MAX_VALUE;
        for (int i = 0; i < maxIndex; i++) {
            minM = Math.min(minM, dp[i]);
        }

        int result = max;
        for (int i = maxIndex + 1; i < nums.length; i++) {
            result = Math.max(result, dp[i] - minM);
            minM = Math.min(minM, dp[i]);
        }
        return result;
    }

    /**
     * Solution 2 Copy From Solution
     * Optimized Brute Force
     */
    public int maxSubArray2(int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            for (int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }

        return maxSubarray;
    }

    /**
     * Solution 3 Copy From Solution
     * Dynamic Programming Kadane's Algorithm (genius!!!!!)
     */

    public int maxSubArray3(int[] nums) {
        // Initialize our variables using the first element.
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];

        // Start with the 2nd element since we already used the first one.
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
            currentSubarray = Math.max(num, currentSubarray + num);
            maxSubarray = Math.max(maxSubarray, currentSubarray);
        }

        return maxSubarray;
    }

    /**
     * Solution 4 Copy From Solution
     * Divide and Conquer(Advanced)
     */
    private int[] numsArray;

    public int maxSubArray4(int[] nums) {
        numsArray = nums;

        // Our helper function is designed to solve this problem for
        // any array - so just call it using the entire input!
        return findBestSubarray(0, numsArray.length - 1);
    }

    private int findBestSubarray(int left, int right) {
        // Base case - empty array.
        if (left > right) {
            return Integer.MIN_VALUE;
        }

        int mid = Math.floorDiv(left + right, 2);
        int curr = 0;
        int bestLeftSum = 0;
        int bestRightSum = 0;

        // Iterate from the middle to the beginning.
        for (int i = mid - 1; i >= left; i--) {
            curr += numsArray[i];
            bestLeftSum = Math.max(bestLeftSum, curr);
        }

        // Reset curr and iterate from the middle to the end.
        curr = 0;
        for (int i = mid + 1; i <= right; i++) {
            curr += numsArray[i];
            bestRightSum = Math.max(bestRightSum, curr);
        }

        // The bestCombinedSum uses the middle element and the best
        // possible sum from each half.
        int bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;

        // Find the best subarray possible from both halves.
        int leftHalf = findBestSubarray(left, mid - 1);
        int rightHalf = findBestSubarray(mid + 1, right);

        // The largest of the 3 is the answer for any given input array.
        return Math.max(bestCombinedSum, Math.max(leftHalf, rightHalf));
    }


}
