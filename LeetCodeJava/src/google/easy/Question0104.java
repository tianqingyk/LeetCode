package google.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0104 {

    /**
     * 104. Maximum Depth of Binary Tree
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
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
     * Runtime: 2 ms, faster than 7.85% of Java online submissions for Maximum Depth of Binary Tree.
     * Memory Usage: 43.7 MB, less than 11.61% of Java online submissions for Maximum Depth of Binary Tree.
     */
    int depth = 0;
    public int maxDepth(TreeNode root) {
        depthSearch(new ArrayList<>(){{add(root);}});
        return depth;
    }

    private void depthSearch(List<TreeNode> nodes) {
        if (nodes.size() < 1) return;

        List<TreeNode> newNodes = new ArrayList<>();
        boolean isEmpyt = true;
        for (TreeNode node : nodes) {
            if (node != null) {
                isEmpyt = false;
                newNodes.add(node.left);
                newNodes.add(node.right);
            }
        }
        if (!isEmpyt) {
            depth++;
            depthSearch(newNodes);
        }
    }
}
