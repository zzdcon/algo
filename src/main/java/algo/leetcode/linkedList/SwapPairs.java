package algo.leetcode.linkedList;//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
//

import algo.dataStructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode first, second, third;
        while (cur.next !=null && cur.next.next != null) {
            first = cur.next;
            second = cur.next.next;
            third = cur.next.next.next;
            cur.next = second;
            cur.next.next = first;
            cur.next.next.next = third;
            cur = cur.next.next;
        }
        return dummy.next;
    }
}