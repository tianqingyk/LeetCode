package leetcode.hard;

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
        for (int x = minRow; x <= maxRow; x++) {
            for (int y = minCol; y <= maxCol; y++) {
                result += matrix[x][y];
            }
        }
        return result;
    }

    /**
     * Solution 2 copy from solution
     * Using Binary Indexed Tree
     */

    class NumMatrix2 {
        private int rows;
        private int cols;
        private int[][] bit; // The BIT matrix

        private int lsb(int n) {
            // the line below allows us to directly capture the right most non-zero bit of a number
            return n & (-n);
        }

        private void updateBIT(int r, int c, int val) {
            // keep adding lsb(i) to i, lsb(j) to j and add val to bit[i][j]
            // Using two nested for loops, one for the rows and one for the columns
            for (int i = r; i <= rows; i += lsb(i)) {
                for (int j = c; j <= cols; j += lsb(j)) {
                    this.bit[i][j] += val;
                }
            }
        }

        private int queryBIT(int r, int c) {
            int sum = 0;
            // keep subtracting lsb(i) to i, lsb(j) to j and obtain the final sum as the sum of non-overlapping sub-rectangles
            // Using two nested for loops, one for the rows and one for the columns
            for (int i = r; i > 0; i -= lsb(i)) {
                for (int j = c; j > 0; j -= lsb(j)) {
                    sum += this.bit[i][j];
                }
            }
            return sum;
        }

        private void buildBIT(int[][] matrix) {
            for (int i = 1; i <= rows; ++i) {
                for (int j = 1; j <= cols; ++j) {
                    // call update function on each of the entries present in the matrix
                    int val = matrix[i - 1][j - 1];
                    updateBIT(i, j, val);
                }
            }
        }

        public NumMatrix2(int[][] matrix) {
            rows = matrix.length;
            if (rows == 0) return;
            cols = matrix[0].length;
            bit = new int[rows + 1][];
            // Using 1 based indexing, hence resizing the bit array to (rows + 1, cols + 1)
            for (int i = 1; i <= rows; ++i)
                bit[i] = new int[cols + 1];
            buildBIT(matrix);
        }

        public void update(int row, int col, int val) {
            int old_val = sumRegion(row, col, row, col);
            // handling 1-based indexing
            row++;
            col++;
            int diff = val - old_val;
            updateBIT(row, col, diff);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // handling 1-based indexing
            row1++;
            col1++;
            row2++;
            col2++;
            int a = queryBIT(row2, col2);
            int b = queryBIT(row1 - 1, col1 - 1);
            int c = queryBIT(row2, col1 - 1);
            int d = queryBIT(row1 - 1, col2);
            return (a + b) - (c + d);
        }
    }

    public static void main(String[] args) {
        System.out.println(5 & - 5);
    }
}
