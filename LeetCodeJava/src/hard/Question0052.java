package hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/11/30
 */
public class Question0052 {
    /**
     * QUESTION 52 N-Queens II
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
     * <p>
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     */

    public int totalNQueens(int n) {
        int[] array = new int[n];

        return  queenCount(n, 0, array);
    }

    private int queenCount(int n, int x, int[] array){
        if (x >= n){
            return 1;
        }
        int result = 0;
        for (int y = 1; y <= n; y++){
            boolean isOk = true;
            for (int w = 0; w < x; w++) {
                if (y == array[w] || x + y == w + array[w] || x + array[w] == w + y) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                array[x] = y;
                result += queenCount(n, x+1, array);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Question0052 q = new Question0052();
        System.out.println(q.totalNQueens(5));
    }
}
