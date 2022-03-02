package google.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/3/2
 */
public class Question0141 {

    /**
     * 141. Linked List Cycle
     */

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Solution 1
     * Runtime: 11 ms, faster than 5.61% of Java online submissions for Linked List Cycle.
     * Memory Usage: 47.1 MB, less than 7.40% of Java online submissions for Linked List Cycle.
     */
    Set<ListNode> visited = new HashSet<>();
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        if (visited.contains(head)) return true;
        visited.add(head);
        return hasCycle(head.next);
    }

    /**
     * Solution 2 Copy From Solution
     * Floyd's Cycle Finding Algorithm
     */

    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head; // slow = k
        ListNode fast = head.next; // fast = 2*k + 1  when k = n- 1, slow = n-1(mod n) is equal to fast = n - 1(mod n).
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
