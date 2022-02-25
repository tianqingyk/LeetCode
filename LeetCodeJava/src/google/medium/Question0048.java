package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/25
 */
public class Question0048 {

    /**
     * 48. Rotate Image
     * <p>
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * <p>
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
     * Memory Usage: 40.8 MB, less than 47.27% of Java online submissions for Rotate Image.
     */
    int n;
    int[] tmp;

    public void rotate(int[][] matrix) {
        n = matrix.length;
        if (matrix == null || n == 1) return;
        tmp = new int[n];
        rotateHelper(matrix, 0);
    }

    private void rotateHelper(int[][] matrix, int layer) {
        if (layer >= n / 2) return;
        for (int i = layer + 1; i < n - layer; i++) {
            tmp[i] = matrix[i][n - 1 - layer];
            matrix[i][n - 1 - layer] = matrix[layer][i];
        }

        for (int i = layer + 1; i < n - layer; i++) {
            int t = matrix[n - 1 - layer][n - 1 - i];
            matrix[n - 1 - layer][n - 1 - i] = tmp[i];
            tmp[i] = t;
        }

        for (int i = layer + 1; i < n - layer; i++) {
            int t = matrix[n - 1 - i][layer];
            matrix[n - 1 - i][layer] = tmp[i];
            tmp[i] = t;
        }

        for (int i = layer + 1; i < n - layer; i++) {
            matrix[layer][i] = tmp[i];
        }
        rotateHelper(matrix, layer + 1);
    }

    /**
     * Solution 2 Copy From Solution
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

}
