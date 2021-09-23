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

    public ListNode mergeKLists(ListNode[] lists) {
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

    public ListNode mergeTwoNode(ListNode leftNode, ListNode rightNode) {
        if(leftNode == null) {
            return rightNode;
        }

        if(rightNode == null) {
            return leftNode;
        }
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

    }


}

