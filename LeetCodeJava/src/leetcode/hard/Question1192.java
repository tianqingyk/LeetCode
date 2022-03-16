package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/15/2022
 */
public class Question1192 {


    /**
     * Solution 1
     * Runtime: 6905 ms, faster than 5.02% of Java online submissions for Critical Connections in a Network.
     * Memory Usage: 358.8 MB, less than 13.00% of Java online submissions for Critical Connections in a Network.
     */
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> circles = new HashMap<>();
    int circleId = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        for (int i = 0; i < connections.size(); i++) {
            List<Integer> connection = connections.get(i);
            graph.compute(connection.get(0), (k, v) -> v == null ? new ArrayList<>() : v).add(connection.get(1));
            graph.compute(connection.get(1), (k, v) -> v == null ? new ArrayList<>() : v).add(connection.get(0));
        }

        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        for (Integer next : graph.get(0)) {
            dfs(next, path);
        }


        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            List<Integer> connection = connections.get(i);
            if (circles.containsKey(connection.get(0)) && circles.get(connection.get(0)) == circles.get(connection.get(1)))
                continue;
            result.add(connection);
        }
        return result;
    }

    public void dfs(int cur, LinkedList<Integer> path) {
        if (!graph.containsKey(cur)) return;
        if (path.contains(cur)) {
            int id = circles.compute(cur, (k, v) -> v == null ? circleId : v);
            ListIterator<Integer> it = path.listIterator(path.size());
            Set<Integer> elementSet = new HashSet<>();
            elementSet.add(cur);
            while (it.hasPrevious()) {
                int previous = it.previous();
                if (previous == cur) break;
                elementSet.add(previous);
                Integer previousId = circles.compute(previous, (k, v) -> v == null ? circleId : v);
                id = Math.min(id, previousId);
            }
            for (Integer element : elementSet) {
                circles.put(element, id);
            }
            circleId++;
            return;
        }

        int pre = path.getLast();
        path.add(cur);
        for (Integer next : graph.get(cur)) {
            if (next == pre) continue;
            if (!circles.containsKey(next) || circles.get(next) != circles.get(cur)) dfs(next, path);
        }
        path.removeLast();
    }

    public static void main(String[] args) {
        Question1192 q = new Question1192();
        q.criticalConnections(5, Arrays.stream(new int[][]{{1, 0}, {2, 0}, {3, 2}, {4, 2}, {4, 3}, {3, 0}, {4, 0}}).map(a -> Arrays.stream(a).boxed().toList()).collect(Collectors.toList()));
//        q.criticalConnections(6, Arrays.stream(new int[][]{{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}}).map(a -> Arrays.stream(a).boxed().toList()).collect(Collectors.toList()));
//        q.criticalConnections(4, Arrays.stream(new int[][]{{0,1},{1,2},{2,0},{1,3}}).map(a -> Arrays.stream(a).boxed().toList()).collect(Collectors.toList()));
    }

}
