package leetcode.hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0212 {

    /**
     * 212. Word Search II
     * <p>
     * Given an m x n board of characters and a list of strings words, return all words on the board.
     * <p>
     * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
     * or vertically neighboring. The same letter cell may not be used more than once in a word.
     */

    int m = 0;
    int n = 0;

    /**
     * Solution 1
     * Time limit exceeded
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        m = board.length;
        n = board[0].length;

        Map<Character, List<Integer>> cache = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cache.compute(board[i][j], (k, v) -> v == null ? new ArrayList<>() : v).add(getIndex(i, j));
            }
        }

        for (String word : words) {
            if (findWord(cache, word, 0, new ArrayList<Integer>(), -1)) {
                result.add(word);
            }
        }

        return result;
    }

    public boolean findWord(Map<Character, List<Integer>> cache, String word, int i, List<Integer> indexList, int beforeIndex) {
        if (i >= word.length()) {
            return true;
        }

        char c = word.charAt(i);
        if (!cache.containsKey(c)) {
            return false;
        }
        List<Integer> listIndex = cache.get(c);
        for (int index : listIndex) {
            if (beforeIndex < 0) {
                List<Integer> newIndexList = new ArrayList<>();
                newIndexList.add(index);
                if (findWord(cache, word, i + 1, newIndexList, index)) {
                    return true;
                }
                continue;
            }
            if (indexList.contains(index)) {
                continue;
            }

            if (isNeighbor(index, beforeIndex)) {
                List<Integer> newIndexList = new ArrayList<>();
                newIndexList.addAll(indexList);
                newIndexList.add(index);
                if (findWord(cache, word, i + 1, newIndexList, index)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getIndex(int row, int col) {
        return row * n + col;
    }

    private boolean isNeighbor(int index1, int index2) {
        int row1 = index1 / n;
        int col1 = index1 % n;
        int row2 = index2 / n;
        int col2 = index2 % n;

        if (row1 == row2) {
            if (col1 == col2 + 1 || col1 == col2 - 1) {
                return true;
            }
        } else {
            if (col1 == col2) {
                if (row1 == row2 + 1 || row1 == row2 - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Solution 2
     * Backtracking Trie  copy from solutions
     */
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        String word = null;

        public TrieNode() {
        }
    }

    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords2(char[][] board, String[] words) {
        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = new TrieNode();
            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word; // store words in Trie
        }

        this._board = board;
        // step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        //check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock direction: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++){
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0 || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])){
                backtracking(newRow, newCol, currNode);
            }
        }

        //End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        //Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }

    public static void main(String[] args) {
        Question0212 q = new Question0212();
        List<String> result = q.findWords(new char[][]{{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}}, new String[]{"ade"});
        System.out.println(result);
    }
}
