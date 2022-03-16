package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0354 {

    /**
     * 354. Russian Doll Envelopes
     */

    int maxEnvelopes = Integer.MIN_VALUE;
    boolean[] visited;
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        if (envelopes.length == 1) return 1;
        BiFunction<int[], int[], Integer> comparator = (a, b) -> {
            int cmp = Integer.compare(a[0], b[0]);
            if (cmp == 0) cmp = Integer.compare(a[1], b[1]);
            return cmp;
        };

        List<int[]> envelopeList = Arrays.stream(envelopes).sorted((a, b) -> comparator.apply(a, b)).toList();
        visited = new boolean[envelopeList.size()];

        dfs(0, envelopeList, new LinkedList<>());
        return maxEnvelopes;
    }

    public void dfs(int index, List<int[]> envelopeList, LinkedList<int[]> cache) {
        if (index >= envelopeList.size()) {
            maxEnvelopes = Math.max(cache.size(), maxEnvelopes);
            return;
        }
        if (visited[index]) return;
        int[] current = envelopeList.get(index);
        if (cache.isEmpty()){
            cache.add(current);
            dfs(index+1, envelopeList, cache);
            cache.removeLast();
            dfs(index+1, envelopeList, cache);
            visited[index] = true;
            return;
        }

        int[] previous = cache.getLast();
        if (previous[0] < current[0] && previous[1] < current[1]) {
            cache.add(current);
            dfs(index + 1, envelopeList, cache);
            cache.remove(current);
            dfs(index + 1, envelopeList, cache);
            visited[index] = true;
            return;
        }
        dfs(index + 1, envelopeList, cache);
        visited[index] = true;
    }

    public static void main(String[] args) {
        Question0354 q = new Question0354();
        q.maxEnvelopes(new int[][]{{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}});
    }
}
