package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/18/21
 */
public class Question0051 {
    /**
     * QUESTION 51 N-Queens
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
     *
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
     */

    public List<List<String>> solveNQueens(int n) {
        boolean[][] matrix = new boolean[n][n];

        int[] tmpIndex = new int[n];
        for ( int i = 0; i < n; i++) {
           tmpIndex[i] = -1;
        }

        for (int i = 0; i < n; i++){
            List<Integer> posList = getPossiblePos(i, matrix);
            boolean isOk = false;
            for (int pos : posList){
                if (pos > tmpIndex[i]){
                    tmpIndex[i] = pos;
                    putQueen(i, pos, matrix);
                    isOk = true;
                    break;
                }
            }
            if (!isOk){
                
            }
        }
        return null;
    }

    private List<Integer> getPossiblePos(int i, boolean[][] matrix){
        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < matrix[i].length; j++){
            if (!matrix[i][j]){
                result.add(j);
            }
        }
        return result;
    }

    private void putQueen(int i, int pos, boolean[][] matrix){

    }

    public static void main(String[] args){
        Question0051 q = new Question0051();
        q.solveNQueens(4);
    }
}
