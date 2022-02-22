package leetcode.hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/4/21
 */

public class Question0041 {
    /**
     * QUESTION 41
     * Given an unsorted integer array nums, return the smallest missing positive integer.
     * <p>
     * You must implement an algorithm that runs in O(n) time and uses constant extra space.
     */

    public int firstMissingPositive(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            int tmp = nums[i];
            if (tmp == i + 1) {
                continue;
            }

            nums[i] = 0;
            if (tmp < 1 || tmp > l) {
                nums[i] = 0;
                continue;
            }
            // 1 <= tmp <=l
            if (tmp < i + 1) {
                nums[tmp - 1] = tmp;
                continue;
            }

            putNextNum(tmp, nums);
        }

        for (int i = 0; i < l; i++) {
            if (nums[i] == 0) {
                return i + 1;
            }
        }
        return l+1;
    }

    private void putNextNum(int tmp, int[] nums) {
        int l = nums.length;
        int next = nums[tmp - 1];
        nums[tmp - 1] = tmp;
        if (next < 1 || next > l || next == tmp) {
            return;
        }
        putNextNum(next, nums);
    }

    public static void main(String[] args){
        Question0041 q = new Question0041();
        int[] nums = {2,2};
        System.out.println(q.firstMissingPositive(nums));;
    }
}
