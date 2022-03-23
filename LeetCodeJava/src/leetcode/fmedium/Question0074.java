package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0074 {

    /**
     * 74. Search a 2D Matrix
     * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
     * <p>
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
     * Memory Usage: 43.4 MB, less than 6.26% of Java online submissions for Search a 2D Matrix.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int pivot = (left + right) / 2;
            int val = matrix[pivot / n][pivot % n];
            if (val == target) return true;
            if (val < target) {
                if (left == pivot) break;
                left = pivot;
            } else {
                if (right == pivot) break;
                right = pivot;
            }
        }

        if (matrix[right / n][right % n] == target) return true;
        return false;
    }

    /**
     * Solution 2 Copy From Solution
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement)
                return true;
            else {
                if (target < pivotElement)
                    right = pivotIdx - 1;
                else
                    left = pivotIdx + 1;
            }
        }
        return false;
    }

}
