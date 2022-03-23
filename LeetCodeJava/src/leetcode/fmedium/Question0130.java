package leetcode.fmedium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0130 {

    /**
     * 130. Surrounded Regions
     */


    /**
     * Solution 1
     * Runtime: 94 ms, faster than 5.04% of Java online submissions for Surrounded Regions.
     * Memory Usage: 52 MB, less than 20.86% of Java online submissions for Surrounded Regions.
     */
    char[][] board;
    int m, n;
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.board = board;
        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    Set<Integer> region = new HashSet<>();
                    int needFlip = findRegion(i, j, region);
                    if (needFlip == 0) {
                        for (int pos : region) {
                            int posI = pos / n;
                            int posJ = pos % n;
                            board[posI][posJ] = 'X';
                        }
                    }
                }
            }
        }
    }

    private int findRegion(int i, int j, Set<Integer> region) {
        region.add(getPosition(i, j));
        int needFlip = 0;
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) needFlip = 1;

        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            int position = getPosition(newI, newJ);
            if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
            if (board[newI][newJ] == 'O' && !region.contains(position)) {
                needFlip += findRegion(newI, newJ, region);
            }
        }
        return needFlip;
    }

    private int getPosition(int i, int j) {
        return i * n + j;
    }

    /**
     * Solution 2 Copy From Solution
     * DFS(Depth-First Search)
     */
    protected Integer ROWS = 0;
    protected Integer COLS = 0;

    public void solve2(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.ROWS = board.length;
        this.COLS = board[0].length;

        List<Pair<Integer, Integer>> borders = new LinkedList<Pair<Integer, Integer>>();
        // Step 1). construct the list of border cells
        for (int r = 0; r < this.ROWS; ++r) {
            borders.add(new Pair(r, 0));
            borders.add(new Pair(r, this.COLS - 1));
        }
        for (int c = 0; c < this.COLS; ++c) {
            borders.add(new Pair(0, c));
            borders.add(new Pair(this.ROWS - 1, c));
        }

        // Step 2). mark the escaped cells
        for (Pair<Integer, Integer> pair : borders) {
            this.DFS(board, pair.first, pair.second);
        }

        // Step 3). flip the cells to their correct final states
        for (int r = 0; r < this.ROWS; ++r) {
            for (int c = 0; c < this.COLS; ++c) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
                if (board[r][c] == 'E')
                    board[r][c] = 'O';
            }
        }
    }

    protected void DFS(char[][] board, int row, int col) {
        if (board[row][col] != 'O')
            return;

        board[row][col] = 'E';
        if (col < this.COLS - 1)
            this.DFS(board, row, col + 1);
        if (row < this.ROWS - 1)
            this.DFS(board, row + 1, col);
        if (col > 0)
            this.DFS(board, row, col - 1);
        if (row > 0)
            this.DFS(board, row - 1, col);
    }

    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Solution 3 Copy From Solution
     * BFS(Breadth-First Search)
     */
    public void solve3(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.ROWS = board.length;
        this.COLS = board[0].length;

        List<Pair<Integer, Integer>> borders = new LinkedList<Pair<Integer, Integer>>();
        // Step 1). construct the list of border cells
        for (int r = 0; r < this.ROWS; ++r) {
            borders.add(new Pair(r, 0));
            borders.add(new Pair(r, this.COLS - 1));
        }
        for (int c = 1; c < this.COLS - 1; ++c) {
            borders.add(new Pair(0, c));
            borders.add(new Pair(this.ROWS - 1, c));
        }

        // Step 2). mark the escaped cells
        for (Pair<Integer, Integer> pair : borders) {
            this.BFS(board, pair.first, pair.second);
        }

        // Step 3). flip the cells to their correct final states
        for (int r = 0; r < this.ROWS; ++r) {
            for (int c = 0; c < this.COLS; ++c) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
                if (board[r][c] == 'E')
                    board[r][c] = 'O';
            }
        }
    }

    protected void BFS(char[][] board, int r, int c) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
        queue.offer(new Pair<>(r, c));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.pollFirst();
            int row = pair.first, col = pair.second;
            if (board[row][col] != 'O')
                continue;

            board[row][col] = 'E';
            if (col < this.COLS - 1)
                queue.offer(new Pair<>(row, col + 1));
            if (row < this.ROWS - 1)
                queue.offer(new Pair<>(row + 1, col));
            if (col > 0)
                queue.offer(new Pair<>(row, col - 1));
            if (row > 0)
                queue.offer(new Pair<>(row - 1, col));
        }
    }

}
