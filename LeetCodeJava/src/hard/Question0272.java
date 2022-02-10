package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2021/12/27
 */
public class Question0272 {

    /**
     * 272. Closest Binary Search Tree Value II
     * <p>
     * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are
     * closest to the target. You may return the answer in any order.
     * <p>
     * You are guaranteed to have only one unique set of k values in the BST that are closest to the target
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
     *
     * Runtime: 2 ms, faster than 79.51% of Java online submissions for Closest Binary Search Tree Value II.
     * Memory Usage: 45.4 MB, less than 14.44% of Java online submissions for Closest Binary Search Tree Value II.
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> sortedValues = new ArrayList<>();
        inOrderTraverse(root, sortedValues);


        int leftIndex = -1;
        int rightIndex = sortedValues.size();

        for (int i = 0; i < sortedValues.size(); i++) {
            int value = sortedValues.get(i);
            if (value <= target) {
                leftIndex = i;
            }else {
                rightIndex = i;
                break;
            }
        }

        List<Integer> result = new ArrayList<>();
        while (k > 0) {
            k--;
            if (leftIndex < 0) {
                result.add(sortedValues.get(rightIndex));
                rightIndex ++;
                continue;
            }

            if (rightIndex >= sortedValues.size()) {
                result.add(sortedValues.get(leftIndex));
                leftIndex --;
                continue;
            }

            if (Math.abs(sortedValues.get(leftIndex) - target) > Math.abs(sortedValues.get(rightIndex) - target)){
                result.add(sortedValues.get(rightIndex));
                rightIndex ++;
            }else {
                result.add(sortedValues.get(leftIndex));
                leftIndex --;
            }

        }


        return result;
    }

    private void inOrderTraverse(TreeNode node, List<Integer> result){
        if (node == null) return;
        inOrderTraverse(node.left, result);
        result.add(node.val);
        inOrderTraverse(node.right, result);
    }

}
