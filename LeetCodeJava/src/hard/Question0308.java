package hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0308 {

    /**
     * 308. Range Sume Query 2D - Mutable
     *
     * Given a 2D matrix matrix, handle multiple queries of the following types.
     *      1. Update the value of a cell in matrix
     *      2. Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
     *      and lower right corner (row2, col2)
     *
     * Implement the NumMatrix class:
     *      1. NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
     *      2. void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
     *      3. int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the
     *      rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
     */

    /**
     * Solution 1
     * Brute Force
     * Runtime: 19 ms, faster than 69.56% of Java online submissions for Range Sum Query 2D - Mutable.
     * Memory Usage: 48.6 MB, less than 43.55% of Java online submissions for Range Sum Query 2D - Mutable.
     */
    int[][] matrix;

    public Question0308(int[][] matrix) {
        this.matrix = matrix;
    }

    public void update(int row, int col, int val) {
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int minRow = Math.min(row1, row2);
        int maxRow = Math.max(row1, row2);

        int minCol = Math.min(col1, col2);
        int maxCol = Math.max(col1, col2);

        int result = 0;
        for (int x = minRow; x <= maxRow ; x++) {
            for (int y = minCol; y <=maxCol; y++) {
                result += matrix[x][y];
            }
        }
        return result;
    }
}
