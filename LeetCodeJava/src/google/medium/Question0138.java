package google.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0138 {

    /**
     * 138. Copy List With Random Pointer
     */
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Solution
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Copy List with Random Pointer.
     * Memory Usage: 46.4 MB, less than 5.22% of Java online submissions for Copy List with Random Pointer.
     */
    Map<Node, Node> visitedMap = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        if (visitedMap.containsKey(head)) return visitedMap.get(head);
        Node newNode = new Node(head.val);
        visitedMap.put(head, newNode);
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }
}
