package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0054 {

    /**
     * 54. Spiral Matrix
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     */


    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
     * Memory Usage: 40.1 MB, less than 44.77% of Java online submissions for Spiral Matrix.
     */
    int m, n;
    static final int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        m = matrix.length;
        n = matrix[0].length;
        int i = 0;
        int j = 0;
        int direct = 0;
        int[] limits = new int[]{0,n, m, 0};
        while (result.size() < m * n) {
            result.add(matrix[i][j]);
            i += direction[direct][0];
            j += direction[direct][1];
            if (i < limits[0] || i >= limits[2] || j < limits[3] || j >= limits[1]) {
                i -= direction[direct][0];
                j -= direction[direct][1];
                if (direct == 1 || direct == 2){
                    limits[direct] -= 1;
                }else {
                    limits[direct] += 1;
                }
                direct = (direct + 1) % 4;
                i += direction[direct][0];
                j += direction[direct][1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Question0054 q = new Question0054();
        q.spiralOrder(new int[][] {{1,2,3},{4,5,6},{7,8,9}});
    }
}
