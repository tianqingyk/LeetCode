package leetcode.fmedium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0113 {

    /**
     * 113. Path Sum II
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
     * Runtime: 2 ms, faster than 76.23% of Java online submissions for Path Sum II.
     * Memory Usage: 44.6 MB, less than 34.45% of Java online submissions for Path Sum II.
     */
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        findPath(root, targetSum, new LinkedList<>());
        return result;
    }

    private void findPath(TreeNode node, int targetSum, LinkedList<Integer> path) {
        if (node == null) return;

        path.add(node.val);
        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                result.add(new ArrayList<>(path)); // NOTE: Should create a new list, because path is a reference.
            }
        }else {
            findPath(node.left, targetSum - node.val, path);
            findPath(node.right, targetSum - node.val, path);
        }
        path.removeLast();
    }
}
