package leetcode.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0110 {

    /**
     * 110. Balanced Binary Tree
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


    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
       if (!isBalanced(root.left) || !isBalanced(root.right)) return false;
       if (Math.abs(countHeight(root.left) - countHeight(root.right)) > 1) return false;
       return true;
    }

    private int countHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(countHeight(node.left), countHeight(node.right)) + 1;
    }
}
