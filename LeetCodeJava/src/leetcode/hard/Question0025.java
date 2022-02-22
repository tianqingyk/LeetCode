package leetcode.hard;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 9/21/21
 * <p>
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 */
public class Question0025 {

    public static class ListNode {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode before = null;
        ListNode tmp = head;
        for (int i = 0; i < k; i++) {
            if (tmp == null) {
                return head;
            }
            before = tmp;
            tmp = tmp.next;
        }
        before.next = null;
        ListNode result = reverseListNode(head);
        head.next = reverseKGroup(tmp, k);
        return result;
    }

    private ListNode reverseListNode(ListNode node) {
        ListNode result = node;
        ListNode next = node.next;
        ListNode tmp = null;
        for (; ; ) {
            if (next == null) {
                return result;
            }
            tmp = result;
            result = next;
            next = next.next;
            result.next = tmp;
        }
    }

    public static void main(String[] args){
        Question0025 q = new Question0025();
        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;

        node = q.reverseKGroup(node, 3);
        System.out.println(node.val);
    }

}
