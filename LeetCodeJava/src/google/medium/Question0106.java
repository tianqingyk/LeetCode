package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0106 {

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
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
     * Runtime: 4 ms, faster than 57.91% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
     * Memory Usage: 44.5 MB, less than 28.23% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
     */
    int[] inorder, postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return buildTreeHelper(0, inorder.length, 0, postorder.length);
    }

    public TreeNode buildTreeHelper(int start1, int end1, int start2, int end2) {
        if (end1 <= start1 || end2 <= start2) return null;

        TreeNode node = new TreeNode();
        node.val = postorder[end2-1];

        int rootIndex1 = start1;
        for (int i = start1; i < end1; i++) {
            if (inorder[i] == node.val) {
                rootIndex1 = i;
                break;
            }
        }

        node.left = buildTreeHelper(start1, rootIndex1,  start2, rootIndex1 - start1 + start2);
        node.right = buildTreeHelper(rootIndex1 + 1, end1, rootIndex1 - start1 + start2, end2 -1);
        return node;
    }
}
