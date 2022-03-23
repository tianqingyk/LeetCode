package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/8/2022
 */
public class Question0228 {

    /**
     * 228. Summary Ranges
     */

    /**
     * Solution
     * Runtime: 11 ms, faster than 19.15% of Java online submissions for Summary Ranges.
     * Memory Usage: 42 MB, less than 67.84% of Java online submissions for Summary Ranges.
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length < 1) return result;

        Integer left = null;
        Integer right = null;
        for(int i = 0; i < nums.length; i++) {
            if(left == null) {
                left = nums[i];
                right = nums[i];
                continue;
            }
            if(right+1 == nums[i]){
                right++;
                continue;
            }

            if(right.equals(left)) result.add(left+"");
            else result.add(left + "->"+right);
            left = nums[i];
            right = nums[i];
        }
        if(right.equals(left)) result.add(left+"");
        else result.add(left + "->"+right);

        return result;
    }

    public static void main(String[] args) {
        Question0228 q = new Question0228();
        q.summaryRanges(new int[]{2147483647});
    }

}
