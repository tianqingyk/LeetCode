package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0105 {

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
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
     * Runtime: 3 ms, faster than 83.17% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
     * Memory Usage: 41.8 MB, less than 68.07% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder,0 ,0, preorder.length);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int start1, int start2, int length) {
        if (length < 1) return null;

        TreeNode node = new TreeNode();
        node.val = preorder[start1];
        if (length == 1) return node;

        int rightStart = start2 + 1;
        for (int i = start2; i < start2 + length; i++) {
            if (inorder[i] == node.val) {
                rightStart = i + 1;
                break;
            }
        }

        node.left = buildTreeHelper(preorder, inorder, start1 + 1, start2, rightStart - start2 - 1);
        node.right = buildTreeHelper(preorder, inorder, rightStart - start2 + start1, rightStart, length - rightStart + start2);
        return node;
    }

    public static void main(String[] args) {
        Question0105 q = new Question0105();
        q.buildTree(new int[]{1,2,3}, new int[]{2,3,1});
    }
}
