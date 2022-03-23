package leetcode.fmedium;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/28
 */
public class Question0092 {

    /**
     * 92. Reverse Linked List II
     * https://leetcode.com/problems/reverse-linked-list-ii/
     */

    /**
     * Solution 1
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
     * Memory Usage: 42.1 MB, less than 14.84% of Java online submissions for Reverse Linked List II.
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

    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode leftNodePrevious = null;
        ListNode leftNode = head;
        for (int i = 2; i <= left; i++) {
            leftNodePrevious = leftNode;
            leftNode = leftNode.next;
        }

        ListNode previousNode = leftNode;
        ListNode currentNode = previousNode.next;
        for (int i = left + 1; i <= right; i++) {
            ListNode nextCurrentNode = currentNode.next;
            currentNode.next = previousNode;

            previousNode = currentNode;
            currentNode = nextCurrentNode;
        }

        if (leftNodePrevious != null) leftNodePrevious.next = previousNode;
        else head = previousNode;
        leftNode.next = currentNode;

        return head;
    }

    /**
     * Solution 2 Copy From Solution
     * Recursion
     */

    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse(ListNode node, int left, int right) {

        // base case. Don't proceed any further
        if (right == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        node = node.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (left > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(node, left - 1, right - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == node || node.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = node.val;
            node.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, left, right);
        return head;
    }

    /**
     * Solution 3 Copy From Solution
     * Iterative Link Reversal
     */
    public ListNode reverseBetween3(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }


}
