package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/1
 */
public class Question0117 {

    /**
     * 117. Populating Next Right Pointers in Each Node II
     */

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 78.55% of Java online submissions for Populating Next Right Pointers in Each Node II.
     * Memory Usage: 45.1 MB, less than 18.44% of Java online submissions for Populating Next Right Pointers in Each Node II.
     */
    public Node connect(Node root) {
        populateNodes(new ArrayList<>() {{
            if (root != null) add(root);
        }});
        return root;
    }

    private void populateNodes(List<Node> nodes) {
        if (nodes.isEmpty()) return;
        List<Node> newNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            Node cur = nodes.get(i);
            if (i + 1 < nodes.size()) cur.next = nodes.get(i + 1);
            if (cur.left != null) newNodes.add(cur.left);
            if (cur.right != null) newNodes.add(cur.right);
        }
        populateNodes(newNodes);
    }
}
