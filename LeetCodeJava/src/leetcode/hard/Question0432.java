package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0432 {

    /**
     * 432. All O`one Data Structure
     */

    /**
     * Solution 1
     * Runtime: 45 ms, faster than 94.83% of Java online submissions for All O`one Data Structure.
     * Memory Usage: 58.1 MB, less than 95.25% of Java online submissions for All O`one Data Structure.
     */
    Map<String, Integer> dataMap = new HashMap<>();
    String maxKey = null;
    String minKey = null;

    public void inc(String key) {
        int value = dataMap.compute(key, (k, v) -> v == null ? 1: v+1);
        if(maxKey == null || value > dataMap.get(maxKey)){
            maxKey = key;
        }
        if(minKey == null || value < dataMap.get(minKey)){
            minKey = key;
        }else if(minKey.equals(key)) findMinKey();


    }

    public void dec(String key) {
        int value = dataMap.compute(key, (k, v) -> v == null ? 0: v - 1);
        if(value == 0) {
            dataMap.remove(key);
        }
        if((key.equals(minKey) && value == 0) || value < dataMap.get(minKey)){
            findMinKey();
        }
        if(key.equals(maxKey)){
            findMaxKey();
        }
    }

    private void findMinKey(){
        for(Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            if(minKey == null || !dataMap.containsKey(minKey) || entry.getValue() < dataMap.get(minKey)){
                minKey = entry.getKey();
            }
        }
    }

    private void findMaxKey(){
        for(Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            if(maxKey == null || !dataMap.containsKey(maxKey) || entry.getValue() > dataMap.get(maxKey)){
                maxKey = entry.getKey();
            }
        }
    }

    public String getMaxKey() {
        return maxKey == null ? "": maxKey;
    }

    public String getMinKey() {
        return minKey == null ? "" : minKey;
    }
}
