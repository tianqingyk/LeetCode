package google.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0103 {

    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
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
     * Runtime: 1 ms, faster than 87.96% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     * Memory Usage: 42.4 MB, less than 39.55% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     */
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return result;
        result.add(new ArrayList<>() {{
            add(root.val);
        }});
        zigzagLevelOrderHelper(new ArrayList<TreeNode>(){{
            add(root.left);
            add(root.right);
        }}, false);
        return result;
    }

    public void zigzagLevelOrderHelper(List<TreeNode> nodes, boolean isLeftToRight) {
        int size = nodes.size();
        if (size < 1) return;
        List<Integer> newList = new ArrayList<>();
        List<TreeNode> newNodes = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            TreeNode node = nodes.get(i);
            if (node != null) {
                newList.add(node.val);
                if (isLeftToRight){
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                }else {
                    newNodes.add(node.right);
                    newNodes.add(node.left);
                }
            }
        }
        if (newList.size() > 0) result.add(newList);
        zigzagLevelOrderHelper(newNodes, !isLeftToRight);
    }
}
