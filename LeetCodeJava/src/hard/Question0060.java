package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/11/30
 */
public class Question0060 {
    /**
     * QUESTION 60 Permutation Sequence
     * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
     *
     * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     */
    public String getPermutation(int n, int k) {

        List<Integer> list = getPermutationList(n, k);
        StringBuilder sb = new StringBuilder();
        for (int i: list) {
            sb.append(i);
        }
        return sb.toString();
    }

    private List<Integer> getPermutationList(int n, int k){
        List<Integer> result = new ArrayList<>();
        if (n <= 1){
            result.add(1);
            return result;
        }
        int f = factorialN(n-1);
        int start =  (k-1)/f + 1;
        int nextK = k%f;
        if(nextK <= 0){
            nextK = f;
        }
        List<Integer> tmpList = getPermutationList(n-1, nextK);

        result.add(start);
        for (int i = 0; i < n-1; i++){
            int v = tmpList.get(i);
            if (v >= start){
                v++;
            }
            result.add(v);
        }
        return result;
    }

    private int factorialN(int n){
        if (n <= 1){
            return 1;
        }
        return n*factorialN(n-1);
    }

    public static void main(String[] args) {
        Question0060 q = new Question0060();
        System.out.println(q.getPermutation(4,9));
    }
}
