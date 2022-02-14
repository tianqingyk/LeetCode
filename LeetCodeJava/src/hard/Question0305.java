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

            if (cache.containsKey(positionStr)) {
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

    /**
     * Solution 2 copy from solution
     * (Ad hoc) 临时
     * The only difference between solution 2 and solution 1 is to set an id for each island
     * Runtime: 1239 ms, faster than 5.03% of Java online submissions for Number of Islands II.
     * Memory Usage: 49 MB, less than 63.81% of Java online submissions for Number of Islands II.
     */

    public List<Integer> numIslands22(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> land2id = new HashMap<Integer, Integer>();
        int num_islands = 0;
        int island_id = 0; // Set id for each island

        for (int[] pos : positions) {

            int r = pos[0], c = pos[1];
            if (land2id.containsKey(r * n + c)) {
                ans.add(num_islands);
                continue;
            }
            Set<Integer> overlap = new HashSet<Integer>();

            if (r - 1 >= 0 && land2id.containsKey((r - 1) * n + c)) {
                overlap.add(land2id.get((r - 1) * n + c));
            }
            if (r + 1 < m && land2id.containsKey((r + 1) * n + c)) {
                overlap.add(land2id.get((r + 1) * n + c));
            }
            if (c - 1 >= 0 && land2id.containsKey(r * n + c - 1)) {
                overlap.add(land2id.get(r * n + c - 1));
            }
            if (c + 1 < n && land2id.containsKey(r * n + c + 1)) {
                overlap.add(land2id.get(r * n + c + 1));
            }

            if (overlap.isEmpty()) {
                ++num_islands;
                land2id.put(r * n + c, island_id++);
            } else if (overlap.size() == 1) {
                land2id.put(r * n + c, overlap.iterator().next());
            } else {
                int root_id = overlap.iterator().next();
                for (Map.Entry<Integer, Integer> entry : land2id.entrySet()) {
                    int k = entry.getKey();
                    int id = entry.getValue();
                    if (overlap.contains(id)) {
                        land2id.put(k, root_id);
                    }
                }
                land2id.put(r * n + c, root_id);
                num_islands -= (overlap.size() - 1);
            }
            ans.add(num_islands);
        }

        return ans;
    }

    /**
     * Solution 3 copy from solution
     * Union Find(aka Disjoint Set)
     */

    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public UnionFind(int N) { // for problem 305 and others
            count = 0;
            parent = new int[N];
            rank = new int[N]; // make the structure of the tree squat
            for (int i = 0; i < N; ++i) {
                parent[i] = -1;
                rank[i] = 0;
            }
        }

        public boolean isValid(int i) { // for problem 305
            return parent[i] >= 0;
        }

        public void setParent(int i) {
            parent[i] = i;
            ++count;
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public List<Integer> numIslands23(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            List<Integer> overlap = new ArrayList<>();

            if (r - 1 >= 0 && uf.isValid((r - 1) * n + c)) overlap.add((r - 1) * n + c);
            if (r + 1 < m && uf.isValid((r + 1) * n + c)) overlap.add((r + 1) * n + c);
            if (c - 1 >= 0 && uf.isValid(r * n + c - 1)) overlap.add(r * n + c - 1);
            if (c + 1 < n && uf.isValid(r * n + c + 1)) overlap.add(r * n + c + 1);

            int index = r * n + c;
            uf.setParent(index);
            for (int i : overlap) uf.union(i, index);
            ans.add(uf.getCount());
        }

        return ans;
    }

}
