package algo.leetcode.linkedList;//给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
//

import algo.dataStructure.ListNode;
import algo.tools.ListNodeBuilder;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = reverList(slow.next);
        slow.next = null;
        ListNode left = head;
        while (right != null) {
            ListNode next = right.next;
            right.next = left.next;
            left.next = right;
            right = next;
            left = left.next.next;
        }

    }

    private ListNode reverList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = reverList(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }

    public static void main(String[] args) {
        ListNode node = ListNodeBuilder.build(new int[] {1, 2, 3, 4});
        new ReorderList().reorderList(node);
    }
}