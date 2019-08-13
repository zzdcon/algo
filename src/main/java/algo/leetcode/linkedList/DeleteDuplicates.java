package algo.leetcode.linkedList;//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
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
class DeleteDuplicates {
    // 方法一： 遍历
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-10000);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null) {
            if (fast.next != null && fast.val == fast.next.val) {
                int temp = fast.val;
                while (fast != null && fast.val == temp) {
                    fast = fast.next;
                }
            } else {
                slow.next = fast;
                slow = fast;
                fast = fast.next;
            }
        }
        slow.next = fast;
        return dummy.next;

    }

    // 方法二： 递归
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        if (head.val == next.val) {
            while (next != null && head.val == next.val) {
                next = next.next;
            }
            return deleteDuplicates2(next);
        } else {
            head.next = deleteDuplicates2(head.next);
            return head;
        }

    }

}