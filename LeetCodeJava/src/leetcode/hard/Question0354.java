package leetcode.hard;

import java.util.*;
import java.util.function.BiFunction;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/12
 */
public class Question0354 {

    /**
     * 354. Russian Doll Envelopes
     */

    /**
     * Solution 1
     * Time Limit Exceeded
     */

    class Node {
        int w = -1;
        int h = -1;
        int depth = 0;
        Node parent = null;

        Node() {
        }

        Node(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public boolean putIn(Node node) {
            if (node.depth > depth) return false;

            if (w < node.w && h < node.h) {
                node.depth = depth + 1;
                node.parent = this;
                return true;
            }

            if (parent != null) return parent.putIn(node);
            return false;
        }
    }


    int maxEnvelopes = Integer.MIN_VALUE;

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        if (envelopes.length == 1) return 1;
        BiFunction<int[], int[], Integer> comparator = (a, b) -> {
            int cmp = Integer.compare(a[0], b[0]);
            if (cmp == 0) cmp = Integer.compare(a[1], b[1]);
            return cmp;
        };

        List<int[]> envelopeList = Arrays.stream(envelopes).sorted((a, b) -> comparator.apply(a, b)).toList();

        Node root = new Node();
        Set<Node> leaves = new HashSet<>();
        leaves.add(root);
        for (int[] envelop : envelopeList) {
            Node node = new Node(envelop[0], envelop[1]);
            for (Node leave : leaves) {
                leave.putIn(node);
            }
            leaves.remove(node.parent);
            leaves.add(node);
        }

        for (Node leave : leaves) {
            maxEnvelopes = Math.max(leave.depth, maxEnvelopes);
        }

        return maxEnvelopes;
    }

    /**
     * Solution 2 Copy From Solution
     * Sort + The Longest Increasing Subsequence
     */

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int maxEnvelopes2(int[][] envelopes) {
        // sort on increasing in first dimension and decreasing in second
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) {
                    return arr2[1] - arr1[1];
                } else {
                    return arr1[0] - arr2[0];
                }
            }
        });
        // extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
        return lengthOfLIS(secondDim);
    }

    public static void main(String[] args) {
        Question0354 q = new Question0354();
        q.maxEnvelopes2(new int[][]{{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}});
    }
}
