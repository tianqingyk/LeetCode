package google.easy;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/23
 */
public class Question0021 {

    /**
     * 21. Merge Two Sorted Lists
     * You are given the heads of two sorted linked lists list1 and list2.
     * <p>
     * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
     * <p>
     * Return the head of the merged linked list.
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
     * Memory Usage: 42.8 MB, less than 29.79% of Java online submissions for Merge Two Sorted Lists.
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode before = new ListNode();
        ListNode next = before;
        while (list1 != null || list2 != null){
            if (list1 == null){
                next.next = list2;
                list2 = list2.next;
            }else if (list2 == null){
                next.next = list1;
                list1 = list1.next;
            }else if (list1.val > list2.val) {
                next.next = list2;
                list2 = list2.next;
            }else {
                next.next = list1;
                list1 = list1.next;
            }
            next = next.next;
        }
        return before.next;
    }
}
