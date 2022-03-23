package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0152 {

    /**
     * 152. Maximum Product Subarray
     */

    /**
     * Solution
     * Runtime: 3 ms, faster than 41.09% of Java online submissions for Maximum Product Subarray.
     * Memory Usage: 45.6 MB, less than 6.21% of Java online submissions for Maximum Product Subarray.
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length < 1) return 0;

        int maxProduct = Integer.MIN_VALUE;
        Integer max = null;
        Integer min = null;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (max == null) {
                max = num;
                min = num;
                maxProduct = Math.max(max, maxProduct);
                continue;
            }

            if (num == 0) {
                maxProduct = Math.max(0, maxProduct);
                max = null;
                min = null;
                continue;
            }
            if (num > 0) {
                max = Math.max(max * num, num);
                min = Math.min(min, min * num);
                maxProduct = Math.max(max, maxProduct);
                continue;
            }

            int maxTmp = max;
            max = Math.max(min * num, num);
            min = Math.min(maxTmp * num, num);
            maxProduct = Math.max(max, maxProduct);
        }

        return maxProduct;
    }

    /**
     * Solution 2 Copy From Solution
     */

    public int maxProduct2(int[] nums) {
        if (nums.length == 0) return 0;

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));
            min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));

            max_so_far = temp_max;

            result = Math.max(max_so_far, result);
        }

        return result;
    }
}
