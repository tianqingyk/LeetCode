package hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/2/21
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QUESTION37
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 */
public class Question0037 {
    public final static int NINE = 9;

    public void solveSudoku(char[][] board) {
        boolean[][] matrix = new boolean[3 * NINE][NINE];
        for (int i = 0; i < 3 * NINE; i++) {
            for (int j = 0; j < NINE; j++) {
                matrix[i][j] = false;
            }
        }

        Map<Integer, Integer> posMap = new HashMap<>();
        for (int i = 0; i < NINE; i++) {
            for (int j = 0; j < NINE; j++) {
                char tmp = board[i][j];
                if (tmp == '.') {
                    posMap.put(i * 10 + j, 0);
                    continue;
                }
                putNumber(Integer.parseInt(String.valueOf(tmp)), i * 10 + j, matrix);
            }
        }

        for (int k = 0; k < posMap.size(); k++) {
            int pos = (int) posMap.keySet().toArray()[k];
            List<Integer> possibleNumbers = getPossibleNumbers(pos, matrix);

            boolean isOk = false;
            for (int num : possibleNumbers) {
                if (num > posMap.get(pos)) {
                    if (posMap.get(pos) > 0){
                        removeNumber(posMap.get(pos), pos, matrix);
                        posMap.put(pos, 0);
                    }
                    putNumber(num, pos, matrix);
                    posMap.put(pos, num);
                    isOk = true;
                    break;
                }
            }

            if(!isOk){
                if (posMap.get(pos) > 0){
                    removeNumber(posMap.get(pos), pos, matrix);
                    posMap.put(pos, 0);
                }
                k-= 2;
            }
        }

        for (Map.Entry<Integer, Integer> entry : posMap.entrySet()) {
            int pos = entry.getKey();
            int i = pos / 10;
            int j = pos % 10;
            board[i][j] = Character.forDigit(entry.getValue(), 10);
        }

    }

    private void putNumber(int num, int pos, boolean[][] matrix) {
        int i = pos / 10;
        int j = pos % 10;
        matrix[i][num - 1] = true;
        matrix[9 + j][num - 1] = true;
        matrix[18 + i / 3 + j / 3 * 3][num - 1] = true;
        System.out.printf("put [%d,%d] %d \n",i,j,num );
    }

    private void removeNumber(int num, int pos, boolean[][] matrix) {
        if (num <= 0){
            return;
        }
        int i = pos / 10;
        int j = pos % 10;
        matrix[i][num - 1] = false;
        matrix[9 + j][num - 1] = false;
        matrix[18 + i / 3 + j / 3 * 3][num - 1] = false;
        System.out.printf("remove [%d,%d] %d \n",i,j,num );
    }

    private List<Integer> getPossibleNumbers(int pos, boolean[][] matrix) {
        int i = pos / 10;
        int j = pos % 10;
        List<Integer> result = new ArrayList<>();
        for (int num = 1; num <= NINE; num++) {
            if (matrix[i][num - 1] || matrix[9 + j][num - 1] || matrix[18 + i / 3 + j / 3 * 3][num - 1]) {
                continue;
            }
            result.add(num);
        }
        return result;
    }

    public static void main(String[] args) {
        Question0037 q = new Question0037();
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        q.solveSudoku(board);
    }
}
