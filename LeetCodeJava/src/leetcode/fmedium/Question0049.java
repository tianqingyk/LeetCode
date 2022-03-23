package leetcode.fmedium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/25
 */
public class Question0049 {

    /**
     * 49. Group Anagrams
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using
     * all the original letters exactly once.
     */

    /**
     * Solution 1
     * Runtime: 18 ms, faster than 34.58% of Java online submissions for Group Anagrams.
     * Memory Usage: 46.5 MB, less than 76.27% of Java online submissions for Group Anagrams.
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> cache = new HashMap<>();
        for (String str : strs) {
            int[] array = new int[26];
            for (int i = 0; i < str.length(); i++) {
                array[str.charAt(i) - 'a'] += 1;
            }
            cache.compute(arrayToString(array), (k,v) -> v == null ? new ArrayList<>():v).add(str);
        }
        List<List<String>> result = new ArrayList<>();
        result.addAll(cache.values());
        return result;
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Question0049 q = new Question0049();
        q.groupAnagrams(new String[]{"eat", "ate", "tea"});
    }

}
