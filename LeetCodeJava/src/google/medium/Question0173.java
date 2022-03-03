package google.medium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0173 {

    /**
     * 173. Binary Search Tree Iterator
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
     * Solution
     * Runtime: 28 ms, faster than 42.80% of Java online submissions for Binary Search Tree Iterator.
     * Memory Usage: 51.6 MB, less than 38.06% of Java online submissions for Binary Search Tree Iterator.
     */
    class BSTIterator {

        TreeNode root;
        public BSTIterator(TreeNode root) {
            this.root = root;
        }

        public int next() {
            TreeNode tmp = root;
            TreeNode previous = null;
            while (tmp != null){
                if (tmp.left != null) {
                    previous = tmp;
                    tmp = tmp.left;
                    continue;
                }
                if (previous == null) {
                    int val = root.val;
                    root = root.right;
                    return val;
                }
                int val = tmp.val;
                previous.left = tmp.right;
                return val;
            }
            return root.val;
        }

        public boolean hasNext() {
            return root != null;
        }
    }

}
