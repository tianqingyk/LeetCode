package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0019 {

    /**
     * 19. Remove Nth Node From End of List
     * <p>
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
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

    /**
     * Solution 1
     * Runtime: 1 ms, faster than 61.16% of Java online submissions for Remove Nth Node From End of List.
     * Memory Usage: 42.1 MB, less than 25.27% of Java online submissions for Remove Nth Node From End of List.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode end = head;
        for (int i = 0; i < n; i++) {
            end = end.next;
            if (end == null) return head.next;
        }

        ListNode beforeRemove = head;
        while (end.next != null){
            beforeRemove = beforeRemove.next;
            end = end.next;
        }

        beforeRemove.next = beforeRemove.next.next;
        return head;
    }

}
