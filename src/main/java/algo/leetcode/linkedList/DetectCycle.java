package algo.leetcode.linkedList;//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 说明：不允许修改给定的链表。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：tail connects to node index 1
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：tail connects to node index 0
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：no cycle
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//你是否可以不用额外空间解决此题？ 
//

import algo.dataStructure.ListNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class DetectCycle {

    // 方法一： 通过hashSet 存储遍历过的节点
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }

            set.add(node);
            node = node.next;

        }
        return  null;
    }

    // 方法一： 通过快慢指针
    // 慢指针每次移动一步，快指针每次移动两步，快指针跟慢指针重合时
    // 慢指针继续与从头开始的指针一起移动，相遇的节点即为环开始节点
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode p = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        while (p != null) {
            if (p == slow) {
                return p;
            }
            p = p.next;
            slow = slow.next;
        }
        return null;
    }
}