package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/3
 */
public class Question0206 {

    /**
     * 206. Reverse Linked List
     */
    class ListNode {
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

    /**
     * Solution
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
     * Memory Usage: 43.9 MB, less than 5.05% of Java online submissions for Reverse Linked List.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode previous = head;
        ListNode cur = head.next;
        head.next = null; // NOTICE!!!
        while (cur != null){
            ListNode next = cur.next;
            cur.next = previous;
            previous = cur;
            cur = next;
        }
        return previous;
    }
}
