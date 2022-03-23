package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/21
 */
public class Question0002 {
    /**
     * 2. Add Two Numbers
     * <p>
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
     * order. and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero. except the number 0 itself.
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
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Two Numbers.
     * Memory Usage: 42.3 MB, less than 76.12% of Java online submissions for Add Two Numbers.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }

    private ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int addOne) {
        if (l1 == null && l2 == null) {
            if (addOne > 0) {
                return new ListNode(addOne);
            }
            return null;
        }
        int val1 = 0, val2 = 0;
        ListNode nodeNext1 = null, nodeNext2 = null;

        if (l1 != null) {
            val1 = l1.val;
            nodeNext1 = l1.next;
        }

        if (l2 != null) {
            val2 = l2.val;
            nodeNext2 = l2.next;
        }

        int val = val1 + val2 + addOne;
        addOne = val/10;
        val %= 10;


        ListNode node = new ListNode(val);
        node.next = addTwoNumbersHelper(nodeNext1, nodeNext2, addOne);
        return node;
    }

}
