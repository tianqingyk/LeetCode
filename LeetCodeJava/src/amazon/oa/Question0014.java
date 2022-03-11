package amazon.oa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/11/2022
 */
public class Question0014 {

    /**
     * 14. Optimal Utilization Solution
     *
     * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and
     * the second integer represents a value. Your task is to find an element from a and an element form b such that
     * the sum of their values is less or equal to target and as close to target as possible. Return a list of ids of
     * selected elements. If no pair is possible, return an empty list.
     */

    public List<int[]> optimalUtilization(int[][] a, int[][] b, int target) {
        List<int[]> result = new ArrayList<>();

        int closedTarget = -1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int[] aPair = a[i];
                int[] bPair = b[j];

                int sum = aPair[1] + bPair[1];
                if (sum > target) continue;
                if (closedTarget == -1) {
                    closedTarget = target - sum;
                    result.add(new int[]{aPair[0], bPair[0]});
                    continue;
                }

                if (closedTarget > target - sum) {
                    result.clear();
                    closedTarget = target - sum;
                    result.add(new int[]{aPair[0], bPair[0]});
                    continue;
                }

                if (closedTarget == target - sum) {
                    result.add(new int[]{aPair[0], bPair[0]});
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Question0014 q = new Question0014();
        System.out.println(q.optimalUtilization(new int[][]{{1,2}, {2,4}, {3,6}}, new int[][]{{1,2}}, 7));
        System.out.println(q.optimalUtilization(new int[][]{{1,3}, {2,5}, {3,7}, {4,10}}, new int[][]{{1,2}, {2,3},{3,4}, {4,5}}, 10));
        System.out.println(q.optimalUtilization(new int[][]{{1,8}, {2,7}, {3, 14}}, new int[][]{{1,5}, {2,10}, {3,14}}, 20));
        System.out.println(q.optimalUtilization(new int[][]{{1,8}, {2,15}, {3, 9}}, new int[][]{{1,8}, {2,11}, {3,12}}, 20));
    }

}
