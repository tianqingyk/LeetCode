package leetcode.hard;

import com.sun.source.tree.IfTree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0410 {

    /**
     * 410. Split Array Largest Sum
     */

    /**
     * Solution
     * Runtime: 38 ms, faster than 18.99% of Java online submissions for Split Array Largest Sum.
     * Memory Usage: 39.8 MB, less than 74.82% of Java online submissions for Split Array Largest Sum.
     */
    int[] sumN, nums;
    int len;
    int average;

    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;
        this.nums = nums;
        len = nums.length;
        sumN = new int[len];
        sumN[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sumN[i] += sumN[i - 1] + nums[i];
        }
        if (m == 1) return sumN[len - 1];
        average = sumN[len - 1] / m;

        int[][] dp = new int[m][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = sumN[i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = i; j < len; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    min = Math.min(min, Math.max(dp[i - 1][k], sumN[j] - sumN[k]));
                }
                dp[i][j] = min;
            }
        }

        return dp[m - 1][len - 1];
    }

    /**
     * Solution 2 Copy From Solution
     * Top-Down Dynamic Programming
     */

    // Defined it as per the maximum size of array and split count
    // But can be defined with the input size as well
    Integer[][] memo = new Integer[1001][51];

    private int getMinimumLargestSplitSum(int[] prefixSum, int currIndex, int subarrayCount) {
        int n = prefixSum.length - 1;

        // We have already calculated the answer so no need to go into recursion
        if (memo[currIndex][subarrayCount] != null) {
            return memo[currIndex][subarrayCount];
        }

        // Base Case: If there is only one subarray left, then all of the remaining numbers
        // must go in the current subarray. So return the sum of the remaining numbers.
        if (subarrayCount == 1) {
            return memo[currIndex][subarrayCount] = prefixSum[n] - prefixSum[currIndex];
        }

        // Otherwise, use the recurrence relation to determine the minimum largest subarray
        // sum between currIndex and the end of the array with subarrayCount subarrays remaining.
        int minimumLargestSplitSum = Integer.MAX_VALUE;
        for (int i = currIndex; i <= n - subarrayCount; i++) {
            // Store the sum of the first subarray.
            int firstSplitSum = prefixSum[i + 1] - prefixSum[currIndex];

            // Find the maximum subarray sum for the current first split.
            int largestSplitSum = Math.max(firstSplitSum,
                    getMinimumLargestSplitSum(prefixSum, i + 1, subarrayCount - 1));

            // Find the minimum among all possible combinations.
            minimumLargestSplitSum = Math.min(minimumLargestSplitSum, largestSplitSum);

            if (firstSplitSum >= minimumLargestSplitSum) {
                break;
            }
        }

        return memo[currIndex][subarrayCount] = minimumLargestSplitSum;
    }

    public int splitArray2(int[] nums, int m) {
        // Store the prefix sum of nums array.
        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        return getMinimumLargestSplitSum(prefixSum, 0, m);
    }

    /**
     * Solution 3
     * Bottom-Up Dynamic Programming
     */
    // Defined it as per the maximum size of array and split count
    // But can be defined with the input size as well
    public int splitArray3(int[] nums, int m) {
        int n = nums.length;

        // Store the prefix sum of nums array
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int subarrayCount = 1; subarrayCount <= m; subarrayCount++) {
            for (int currIndex = 0; currIndex < n; currIndex++) {
                // Base Case: If there is only one subarray left, then all of the remaining numbers
                // must go in the current subarray. So return the sum of the remaining numbers.
                if (subarrayCount == 1) {
                    memo[currIndex][subarrayCount] = prefixSum[n] - prefixSum[currIndex];
                    continue;
                }

                // Otherwise, use the recurrence relation to determine the minimum largest subarray
                // sum between currIndex and the end of the array with subarrayCount subarray remaining.
                int minimumLargestSplitSum = Integer.MAX_VALUE;
                for (int i = currIndex + 1; i <= n - subarrayCount + 1; i++) {
                    // Store the sum of the first subarray.
                    int firstSplitSum = prefixSum[i] - prefixSum[currIndex];

                    // Find the maximum subarray sum for the current first split.
                    int largestSplitSum = Math.max(firstSplitSum, memo[i][subarrayCount - 1]);

                    // Find the minimum among all possible combinations.
                    minimumLargestSplitSum = Math.min(minimumLargestSplitSum, largestSplitSum);

                    if (firstSplitSum >= minimumLargestSplitSum) {
                        break;
                    }
                }

                memo[currIndex][subarrayCount] = minimumLargestSplitSum;
            }
        }

        return memo[0][m];
    }

    /**
     * Solution 3
     * Binary Search
     */

    private int minimumSubarraysRequired(int[] nums, int maxSumAllowed) {
        int currentSum = 0;
        int splitsRequired = 0;

        for (int element : nums) {
            // Add element only if the sum doesn't exceed maxSumAllowed
            if (currentSum + element <= maxSumAllowed) {
                currentSum += element;
            } else {
                // If the element addition makes sum more than maxSumAllowed
                // Increment the splits required and reset sum
                currentSum = element;
                splitsRequired++;
            }
        }

        // Return the number of subarrays, which is the number of splits + 1
        return splitsRequired + 1;
    }

    public int splitArray4(int[] nums, int m) {
        // Find the sum of all elements and the maximum element
        int sum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int element : nums) {
            sum += element;
            maxElement = Math.max(maxElement, element);
        }

        // Define the left and right boundary of binary search
        int left = maxElement;
        int right = sum;
        int minimumLargestSplitSum = 0;
        while (left <= right) {
            // Find the mid value
            int maxSumAllowed = left + (right - left) / 2;

            // Find the minimum splits. If splitsRequired is less than
            // or equal to m move towards left i.e., smaller values
            if (minimumSubarraysRequired(nums, maxSumAllowed) <= m) {
                right = maxSumAllowed - 1;
                minimumLargestSplitSum = maxSumAllowed;
            } else {
                // Move towards right if splitsRequired is more than m
                left = maxSumAllowed + 1;
            }
        }

        return minimumLargestSplitSum;
    }
}
