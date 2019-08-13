package algo.leetcode.linkedList;//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
//
// 你应当保留两个分区中每个节点的初始相对位置。 
//
// 示例: 
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
// 
//

import algo.dataStructure.ListNode;
import algo.tools.ListNodeBuilder;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Partition {
    // 方法一，遍历移动： 首先找到第一个大于等于x的节点位置，以此节点作为分界点，一次将该节点右侧小于x的节点移动到该
    // 节点左边
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode insert;
        while (cur.next != null && cur.next.val < x) {
            cur = cur.next;
        }
        insert = cur;
        while (cur != null && cur.next != null) {
            if (cur.next.val < x) {
                ListNode t = cur.next;
                cur.next = t.next;
                t.next = insert.next;
                insert.next = t;
                insert = insert.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    // 方法二： 新建一个链表存储大于等于x的节点，并将这些节点从原链表中移除，最后拼接原链表和新链表
    public ListNode partition2(ListNode head, int x) {
        ListNode dummy2 = new ListNode(0);
        ListNode after = dummy2;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.val >= x) {
                after.next = new ListNode(head.val);
                after = after.next;
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        prev.next = dummy2.next;
        return dummy.next;
    }

    // 方法三： 新建两个链表
    public ListNode partition3(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode l1 = dummy1;
        ListNode dummy2 = new ListNode(0);
        ListNode l2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                l1.next = new ListNode(head.val);
                l1 = l1.next;
            } else {
                l2.next = new ListNode(head.val);
                l2 = l2.next;
            }
            head = head.next;
        }
        l1.next = dummy2.next;
        return dummy1.next;
    }



    public static void main(String[] args) {
        System.out.println(new Partition().partition2(ListNodeBuilder.build(new int[] {1, 4,3, 2,
        5, 2}), 3));
    }
}