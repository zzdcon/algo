package algo.leetcode.linkedList;//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 示例 : 
//
// 给定这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 说明 : 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
// 
//

import algo.dataStructure.ListNode;
import algo.tools.ListNodeBuilder;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ReverseKGroup {
    // 方法一： 递归法
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = k;
        ListNode p = head;
        while (p != null && n-- > 0) {
            p = p.next;
        }
        if (n > 0) {
            return head;
        }

        ListNode nextH = reverseKGroup(p, k);
        while (k-- > 0) {
            ListNode next = head.next;
            head.next = nextH;
            nextH = head;
            head = next;
        }
        return nextH;
    }

    // 方法二： 遍历法，先遍历一遍判断k是否大于链表的长度，小于链表长度直接返回；否则每k个翻转
    public ListNode reverseKGroup2(ListNode head, int k) {
        int length = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            length++;
        }

        if (length<k) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur;
        while (length >= k) {
            cur = prev.next;
            for (int i=1; i<k; i++) {
                ListNode t = cur.next;
                cur.next = t.next;
                t.next = prev.next;
                prev.next = t;
            }
            prev = cur;
            length = length - k;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseKGroup().reverseKGroup(ListNodeBuilder.build(new int[] {1, 2, 3, 4, 5}), 2));
    }
}