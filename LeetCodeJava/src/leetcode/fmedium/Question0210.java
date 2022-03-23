package leetcode.fmedium;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/4
 */
public class Question0210 {

    /**
     * 210. Course Schedule II
     */

    /**
     * Solution 1
     * Runtime: 20 ms, faster than 20.38% of Java online submissions for Course Schedule II.
     * Memory Usage: 50.6 MB, less than 16.78% of Java online submissions for Course Schedule II.
     */
    boolean[] visited;
    boolean[] checked;
    List<List<Integer>> courseList;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        checked = new boolean[numCourses];
        courseList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            courseList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            courseList.get(prerequisite[0]).add(prerequisite[1]);
        }


        List<Integer> studySequence = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (checked[i]) continue;
            if(!dfs(i, studySequence)) return new int[0];
        }
        return studySequence.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfs(int id, List<Integer> studySequence) {
        if (visited[id]) return false;
        visited[id] = true;
        List<Integer> requires = courseList.get(id);
        boolean ret = true;
        for (int require : requires) {
            ret = dfs(require, studySequence);
           if (!ret) break;
        }
        visited[id] = false;
        if (!checked[id]){
            studySequence.add(id);
            checked[id] = true;
        }
        return ret;
    }

    /**
     * Solution 2
     * Using Depth First Search
     */
    class Solution2 {
        static int WHITE = 1;
        static int GRAY = 2;
        static int BLACK = 3;

        boolean isPossible;
        Map<Integer, Integer> color;
        Map<Integer, List<Integer>> adjList;
        List<Integer> topologicalOrder;

        private void init(int numCourses) {
            this.isPossible = true;
            this.color = new HashMap<Integer, Integer>();
            this.adjList = new HashMap<Integer, List<Integer>>();
            this.topologicalOrder = new ArrayList<Integer>();

            // By default all vertces are WHITE
            for (int i = 0; i < numCourses; i++) {
                this.color.put(i, WHITE);
            }
        }

        private void dfs(int node) {

            // Don't recurse further if we found a cycle already
            if (!this.isPossible) {
                return;
            }

            // Start the recursion
            this.color.put(node, GRAY);

            // Traverse on neighboring vertices
            for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
                if (this.color.get(neighbor) == WHITE) {
                    this.dfs(neighbor);
                } else if (this.color.get(neighbor) == GRAY) {
                    // An edge to a GRAY vertex represents a cycle
                    this.isPossible = false;
                }
            }

            // Recursion ends. We mark it as black
            this.color.put(node, BLACK);
            this.topologicalOrder.add(node);
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {

            this.init(numCourses);

            // Create the adjacency list representation of the graph
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0];
                int src = prerequisites[i][1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
                lst.add(dest);
                adjList.put(src, lst);
            }

            // If the node is unprocessed, then call dfs on it.
            for (int i = 0; i < numCourses; i++) {
                if (this.color.get(i) == WHITE) {
                    this.dfs(i);
                }
            }

            int[] order;
            if (this.isPossible) {
                order = new int[numCourses];
                for (int i = 0; i < numCourses; i++) {
                    order[i] = this.topologicalOrder.get(numCourses - i - 1);
                }
            } else {
                order = new int[0];
            }

            return order;
        }
    }

    /**
     * Solution 3
     * Using Node Indegree(Kahn's algorithm)
     */
    class Solution3 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            boolean isPossible = true;
            Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // Create the adjacency list representation of the graph
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0];
                int src = prerequisites[i][1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
                lst.add(dest);
                adjList.put(src, lst);

                // Record in-degree of each vertex
                indegree[dest] += 1;
            }

            // Add all vertices with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int i = 0;
            // Process until the Q becomes empty
            while (!q.isEmpty()) {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // Reduce the in-degree of each neighbor by 1
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--;

                        // If in-degree of a neighbor becomes 0, add it to the Q
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // Check to see if topological sort is possible or not.
            if (i == numCourses) {
                return topologicalOrder;
            }

            return new int[0];
        }
    }
}
