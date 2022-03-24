package leetcode.fmedium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/23/2022
 */
public class Question0040 {

    /**
     * 40. Combination Sum II
     */

    /**
     * Solution 1
     * Runtime: 28 ms, faster than 5.53% of Java online submissions for Combination Sum II.
     * Memory Usage: 49.6 MB, less than 5.09% of Java online submissions for Combination Sum II.
     */

    List<List<Integer>> result = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    Set<String> calculated = new HashSet<>();
    int[] candidates;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        recursive(0, target, new LinkedList<>(), new StringBuilder());
        return result;

    }

    public void recursive(int index, int target, LinkedList<Integer> cache, StringBuilder sb){
        if(target == 0){
            if(!calculated.contains(sb.toString())){
                result.add(new ArrayList<>(cache));
                calculated.add(sb.toString());
            }
            return;
        }
        if(index > candidates.length - 1) return;
        int candidate = candidates[index];
        if(candidate > target) return;
        String visit = index + ","+ sb.toString();
        if(visited.contains(visit)) return;
        visited.add(visit);

        cache.add(candidate);
        String appendStr = "," + candidate;
        sb.append(appendStr);
        recursive(index+1, target - candidate, cache, sb);
        cache.removeLast();
        sb.delete(sb.length() - appendStr.length(), sb.length());

        recursive(index+1, target, cache, sb);
    }

    /**
     * Solution 2
     * Runtime: 5 ms, faster than 66.20% of Java online submissions for Combination Sum II.
     * Memory Usage: 44.8 MB, less than 12.74% of Java online submissions for Combination Sum II.
     */

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        recursive(0, target, new LinkedList<>());
        return result;

    }

    public void recursive(int index, int target, LinkedList<Integer> cache){
        if(target == 0){
            result.add(new ArrayList<>(cache));
            return;
        }
        if(index > candidates.length - 1) return;
        int candidate = candidates[index];
        if(candidate > target) return;

        int next = index+1;
        while(next < candidates.length){
            if(candidate != candidates[next]){
                break;
            }
            next++;
        }

        recursive(next, target, cache);
        for(int i = 1; i <= next - index; i++ ){
            for(int j = 0 ; j < i; j++)
                cache.add(candidate);
            recursive(next, target - i*candidate, cache);
            for(int j = 0 ; j < i; j++)
                cache.removeLast();
        }
    }
}
