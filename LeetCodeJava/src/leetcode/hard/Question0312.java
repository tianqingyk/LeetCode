package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0312 {

    /**
     * 312. Burst Balloons
     * <p>
     * You are given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by an array nums.
     * You are asked to burst all the balloons.
     * If you burst the ith balloon, you will get nums[i-1] * nums[i] * nums[i+1] coins. If i - 1 or i + 1 goes out of bounds
     * of the array, then treat it as if there is a balloon with a 1 painted on it.
     * <p>
     * Return the maximum coins you can collect by bursting the balloons wisely.
     */

    /**
     * Solution 1 copy from solution
     * Dynamic Programming(Top-Down)
     */

    public int maxCoins(int[] nums) {
        // add 1 before and after nums
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n-1] = 1;

        // cache the results of dp
        int[][] memo = new int[n][n];

        // We can not burst the first one and last one
        // Since they are both face balloons added by ourselves.
        return dp(memo, newNums, 1, n - 2);
    }

    public int dp(int[][] memo, int[] nums, int left, int right) {
        // return maximum if we burst all nums[left] .... nums[right], inclusive
        if (right - left < 0) {
            return 0;
        }

        // we've already seen this, return from cache.
        if (memo[left][right] > 0) {
            return memo[left][right];
        }

        //find the last burst one in nums[left]...nums[right]
        int result = 0;
        for (int i = left; i <= right; i++) {
            //nums[i] is the last burst one
            int gain = nums[left-1] * nums[i] * nums[right + 1];
            //nums[i] is fixed, recursively call left side and right side
            int remaining = dp(memo, nums, left, i-1) + dp(memo, nums, i+1, right);
            result = Math.max(result, remaining + gain);
        }
        // add to the cache
        memo[left][right] = result;
        return result;
    }

    /**
     * Solution 2 copy from solution
     * Dynamic Programming(Bottom-up)
     */

    public int maxCoins2(int[] nums) {
        // add 1 before and after nums
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n - 1] = 1;
        // dp[i][j] represents
        // maximum if we burst all nums[left]...nums[right], inclusive
        int[][] dp = new int[n][n];
        // do not include the first one and the last one
        // since they are both fake balloons added by ourselves and we can not burst
        // them
        for (int left = n - 2; left >= 1; left--) {
            for (int right = left; right <= n - 2; right++) {
                // find the last burst one in newNums[left]...newNums[right]
                for (int i = left; i <= right; i++) {
                    // newNums[i] is the last burst one
                    int gain = newNums[left - 1] * newNums[i] * newNums[right + 1];
                    // recursively call left side and right side
                    int remaining = dp[left][i - 1] + dp[i + 1][right];
                    // update
                    dp[left][right] = Math.max(remaining + gain, dp[left][right]);
                }
            }
        }
        // burst newNums[1]...newNums[n-2], excluding the first one and the last one
        return dp[1][n - 2];
    }
}
