package algo.leetcode.linkedList;

import algo.dataStructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode node = head;
        ListNode prev = null;
        ListNode start = null;
        ListNode end = null;
        for (int i=1; i<=n; i++) {
            if (i >= m) {
                if (i == m) {
                    start = node;
                }

                // 翻转
                ListNode tempNext = node.next;
                node.next = prev;
                prev = node;
                node = tempNext;
                if (i == n) {
                    end =
                }
            } else {
                prev = node;
                node = node.next;
            }
        }
        return head;
    }

    private ListNode getReverse
}