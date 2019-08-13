package algo.leetcode.linkedList;//删除链表中等于给定值 val 的所有节点。
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
//

import algo.dataStructure.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.val == val) {
                head = head.next;
                prev.next = head;
            } else {
                head = head.next;
                prev = prev.next;
            }

        }
        return dummy.next;
    }
}