package algo.leetcode.linkedList;

import algo.dataStructure.ListNode;
import algo.tools.ListNodeBuilder;

//反转一个单链表。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
// 进阶:
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
//
public class ReverseList {

    /**
     * 递归法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList(head.next);
        // head -> head.next 转换为 head.next -> head -> null;
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    public ListNode reverseList4(ListNode head) {
        ListNode before = new ListNode(0);
        before.next = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = before.next;
            before.next = cur;
            cur = next;
        }
        return before.next;
    }

    /**
     * 遍历法
     * 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。

     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！

     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/fan-zhuan-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;

    }

    public ListNode reverseList3(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode cur = head;
        while (cur.next != null) {
            ListNode t = cur.next;
            cur.next = t.next;
            t.next = prev.next;
            prev.next = t;
        }
        return prev.next;

    }

    public static void main(String[] args) {
        System.out.println(new ReverseList().reverseList3(ListNodeBuilder.build(new int[] {1, 2, 3})));
    }

}
