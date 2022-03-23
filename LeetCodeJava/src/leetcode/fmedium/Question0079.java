package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0079 {

    /**
     * 79. Word Search
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     * <p>
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once
     */

    /**
     * Solution 1
     * Runtime: 143 ms, faster than 64.38% of Java online submissions for Word Search.
     * Memory Usage: 41.9 MB, less than 46.99% of Java online submissions for Word Search.
     */
    char[][] board;
    int m, n;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (recurse(i,j,0, word)) return true;
            }
        }
        return false;
    }

    private boolean recurse(int i, int j, int index, String word) {
        if (index > word.length() - 1) return true;
        if (i < 0 || j < 0 || i > m - 1 || j > n - 1) return false;
        char c = word.charAt(index);
        if (board[i][j] != c) return false;


        board[i][j] = '*';
        boolean result = recurse(i + 1, j, index + 1, word)
                || recurse(i - 1, j, index + 1, word)
                || recurse(i, j - 1, index + 1, word)
                || recurse(i, j + 1, index + 1, word);
        board[i][j] = c;
        return result;
    }
}
