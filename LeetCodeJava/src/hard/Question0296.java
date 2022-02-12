package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/10
 */
public class Question0296 {

    /**
     * 296. Best Meeting Point
     *
     * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
     *
     * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
     *
     * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x 0 p1.x| + |p2.y - p1.y|.
     */

    /**
     * Solution 1
     * Runtime: 24 ms, faster than 18.76% of Java online submissions for Best Meeting Point.
     * Memory Usage: 49.5 MB, less than 11.44% of Java online submissions for Best Meeting Point.
     * @param grid
     * @return
     */
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                if (v == 1) {
                    xList.add(i);
                    yList.add(j);
                }
            }
        }

        return minTotalDistance(xList) + minTotalDistance(yList);
    }

    private int minTotalDistance(List<Integer> list) {
        list.sort(Integer::compareTo);
        int median = list.get(list.size()/2);
        int result = 0;
        for (int v : list) {
            result += Math.abs(v - median);
        }
        return result;
    }
}
