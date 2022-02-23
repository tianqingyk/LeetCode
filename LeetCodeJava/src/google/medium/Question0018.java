package google.medium;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0018 {

    /**
     * 18. 4Sum
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     * <p>
     * 0 <= a, b, c, d < n
     * a, b, c, and d are distinct.
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * You may return the answer in any order.
     */

    /**
     * Solution 1
     * Runtime: 16 ms, faster than 77.73% of Java online submissions for 4Sum.
     * Memory Usage: 45.1 MB, less than 33.43% of Java online submissions for 4Sum.
     */
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                threeSum(nums, i, target - nums[i]);
            }
        }
        return result;
    }

    private void threeSum(int[] nums, int i, int target) {
        for (int j = i + 1; j < nums.length - 2; j++) {
            if (j == i + 1 || nums[j] != nums[j - 1]) {
                twoSum(nums, i, j, target - nums[j]);
            }
        }
    }

    private void twoSum(int[] nums, int i, int j, int target) {
        int lo = j + 1, hi = nums.length - 1;
        while (lo < hi) {
            int vLo = nums[lo];
            int vHi = nums[hi];
            int v = vLo + vHi;
            if (v < target) {
                lo++;
            } else if (v > target) {
                hi--;
            } else {
                result.add(new ArrayList<>() {{
                    add(nums[i]);
                    add(nums[j]);
                    add(vLo);
                    add(vHi);
                }});
                lo++;
                hi--;
                while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
            }
        }
    }
}
