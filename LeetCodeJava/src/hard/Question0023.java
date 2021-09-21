package hard;

import java.awt.*;

/**
 * @author yangke
 * @projectName LeetCodeJava
 * @date 9/14/21
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class Question0023 {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }
        if (lists.length <= 1){
            return lists[0];
        }
        int l = (lists.length+1)/2;
        ListNode[] tmp = new ListNode[l];
        for (int i=0; i < l; i ++){
            if (2*i+1 >=  lists.length){
                tmp[i] = lists[2*i];
            }else {
                tmp[i] = mergeTwoNode(lists[2*i], lists[2*i+1]);
            }
        }
        return mergeKLists(tmp);
    }

    public static ListNode mergeTwoNode(ListNode leftNode, ListNode rightNode) {
        ListNode result = null;
        ListNode next = null;
        ListNode left = leftNode;
        ListNode right = rightNode;
        if (left.val >= right.val) {
            result = new ListNode(right.val);
            next = result;
            right = right.next;
        }else { // left.val < right.val
            result = new ListNode(left.val);
            next = result;
            left = left.next;
        }

        for (;;){
            if (left == null) {
                next.next = right;
                break;
            }
            if (right == null) {
                next.next = left;
                break;
            }

            if (left.val >= right.val) {
                next.next = new ListNode(right.val);
                next = next.next;
                right = right.next;
            }else { // left.val < right.val
                next.next = new ListNode(left.val);
                next = next.next;
                left = left.next;
            }
        }
        return result;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(9);
        node4.next = node5;
        node5.next = node6;

        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(3);
        ListNode node9 = new ListNode(9);
        node7.next = node8;
        node8.next = node9;

//        mergeTwoNode(node1,node4).printVals();
        ListNode[] lists = {node1, node4, node7};
        mergeKLists(lists).printVals();
    }


}

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

    void printVals(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.val);
        ListNode next = this.next;
        while (next != null){
            sb.append(next.val);
            next = next.next;
        }
        System.out.println(sb.toString());
    }
}
