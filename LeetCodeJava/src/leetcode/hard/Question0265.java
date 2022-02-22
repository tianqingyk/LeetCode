package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0265 {

    /**
     * 265. Paint House II
     *
     * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house
     * with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same
     * color.
     *
     * The cost of painting each house with a certain color is represented by an n X k cost matrix costs.
     *          For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house
     *          1 with color 2, and so on....
     * Return the minimum cost to paint all houses.
     */

    /**
     * Solution 1 copy from solution
     * 1. Memoization
     */
    private int n;
    private int k;
    private int[][] costs;
    private Map<String, Integer> memo;

    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        this.k = costs[0].length;
        this.n = costs.length;
        this.costs = costs;
        this.memo = new HashMap<>();
        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color < k; color++) {
            minCost = Math.min(minCost, memoSolve(0, color));
        }
        return minCost;
    }

    private int memoSolve(int houseNumber, int color) {
        //Base case: There are no more houses after this one.
        if (houseNumber == n - 1) {
            return costs[houseNumber][color];
        }
        String key = getKey(houseNumber, color);
        // Memoization lookup case: Have we already solved this subproblem?
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        //Recursive case: Determine the minimum cost for the remainder.
        int minRemainingCost = Integer.MAX_VALUE;
        for (int nextColor = 0; nextColor < k; nextColor++) {
            if (color == nextColor) continue;
            int currentRemainingCost = memoSolve(houseNumber + 1, nextColor);
            minRemainingCost = Math.min(currentRemainingCost, minRemainingCost);
        }
        int totalCost = costs[houseNumber][color] + minRemainingCost;
        memo.put(key, totalCost);
        return totalCost;
    }

    private String getKey(int n, int color) {
        return String.valueOf(n) + " " + String.valueOf(color);
    }

    /**
     * Solution 2
     * Dynamic Programming
     */

    public int minCostII2(int[][] costs) {
        if (costs.length == 0) return 0;
        int k = costs[0].length;
        int n = costs.length;

        for (int house = 1; house < n; house++) {
            for (int color = 0; color < k; color++) {
                int min = Integer.MAX_VALUE;
                for (int previousColor = 0; previousColor < k; previousColor++) {
                    if (color == previousColor) continue;
                    min = Math.min(min, costs[house - 1][previousColor]);
                }
                costs[house][color] += min;
            }
        }

        //Find the minimum in the last row
        int min = Integer.MAX_VALUE;
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }

    /**
     * Solution 3
     * Dynamic programming with O(k) additional Space
     */
    public int minCostII3(int[][] costs) {
        if (costs.length == 0) return 0;
        int k = costs[0].length;
        int n = costs.length;

        int[] previousRow = costs[0];

        for (int house = 1; house < n; house++) {
            int[] currentRow = new int[k];
            for (int color = 0; color < k; color++) {
                int min = Integer.MAX_VALUE;
                for (int previousColor = 0; previousColor < k; previousColor++) {
                    if (color == previousColor) continue;
                    min = Math.min(min, previousRow[previousColor]);
                }
                currentRow[color] += costs[house][color] += min;
            }
            previousRow = currentRow;
        }

        // Find the minimum in the last row
        int min = Integer.MAX_VALUE;
        for (int c : previousRow) {
            min = Math.min(min, c);
        }
        return min;
    }

    /**
     * Solution 4 Dynamic programming with Optimized Time
     */

    public int minCostII4(int[][] costs) {
        if (costs.length == 0) return 0;
        int k = costs[0].length;
        int n = costs.length;

        for (int house = 1; house < n; house++) {
            // Find the minimum and second minimum color int the PREVIOUS row.
            int minColor = -1;
            int secondMinColor = -1;
            for (int color = 0; color < k; color++) {
                int cost = costs[house - 1][color];
                if (minColor == -1 || cost < costs[house - 1][minColor]) {
                    secondMinColor = minColor;
                    minColor = color;
                } else if (secondMinColor == -1 || cost < costs[house - 1][secondMinColor]) {
                    secondMinColor = color;
                }
            }

            // And now calculate the new costs for the current row.
            for (int color = 0; color < k; color++) {
                if (color == minColor) {
                    costs[house][color] += costs[house - 1][secondMinColor];
                } else {
                    costs[house][color] += costs[house - 1][minColor];
                }
            }
        }

        // Find the minimum int the last row.
        int min = Integer.MAX_VALUE;
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }

        return min;
    }

    /**
     * Dynamic programming with optimized time and space
     */

    public int minCostII5(int[][] costs) {
        if (costs.length == 0) return 0;
        int k = costs[0].length;
        int n = costs.length;

        /**
         * Firstly, we need to determine the 2 lowest costs of the first row.
         * We also need to remember the color of the lowest
         */
        int prevMin = -1;
        int prevSecondMin = -1;
        int prevMinColor = -1;
        for (int color = 0; color < k; color++) {
            int cost = costs[0][color];
            if (prevMin == -1 || cost < prevMin) {
                prevSecondMin = prevMin;
                prevMinColor = color;
                prevMin = cost;
            }else if (prevSecondMin == -1 || cost < prevSecondMin) {
                prevSecondMin = cost;
            }
        }

        // And now, we need to work our way down, keeping track of the minimums.
        for (int house = 1; house < n; house++) {
            int min = -1;
            int secondMin = -1;
            int minColor = -1;
            for (int color = 0; color < k; color ++) {
                // Determine the cost for this cell(without writing it in).
                int cost = costs[house][color];
                if (color == prevMinColor) {
                    cost += prevSecondMin;
                } else {
                    cost += prevMin;
                }

                // Determine whether or not this current cost is also a minimum.
                if (min == -1 || cost < min){
                    secondMin = min;
                    minColor = color;
                    min = cost;
                } else if (secondMin == -1 || cost < secondMin) {
                    secondMin = cost;
                }
            }
            // Tranksfer current mins to be previous mins.
            prevMin = min;
            prevSecondMin = secondMin;
            prevMinColor = minColor;
        }

        return prevMin;
    }

}
