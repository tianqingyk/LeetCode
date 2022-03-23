package leetcode.fmedium;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0128 {

    /**
     * 128. Longest Consecutive Sequence
     */


    /**
     * Solution 1
     * Memory Limit Exceeded
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        if (nums.length == 1) return 1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        boolean[] cache = new boolean[max - min + 1];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            cache[num - min] = true;
        }

        int maxLen = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < cache.length; i++) {
            if (cache[i]) count++;
            else {
                maxLen = Math.max(count, maxLen);
                count = 0;
            }
        }
        maxLen = Math.max(count, maxLen);
        return maxLen;
    }

    /**
     * Solution 2
     * HashSet and Intelligent Sequence Building
     */

    public int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) { // Time complexity of hashset.contains() is O(1).
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

}

