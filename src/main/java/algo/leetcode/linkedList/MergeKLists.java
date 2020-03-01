package algo.leetcode.linkedList;//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
//

import algo.dataStructure.ListNode;
import algo.tools.ListNodeBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeKLists {
    // 分治法： 每两个链表合并一次
    // 时间复杂度： O(Nlogk)
    // 空间复杂度 O(1)
    // 6ms, 81.61%; 42.6M, 71.91%
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
       return mergeKlists(lists, 0, lists.length-1);
    }

    private ListNode mergeKlists(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        int mid = (l + r) / 2;
        return merge2Lists(mergeKlists(lists, l, mid), mergeKlists(lists, mid+1, r));
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }


    // 优先级队列 最小顶堆法 14ms, 53%; 43M, 66%
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val -o2.val);
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            cur.next = poll;
            cur = cur.next;
            if (poll.next != null) {
                queue.add(poll.next);
            }
        }
        return dummy.next;

    }



    public static void main(String[] args) {
        ListNode l1 = ListNodeBuilder.build(new int[] {1, 4, 5});
        ListNode l2 = ListNodeBuilder.build(new int[] {1, 3, 4});
        ListNode l3 = ListNodeBuilder.build(new int[] {2, 6});
        System.out.println(new MergeKLists().mergeKLists2(new ListNode[]{l1, l2, l3}));
    }


}