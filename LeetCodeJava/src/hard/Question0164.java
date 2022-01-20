package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0164 {
    /**
     * 164. Maximum Gap
     * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
     *
     * You must write an algorithm that runs in linear time and uses linear extra space.
     */

    public int maximumGap(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num  : nums) {
            list.add(num);
        }
        return maximumGap(list);
    }

    public int maximumGap(List<Integer> nums){
        if (nums.size() <= 1){
            return 0;
        }
        if (nums.size() <= 2){
            return Math.abs(nums.get(1) - nums.get(0));
        }

        int cp = nums.get(0);
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            int num = nums.get(i);
            if (num <= cp){
                leftList.add(num);
                leftMin = Math.min(leftMin, cp - num);
            }else{
                rightList.add(num);
                rightMin = Math.min(rightMin, num - cp);
            }
        }
        if (leftMin >= Integer.MAX_VALUE){
            leftMin = 0;
        }
        if (rightMin >= Integer.MAX_VALUE){
            rightMin = 0;
        }
        int max = Math.max(leftMin, rightMin);

        max = Math.max(max, Math.max(maximumGap(leftList), maximumGap(rightList)));
        return max;

    }

    public static void main(String[] args) {
        Question0164 q = new Question0164();
        q.maximumGap(new int[] {9,19});
    }
}
