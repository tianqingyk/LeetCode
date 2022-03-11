package amazon.oa;

import org.w3c.dom.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0017 {

    /**
     * 17. Shopping Patterns Minimum Degree of a Connected Trio in a Graph
     *
     * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an
     * array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
     *
     * A connected trio is a set of three nodes where there is an edge between every pair of them.
     *
     * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
     *
     * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
     */

    /**
     * Copy From Solution
     * @return
     */
    public int minTrioDegree(int n, int[][] edges) {
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> degrees = new HashMap<>();
        boolean[][] isEdge = new boolean[n+1][n+1];

        for(int[] edge : edges) {
            degrees.compute(edge[0], (k,v) -> v == null? 1: v+1);
            degrees.compute(edge[1], (k,v) -> v == null? 1: v+1);
            isEdge[edge[0]][edge[1]] = true;
            isEdge[edge[1]][edge[0]] = true;
        }

        for (int[] edge : edges) {
            for (int i = 1; i <= n; i++) {
                if (isEdge[i][edge[0]] && isEdge[i][edge[1]]){
                    int degree = degrees.get(i) + degrees.get(edge[0]) + degrees.get(edge[1]) - 6;
                    min = Math.min(min, degree);
                }
            }
        }

        if (min == Integer.MAX_VALUE)
            return -1;

        return min;
    }
}
