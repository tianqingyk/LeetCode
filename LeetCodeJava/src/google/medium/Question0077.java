package google.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0077 {

    /**
     * 77. Combinations
     *
     * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
     *
     * You may return the answer in any order.
     */

    /**
     * Solution 1
     * It's a combination, not a permutation!
     * Runtime: 15 ms, faster than 82.79% of Java online submissions for Combinations.
     * Memory Usage: 45 MB, less than 76.43% of Java online submissions for Combinations.
     */
    List<List<Integer>> result = new ArrayList<>();
    int n,k;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        recurse(1, new LinkedList<Integer>());
        return result;
    }

    private void recurse(int i, LinkedList<Integer> cache) {
        if (cache.size() == k){
            result.add(new LinkedList<>(cache));
            return;
        }
        if (i > n) return;

        recurse(i+1, cache);

        cache.add(i);
        recurse(i+1, cache);
        cache.removeLast();
    }
}
