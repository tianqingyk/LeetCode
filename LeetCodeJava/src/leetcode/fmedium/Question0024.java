package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0024 {

    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.
     * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
     * Memory Usage: 42 MB, less than 20.64% of Java online submissions for Swap Nodes in Pairs.
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

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode before = new ListNode();
        before.next = head;
        head = before;

        ListNode next, next2, next3;
        while (before.next != null && before.next.next != null) {
            next = before.next;
            next2 = next.next;
            next3 = next2.next;

            before.next = next2;
            next2.next = next;
            next.next = next3;
            before = next;
        }
        return head.next;
    }

    /**
     * Solution 2 Copy from Solution
     */
    public ListNode swapPairs2(ListNode head) {

        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next  = swapPairs2(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }
}
