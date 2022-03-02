package google.medium;

import com.sun.source.tree.Tree;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0109 {

    /**
     * 109. Convert Sorted List to Binary Search Tree
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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
     * Runtime: 1 ms, faster than 84.58% of Java online submissions for Convert Sorted List to Binary Search Tree.
     * Memory Usage: 43.2 MB, less than 85.42% of Java online submissions for Convert Sorted List to Binary Search Tree.
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        List<Integer> vals = new ArrayList<>();
        vals.add(head.val);
        while (head.next != null){
            head = head.next;
            vals.add(head.val);
        }

        return buildTree(vals, 0 , vals.size());
    }

    private TreeNode buildTree(List<Integer> vals, int start, int end) {
        if (end <= start) return null;
        int mid = (start + end - 1)/2;
        TreeNode root = new TreeNode(vals.get(mid));
        root.left = buildTree(vals, start, mid);
        root.right = buildTree(vals, mid + 1, end);
        return root;
    }

}
