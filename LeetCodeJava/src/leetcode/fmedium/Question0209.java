package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/4
 */
public class Question0209 {

    /**
     * 209. Minimum Size Subarray Sum
     */

    /**
     * Solution 1
     * Runtime: 78 ms, faster than 9.84% of Java online submissions for Minimum Size Subarray Sum.
     * Memory Usage: 42.4 MB, less than 51.27% of Java online submissions for Minimum Size Subarray Sum.
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length < 1) return 0;

        int min = Integer.MAX_VALUE;

        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        if (sums[0] >= target) return 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= target) return 1;
            sums[i] = sums[i - 1] + nums[i];
            if (sums[i] >= target) min = Math.min(min, i + 1);
        }

        for (int i = 2; i < min; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (sums[j + i] - sums[j] >= target) {
                    if (min > i) min = i;
                    else return min;
                }
            }
        }

        if (min == Integer.MAX_VALUE) min = 0;

        return min;
    }

    /**
     * Solution 2 Copy From Solution
     * Runtime: 1 ms, faster than 99.98% of Java online submissions for Minimum Size Subarray Sum.
     * Memory Usage: 45.2 MB, less than 13.64% of Java online submissions for Minimum Size Subarray Sum.
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= target) {
                min = Math.min(min, i + 1 - left);
                sum -= nums[left++];
            }
        }
        return (min == Integer.MAX_VALUE ? 0 : min);
    }

}
