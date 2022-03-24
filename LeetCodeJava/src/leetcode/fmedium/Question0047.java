package leetcode.fmedium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/23/2022
 */
public class Question0047 {

    /**
     * 47. Permutations II
     */

    /**
     * Solution
     * Runtime: 16 ms, faster than 25.67% of Java online submissions for Permutations II.
     * Memory Usage: 51.2
     */

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < len; i++) {
            countMap.compute(nums[i], (k,v) -> v == null ? 1: v + 1);
        }

        recursive(new LinkedList<>(), countMap);
        return result;
    }

    public void recursive(LinkedList<Integer> cache, Map<Integer, Integer> countMap) {
        if(countMap.size() == 0){
            result.add(new ArrayList<>(cache));
            return;
        }
        Set<Integer> keySet = new HashSet<>(countMap.keySet());
        for(Integer key : keySet) {
            cache.add(key);
            if(countMap.get(key) == 1){
                countMap.remove(key);
            }else{
                countMap.put(key, countMap.get(key)-1);
            }
            recursive(cache, countMap);
            countMap.compute(key, (k,v) -> v == null? 1 : v +1);
            cache.removeLast();
        }
    }

}
