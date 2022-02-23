package google.medium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0015 {

    /**
     * 15. 3Sum
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Notice that the solution set must not contain duplicate triplets.
     */


    /**
     * Solution 1
     * Runtime: 687 ms, faster than 14.82% of Java online submissions for 3Sum.
     * Memory Usage: 48.6 MB, less than 71.47% of Java online submissions for 3Sum.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length < 3) {
            return result.stream().toList();
        }
        int len = nums.length;

        Map<Integer, List<Integer>> cache = new HashMap<>();
        for (int i = 0; i < len; i++) {
            cache.compute(nums[i], (k, v) -> v == null ? new ArrayList<>() : v).add(i);
            List<Integer> list = cache.get(nums[i]);
            if (nums[i] == 0){
                if (list.size() > 3){
                    list.remove(0);
                }
                continue;
            }
            if (list.size() > 2){
                list.remove(0);
            }
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int v1 = nums[i];
                int v2 = nums[j];
                int key = -(v1 + v2);
                if (cache.containsKey(key)) {
                    List<Integer> indices = cache.get(key);
                    for (Integer index : indices) {
                        if (index > j) {
                            List<Integer> list = new ArrayList<>() {{
                                add(v1);
                                add(v2);
                                add(nums[index]);
                            }};
                            list.sort(Integer::compare);
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result.stream().toList();
    }

    /**
     * Solution 2 Copy from Solution
     * Two Pointers
     */

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i-1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        }
        return res;
    }

    private void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }

    /**
     * Solution 3
     * Hashset
     */

    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res);
            }
        return res;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }
}
