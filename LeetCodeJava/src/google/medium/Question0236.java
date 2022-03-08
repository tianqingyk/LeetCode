package google.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/8/2022
 */
public class Question0236 {

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * Solution
     * Runtime: 17 ms, faster than 14.76% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * Memory Usage: 46.7 MB, less than 52.57% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     */
    Set<TreeNode> needFind = new HashSet<>();
    TreeNode ancestor = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        needFind.add(p);
        needFind.add(q);
        findCommonAncestor(root);
        return ancestor;
    }

    private boolean findCommonAncestor(TreeNode node) {
        if (ancestor != null || needFind.isEmpty() || node == null) return false;
        boolean isContain = false;
        if (needFind.contains(node)) {
            isContain = true;
            needFind.remove(node);
            if (needFind.isEmpty()) return true;
        }

        boolean left = findCommonAncestor(node.left);
        boolean right = findCommonAncestor(node.right);

        if ((left && right) || (isContain && (left || right))) {
            ancestor = node;
            return true;
        }

        return isContain || left || right;
    }
}
