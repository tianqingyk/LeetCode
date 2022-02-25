package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/24
 */
public class Question0045 {

    /**
     * 45. Jump Game II
     *
     * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * You can assume that you can always reach the last index.
     */

    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int step = 1;
        int curPos = 0;
        while (curPos + nums[curPos] < nums.length - 1){
            int max = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int i = 1; i < nums[curPos]; i++) {
                if (max < nums[i] + i) {
                    max = nums[i] + i;
                    maxIndex = i;
                }
            }
            curPos = maxIndex;
            step++;
        }
        return step;
    }

}
