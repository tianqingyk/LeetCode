package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0098 {

    /**
     * 98. Validate Binary Search Tree
     * https://leetcode.com/problems/validate-binary-search-tree/
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

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 61.23% of Java online submissions for Validate Binary Search Tree.
     * Memory Usage: 43.9 MB, less than 29.12% of Java online submissions for Validate Binary Search Tree.
     */

    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, (long) Integer.MIN_VALUE - 1, (long)Integer.MAX_VALUE + 1);
    }

    private boolean isValidBSTHelper(TreeNode root, long left, long right){
        if (root == null) return true;
        if (root.left != null && ((root.left.val >= root.val) || (root.left.val <= left) || (root.left.val >= right))) return false;
        if (root.right != null && ((root.right.val <= root.val) || (root.right.val <= left) || (root.right.val >= right)) ) return false;

        return isValidBSTHelper(root.left, left, root.val) && isValidBSTHelper(root.right, root.val, right);
    }
}
