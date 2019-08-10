package algo.leetcode.linkedList;

import algo.dataStructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        head = pre;
        ListNode cur = pre.next;
        ListNode next;
        ListNode temp;
        for (int j = 0; j < n - m; j++) {
            temp = head.next;
            next = cur.next.next;
            head.next = cur.next;
            head.next.next = temp;
            cur.next = next;
        }
        return dummy.next;
    }
}