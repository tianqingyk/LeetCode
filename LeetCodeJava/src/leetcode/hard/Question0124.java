package leetcode.hard;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0124 {

    /**
     * Question 124 Binary Tree Maximum Path Sum
     * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
     * <p>
     * The path sum of a path is the sum of the node's values in the path.
     * <p>
     * Given the root of a binary tree, return the maximum path sum of any non-empty path.
     */


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Solution 1
     * Runtime: 262 ms, faster than 5.48% of Java online submissions for Binary Tree Maximum Path Sum.
     * Memory Usage: 40.8 MB, less than 75.43% of Java online submissions for Binary Tree Maximum Path Sum.
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if(root.left == null && root.right == null){
            return root.val;
        }

        int withRoot = Math.max(0, maxPathSumWithRoot(root.left)) + Math.max(0,maxPathSumWithRoot(root.right)) + root.val;

        if(root.left == null){
            return Math.max(withRoot, maxPathSum(root.right));
        }

        if(root.right == null){
            return Math.max(withRoot, maxPathSum(root.left));
        }



        int withOutRoot = Math.max(maxPathSum(root.left), maxPathSum(root.right));
        return Math.max(withRoot, withOutRoot);
    }

    private int maxPathSumWithRoot(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, maxPathSumWithRoot(node.left));
        int right = Math.max(0, maxPathSumWithRoot(node.right));
        return Math.max(left, right) + node.val;
    }


    int max_sum = Integer.MIN_VALUE;

    /**
     * Solution 2 copy from solution
     * @param node
     * @return
     */
    public int max_gain(TreeNode node) {
        if (node == null) return 0;

        // max sum on the left and right sub-trees of node
        int left_gain = Math.max(max_gain(node.left), 0);
        int right_gain = Math.max(max_gain(node.right), 0);

        // the price to start a new path where `node` is a highest node
        int price_newpath = node.val + left_gain + right_gain;

        // update max_sum if it's better to start a new path
        max_sum = Math.max(max_sum, price_newpath);

        // for recursion :
        // return the max gain if continue the same path
        return node.val + Math.max(left_gain, right_gain);
    }

    public int maxPathSum2(TreeNode root) {
        max_gain(root);
        return max_sum;
    }


}
