package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/25
 */
public class Question0046 {

    /**
     * 46. Permutations
     * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
     */

    /**
     * Solution 1
     * Runtime: 3 ms, faster than 49.53% of Java online submissions for Permutations.
     * Memory Usage: 45.1 MB, less than 8.56% of Java online submissions for Permutations.
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, 0);
    }

    private List<List<Integer>> permute(int[] nums, int index) {
        List<List<Integer>> result = new ArrayList<>();
        if (index >= nums.length - 1) {
            result.add(new ArrayList<>() {{
                add(nums[nums.length - 1]);
            }});
            return result;
        }

        List<List<Integer>> nextList = permute(nums, index + 1);
        int indexVal = nums[index];
        for (int i = index; i < nums.length; i++) {
            int curVal = nums[i];
            for (List<Integer> list : nextList) {
                if (i == index) {
                    result.add(new ArrayList<>() {{
                        add(curVal);
                        addAll(list);
                    }});
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(curVal);
                    for (Integer integer : list) {
                        if (integer == curVal) newList.add(indexVal);
                        else newList.add(integer);
                    }
                    result.add(newList);
                }
            }
        }
        return result;
    }

}
