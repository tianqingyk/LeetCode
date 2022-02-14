package hard;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0305 {

    /**
     * 305. Number of Islands II
     * You are given an empty 2D binary grid of size m x n. The grid represents a map where 0's represent water and 1's
     * represent land. Initially, all the cells of grid are water cells.(i.e. all the cells are 0's).
     * <p>
     * We may perform an add land operation which turns the water at position into a land. You are given an array positions
     * where positions[i] = [ri, ci] is the position(ri, ci) at which we should operate the ith operation.
     * <p>
     * Return an array of integers answer[i] is the number of islands after turning the cell(ri, ci) into a land.
     * <p>
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
     * assume all four edges of the grid are all surrounded by water.
     */

    /**
     * Solution 1
     * Time Limit Exceeded
     */

    Map<String, List<String>> cache = new HashMap<>();

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            int[] position = positions[i];
            String positionStr = getPositionStr(position);
            int x = position[0];
            int y = position[1];

            if (i == 0) {
                result.add(1);
                cache.put(positionStr, new ArrayList<>() {{
                    add(positionStr);
                }});
                continue;
            }

            if (cache.containsKey(positionStr)){
                result.add(result.get(i - 1));
                continue;
            }

            // If the position is a new Island
            String up = getPositionStr(x + 1, y);
            String down = getPositionStr(x - 1, y);
            String left = getPositionStr(x, y - 1);
            String right = getPositionStr(x, y + 1);
            if (!cache.containsKey(up) && !cache.containsKey(down) && !cache.containsKey(left) && !cache.containsKey(right)) {
                result.add(1 + result.get(i - 1));
                cache.put(positionStr, new ArrayList<>() {{
                    add(positionStr);
                }});
                continue;
            }

            List<String> surrounds = new ArrayList<>();
            if (x + 1 < m) {
                surrounds.add(up);
            }
            if (x > 0) {
                surrounds.add(down);
            }

            if (y + 1 < n) {
                surrounds.add(right);
            }

            if (y > 0) {
                surrounds.add(left);
            }

            //If the position is connected with an old Island
            Set<List<String>> oldIsland = new HashSet<>();
            for (String surround : surrounds) {
                if (cache.containsKey(surround)) {
                    oldIsland.add(cache.get(surround));
                }
            }

            result.add(result.get(i - 1) - oldIsland.size() + 1);
            List<String> newIsLand = new ArrayList<>();
            newIsLand.add(positionStr);
            for (List<String> old : oldIsland) {
                newIsLand.addAll(old);
            }

            for (String key : newIsLand) {
                cache.put(key, newIsLand);
            }

        }

        return result;
    }

    private String getPositionStr(int x, int y) {
        return x + " " + y;
    }

    private String getPositionStr(int[] position) {
        return getPositionStr(position[0], position[1]);
    }
}
