package leetcode.fmedium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/25/2022
 */
public class Question0099 {

    /**
     * 99. Recover Binary Search Tree
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
     * Runtime: 10 ms, faster than 5.15% of Java online submissions for Recover Binary Search Tree.
     * Memory Usage: 47.4 MB, less than 46.10% of Java online submissions for Recover Binary Search Tree.
     */

    public void recoverTree(TreeNode root) {
        dfs(root, new ArrayList<>());
    }

    private void dfs(TreeNode root, List<TreeNode> cache) {
        if (root == null) return;
        dfs(root.left, cache);
        TreeNode curNode = root;
        for (int i = cache.size() - 1; i >= 0; i--) {
            TreeNode node = cache.get(i);
            if (curNode.val < node.val) {
                int tmp = curNode.val;
                curNode.val = node.val;
                node.val = tmp;
                curNode = node;
            }
        }
        cache.add(root);
        dfs(root.right, cache);
    }

    /**
     * Solution 2
     * Runtime: 2 ms, faster than 87.51% of Java online submissions for Recover Binary Search Tree.
     * Memory Usage: 42.5 MB, less than 84.21% of Java online submissions for Recover Binary Search Tree.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */

    public void inorder(TreeNode root, List<TreeNode> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root);
        inorder(root.right, nums);
    }

    public TreeNode[] findTwoSwapped(List<TreeNode> nums) {
        int n = nums.size();
        TreeNode x = null, y = null;
        boolean swapped_first_occurrence = false;
        for(int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1).val < nums.get(i).val) {
                y = nums.get(i + 1);
                if (!swapped_first_occurrence) {
                    // first swap occurrence
                    x = nums.get(i);
                    swapped_first_occurrence = true;
                } else {
                    // second swap occurrence
                    break;
                }
            }
        }
        return new TreeNode[]{x, y};
    }

    public void recoverTree2(TreeNode root) {
        List<TreeNode> nums = new ArrayList();
        inorder(root, nums);
        TreeNode[] swapped = findTwoSwapped(nums);
        int tmp = swapped[0].val;
        swapped[0].val = swapped[1].val;
        swapped[1].val = tmp;
    }

    /**
     * Solution 3
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Recover Binary Search Tree.
     * Memory Usage: 42 MB, less than 96.99% of Java online submissions for Recover Binary Search Tree.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */


    boolean isFirst = false;
    public TreeNode inorder(TreeNode root, TreeNode pre) {
        if (root == null) return pre;
        pre = inorder(root.left, pre);
        if (pre != null && pre.val > root.val){
            y = root;
            if(!isFirst) {
                x = pre;
                isFirst = true;
            }else {
                return root;
            }
        }
        return inorder(root.right, root);
    }

    TreeNode x =null, y = null;
    public void recoverTree3(TreeNode root) {
        TreeNode pre = null;
        inorder(root, pre);
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }
}
