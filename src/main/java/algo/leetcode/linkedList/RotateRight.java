package algo.leetcode.linkedList;//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
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
class RotateRight {
    // 方法一： 快慢指针， 当k>n时，k=k%n, 重新调用该函数
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        int cnt = 1;
        for (int i=1; i<=k; i++) {
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
            cnt++;
        }
        if (cnt <= k) {
            k = k % cnt;
            return rotateRight(head, k);
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //fast下一个节点连接到原链表头上，新的链表头为slow的下一个节点，断开slow和下一个节点，slow指向null
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    // 方法二： 找到倒数第n-k-1个链表位置，该位置的下一个节点即为新节点，断开该位置
    // 第一步，数出链表长度n, k=k%n, 将链表形成环
    // 第二步，从head开始，数到n-k-1个节点，断开节点
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode findEnd = head;
        int cnt = 1;
        while (findEnd.next != null) {
           findEnd = findEnd.next;
           cnt++;
        }
        k = k % cnt;
        if (k == 0) {
            return head;
        }
        findEnd.next = head;
        for(int i=0; i<cnt-k-1; i++) {
            head = head.next;
        }
        ListNode temp = head;
        head = head.next;
        temp.next = null;
        return head;
    }

}