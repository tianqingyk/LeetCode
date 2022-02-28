package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0100 {

    /**
     * 100. Same Tree
     * https://leetcode.com/problems/same-tree/
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
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
     * Memory Usage: 41.1 MB, less than 37.06% of Java online submissions for Same Tree.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
