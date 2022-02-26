package google.medium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0056 {

    /**
     * 56. Merge Intervals
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
     */

    /**
     * Solution 1
     * Runtime: 14 ms, faster than 55.35% of Java online submissions for Merge Intervals.
     * Memory Usage: 55.4 MB, less than 16.69% of Java online submissions for Merge Intervals.
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        int n = intervals.length;
        if (n == 2) {
            if (isOverlapping(intervals[0], intervals[1])) {
                return new int[][]{mergerTwo(intervals[0], intervals[1])};
            }
            return intervals;
        }
        sortIntervals(intervals);

        List<int[]> result = new ArrayList<>();
        int[] tmp = intervals[0];
        for (int i = 1; i < n; i++) {
            int[] interval = intervals[i];
            if (isOverlapping(interval, tmp)) {
                tmp = mergerTwo(interval, tmp);
                continue;
            }
            result.add(tmp);
            tmp = interval;
        }
        result.add(tmp);

        return result.toArray(new int[result.size()][]);
    }

    private void sortIntervals(int[][] intervals) {
        Map<Integer, List<int[]>> cache = new HashMap<>();
        int[] array = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            cache.compute(intervals[i][0], (k, v) -> v == null ? new ArrayList<>() : v).add(intervals[i]);
            array[i] = intervals[i][0];
        }

        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            intervals[i] = cache.get(array[i]).remove(0);
        }
    }

    private int[] mergerTwo(int[] interval1, int[] interval2) {
        return new int[]{Math.min(interval1[0], interval2[0]), Math.max(interval1[1], interval2[1])};
    }

    private boolean isOverlapping(int[] interval1, int[] interval2) {
        if (interval1[1] < interval2[0] || interval1[0] > interval2[1]) return false;
        return true;
    }

    /**
     * Solution 2 Copy From Solution
     * Connected Components
     */


    private Map<int[], List<int[]>> graph;
    private Map<Integer, List<int[]>> nodesInComp;
    private Set<int[]> visited;

    // return whether two intervals overlap (inclusive)
    private boolean overlap(int[] a, int[] b) {
        return a[0] <= b[1] && b[0] <= a[1];
    }

    // build a graph where an undirected edge between intervals u and v exists
    // iff u and v overlap.
    private void buildGraph(int[][] intervals) {
        graph = new HashMap<>();
        for (int[] interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }

        for (int[] interval1 : intervals) {
            for (int[] interval2 : intervals) {
                if (overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

    // merges all of the nodes in this connected component into one interval.
    private int[] mergeNodes(List<int[]> nodes) {
        int minStart = nodes.get(0)[0];
        for (int[] node : nodes) {
            minStart = Math.min(minStart, node[0]);
        }

        int maxEnd = nodes.get(0)[1];
        for (int[] node : nodes) {
            maxEnd = Math.max(maxEnd, node[1]);
        }

        return new int[]{minStart, maxEnd};
    }

    // use depth-first search to mark all nodes in the same connected component
    // with the same integer.
    private void markComponentDFS(int[] start, int compNumber) {
        Stack<int[]> stack = new Stack<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            int[] node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);

                if (nodesInComp.get(compNumber) == null) {
                    nodesInComp.put(compNumber, new LinkedList<>());
                }
                nodesInComp.get(compNumber).add(node);

                for (int[] child : graph.get(node)) {
                    stack.add(child);
                }
            }
        }
    }

    // gets the connected components of the interval overlap graph.
    private void buildComponents(int[][] intervals) {
        nodesInComp = new HashMap<>();
        visited = new HashSet<>();
        int compNumber = 0;

        for (int[] interval : intervals) {
            if (!visited.contains(interval)) {
                markComponentDFS(interval, compNumber);
                compNumber++;
            }
        }
    }

    public int[][] merge2(int[][] intervals) {
        buildGraph(intervals);
        buildComponents(intervals);

        // for each component, merge all intervals into one interval.
        List<int[]> merged = new LinkedList<>();
        for (int comp = 0; comp < nodesInComp.size(); comp++) {
            merged.add(mergeNodes(nodesInComp.get(comp)));
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * Solution 3 Copy From Solution
     * Sorting
     */

    public int[][] merge3(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

}
