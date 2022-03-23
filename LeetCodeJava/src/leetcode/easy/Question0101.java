package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0101 {

    /**
     * 101. Symmetric Tree
     * https://leetcode.com/problems/symmetric-tree/
     */

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 60.78% of Java online submissions for Symmetric Tree.
     * Memory Usage: 41.6 MB, less than 39.98% of Java online submissions for Symmetric Tree.
     */

    public class TreeNode {
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

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }
}
