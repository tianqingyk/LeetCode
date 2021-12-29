package hard;

import javax.swing.tree.TreeNode;

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
        int withRoot = maxPathSumWithRoot(root.left) + maxPathSumWithRoot(root.right) + root.val;
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

}
