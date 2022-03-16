package leetcode.hard;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0330 {

    /**
     * 330. Patching Array
     */

    /**
     * Solution 1
     * Time Limit Exceeded
     */
    int result = 0;
    int n;
    int check = 1;

    public int minPatches(int[] nums, int n) {
        this.n = n;
        Set<Integer> havedSet = recursive(0, nums, new HashSet<>());
        List<Integer> havedList = havedSet.stream().sorted().toList();

        while (true) {
            int min = havedList.get(0);
            int max = havedList.get(havedList.size() - 1);
            if (min == 1 && max - min + 1 == havedList.size()) break;

            Set<Integer> newHaveSet = new HashSet<>();

            int addNum = -1;
            for (; check <= max; check++) {
                if (!havedList.contains(check)) {
                    addNum = check;
                    break;
                }
            }
            if (addNum == -1) break;

            newHaveSet.add(addNum);
            for (Integer num : havedList) {
                newHaveSet.add(num);
                if (num + addNum <= n) newHaveSet.add(num + addNum);
            }
            havedList = newHaveSet.stream().sorted().toList();
            result++;
        }

        long max = havedList.get(havedList.size() - 1);

        if (max >= n) return result;
        while (max < n) {
            result++;
            max = 2 * max + 1;
        }


        return result;
    }

    private Set<Integer> recursive(int index, int[] nums, Set<Integer> havedSet) {
        if (index >= nums.length) return havedSet;
        int num = nums[index];
        if (num > n) return havedSet;

        Set<Integer> newHavedSet = new HashSet<>(havedSet);
        for (Integer i : havedSet) {
            if (i + num <= n) newHavedSet.add(i + num);
        }
        newHavedSet.add(num);
        return recursive(index + 1, nums, newHavedSet);
    }

    /**
     * Solution 2 Copy From Solution
     */

    public int minPatches2(int[] nums, int n) {
        int patches = 0, i = 0;
        long miss = 1; // use long to avoid integer overflow error
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) // miss is covered
                miss += nums[i++];
            else { // patch miss to the array
                miss += miss;
                patches++; // increase the answer
            }
        }
        return patches;
    }

}
