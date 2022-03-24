package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/23/2022
 */
public class Question0036 {

    /**
     * 36. Valid Sudoku
     */

    /**
     * Solution
     * Runtime: 4 ms, faster than 70.31% of Java online submissions for Valid Sudoku.
     * Memory Usage: 45.3 MB, less than 72.32% of Java online submissions for Valid Sudoku.
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] dp = new boolean[3 * 9][9];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++){
                char c = board[i][j];
                if(!Character.isDigit(c)) continue;
                int digit = c - '1';
                int row1 = i;
                int row2 = 9 + j;
                int row3 = 18 + i/3 * 3+j/3;
                if(dp[row1][digit] || dp[row2][digit] || dp[row3][digit]) return false;
                dp[row1][digit] = true;
                dp[row2][digit] = true;
                dp[row3][digit] = true;
            }
        }

        return true;
    }
}
