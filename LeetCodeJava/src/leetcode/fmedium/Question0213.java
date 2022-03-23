package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/4
 */
public class Question0213 {

    /**
     * 213. House Robber II
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
     * Memory Usage: 41.8 MB, less than 16.94% of Java online submissions for House Robber II.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        int[][] dpRob0 = new int[len][2]; // 0 is not rob, 1 is rob
        int[][] dpNotRob0 = new int[len][2];

        dpRob0[0][0] = nums[0];
        dpRob0[0][1] = nums[0];

        for (int i = 1; i < len - 1; i++) {
            dpNotRob0[i][0] = Math.max(dpNotRob0[i - 1][0], dpNotRob0[i - 1][1]);
            dpNotRob0[i][1] = dpNotRob0[i - 1][0] + nums[i];
            if (i == 1) {
                dpRob0[i][0] = dpRob0[0][1];
                dpRob0[i][1] = dpRob0[0][1];
                continue;
            }
            dpRob0[i][0] = Math.max(dpRob0[i - 1][0], dpRob0[i - 1][1]);
            dpRob0[i][1] = dpRob0[i - 1][0] + nums[i];
        }

        dpRob0[len - 1][0] = Math.max(dpRob0[len - 2][0], dpRob0[len - 2][1]);
        dpNotRob0[len - 1][0] = Math.max(dpNotRob0[len - 2][0], dpNotRob0[len - 2][1]);
        dpNotRob0[len - 1][1] = dpNotRob0[len - 2][0] + nums[len - 1];
        return Math.max(Math.max(dpNotRob0[len - 1][0], dpNotRob0[len - 1][1]), dpRob0[len - 1][0]);
    }

    /**
     * Solution 2 Copy From Solution
     */

    public int rob2(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int max1 = rob_simple(nums, 0, nums.length - 2);
        int max2 = rob_simple(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    public int rob_simple(int[] nums, int start, int end) {
        int t1 = 0;
        int t2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = t1;
            int current = nums[i];
            t1 = Math.max(current + t2, t1);
            t2 = temp;
        }

        return t1;
    }

}
