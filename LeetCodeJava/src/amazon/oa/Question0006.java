package amazon.oa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/10/2022
 */
public class Question0006 {

    /**
     * 6. Shopping Options/ Find All Combination of Numbers Sum to Target
     * Given a positive integer, target, print all possible combinations of positive integers that sum up to the target number.
     */

    List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> combinationOfNumbersSum(int target){
        recursive(1, target, new LinkedList<>());
        return results;
    }

    private void recursive(int previous, int reminder, LinkedList<Integer> result) {
        if (reminder == 0) {
            if (result.size() > 1) results.add(new LinkedList<>(result));
            return;
        }
        if (reminder < previous) return;
        for (int i = 0; i <= reminder - previous; i++) {
            int cur = previous + i;
            result.add(cur);
            recursive(cur, reminder - cur, result);
            result.removeLast();
        }
    }

    public static void main(String[] args) {
        Question0006 q = new Question0006();
        List<List<Integer>> results = q.combinationOfNumbersSum(5);
        System.out.println(results);
    }
}
