package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0055 {

    /**
     * 55. Jump Game
     * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
     *
     * Return true if you can reach the last index, or false otherwise.
     */

    /**
     * Solution 1
     * Runtime: 143 ms, faster than 27.42% of Java online submissions for Jump Game.
     * Memory Usage: 68.1 MB, less than 23.65% of Java online submissions for Jump Game.
     */
    public boolean canJump(int[] nums) {
        int curPosition = 0;
        while (curPosition < nums.length - 1){
            int step = nums[curPosition];
            int maxJump = curPosition;
            int maxIndex = curPosition;
            for (int i = curPosition + 1; i <= curPosition + step; i++) {
                if (maxJump < i + nums[i]) {
                    maxJump = i + nums[i];
                    maxIndex = i;
                }
            }

            if (maxJump <= curPosition)
                return false;
            curPosition = maxIndex;
        }
        return true;
    }

    /**
     * Solution 2
     * Greedy
     */
    public boolean canJump2(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {
        Question0055 q = new Question0055();
        q.canJump(new int[] {5,9,3,2,1,0,2,3,3,1,0,0});
    }
}
