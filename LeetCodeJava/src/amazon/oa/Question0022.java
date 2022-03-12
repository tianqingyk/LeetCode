package amazon.oa;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0022 {

    /**
     * 22. Grid Connections
     * <p>
     * A supply chain manager at Amazon Logistics wants to determine the number of connections between warehouses, represented
     * as nodes on a grid. A grid with m rows and n columns is used to from a cluster of nodes. If a point in the grid
     * has a value of 1, then it represents a node.
     * <p>
     * Each node in the cluster has a level associated with it. A node located in the ith row of the grid is a level inode.
     * <p>
     * Here are the rules for creating a cluster:
     * - Every node at a level connects to the next level that contains at least 1 node(i.e., every node at level i
     * connects to all the nodes at level k where k > i and k is the first level after level i than contains at least
     * on note).
     * - When i reaches the last level in the grid, no more connections are possible.
     * <p>
     * Given such a grid, please help the supply chain manager by finding the number of connections present in the cluster.
     */

    public int gridConnections(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        int prv = 0;
        int cur = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) cur++;
            }
            if (cur > 0) {
                count += prv * cur;
                prv = cur;
                cur = 0;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Question0022 q = new Question0022();
        System.out.println(q.gridConnections(new int[][] {{1,1,1}, {0,1,0}, {0,0,0},{1,1,0}}));
    }
}
