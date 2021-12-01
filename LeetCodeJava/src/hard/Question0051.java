package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 11/18/21
 */
public class Question0051 {
    /**
     * QUESTION 51 N-Queens
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
     * <p>
     * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
     * <p>
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
     */

    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<String>> resultStr = new ArrayList<>();

        List<List<Integer>> nPermutation = getNPermutation(n);
        for (List<Integer> list : nPermutation) {
            boolean isOk = true;
            for (int i = 1; i < n; i ++){
                for (int j = 0; j < i; j++){
                    int iValue = list.get(i);
                    int jValue = list.get(j);
                    if (i+iValue == j+jValue || i+jValue == j+iValue){
                        isOk = false;
                        break;
                    }
                }
                if (!isOk){
                    break;
                }
            }
            if (isOk){
             result.add(list);
            }
        }

        Map<Integer, String> intToStr = new HashMap<>();
        for (int i = 0; i < n; i++){
            intToStr.put(i, produceStr(i, n));
        }

        for (List<Integer> list : result){
            List<String> tmp = new ArrayList<>();
            for (Integer i : list){
                tmp.add(intToStr.get(i));
            }
            resultStr.add(tmp);
        }
        return resultStr;
    }

    private String produceStr(int i, int n){
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++){
            if (j == i){
                sb.append('Q');
            }else {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    /**
     * take too much time
     * @param n
     * @return
     */
    private List<List<Integer>> getNPermutation(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 1) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(0);
            result.add(tmp);
            return result;
        }
        for (int i = 0; i < n; i++) {
            for (List<Integer> list : getNPermutation(n - 1)) {
                List<Integer> tmp = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (j == i) {
                        tmp.add(0);
                    } else if (j < i) {
                        tmp.add(list.get(j) + 1);
                    } else {
                        tmp.add(list.get(j - 1) + 1);
                    }
                }
                result.add(tmp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Question0051 q = new Question0051();
        System.out.println(q.solveNQueens(5));
    }
}
