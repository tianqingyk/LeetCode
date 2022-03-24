package leetcode.fmedium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/23/2022
 */
public class Question0039 {

    /**
     * 39. Combination Sum
     */

    /**
     * Solution
     * Runtime: 8 ms, faster than 36.91% of Java online submissions for Combination Sum.
     * Memory Usage: 42.8 MB, less than 81.40% of Java online submissions for Combination Sum.
     */
    List<List<Integer>> result = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    int[] candidates;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        recursive(0, target, new LinkedList<Integer>());
        return result;
    }

    private void recursive(int index, int target, LinkedList<Integer> cache){
        if(target == 0) {
            result.add(new ArrayList<>(cache));
            return;
        }
        if(index > candidates.length - 1) return;
        int candidate = candidates[index];
        if(candidate > target) return;
        String key = index+" "+target;
        if(visited.contains(key)) return;
        visited.add(key);

        cache.add(candidate);
        recursive(index, target - candidate, cache);
        cache.removeLast();
        recursive(index+1, target, cache);
    }

}
