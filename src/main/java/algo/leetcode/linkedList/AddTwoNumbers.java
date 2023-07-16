package algo.leetcode.linkedList;

//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

import algo.dataStructure.ListNode;
import algo.dataStructure.TreeNode;
import algo.tools.ListNodeBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = getStackFromList(l1);
        Stack<Integer> stack2 = getStackFromList(l2);
        int carry = 0;
        ListNode dummy = new ListNode(0);
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            Integer val1 = stack1.isEmpty() ? 0 : stack1.pop();
            Integer val2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = val1 + val2 + carry;
            int val = sum % 10;
            carry = sum >= 10 ? 1 : 0;
            ListNode next = dummy.next;
            dummy.next = new ListNode(val);
            dummy.next.next = next;
        }
        return dummy.next == null ? dummy : dummy.next;
    }

    private Stack<Integer> getStackFromList(ListNode l) {
        Stack<Integer> stack = new Stack<>();
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }

    private long getNumFromList(ListNode l) {
        long ans = 0;
        while (l != null) {
            ans = ans*10 +  l.val ;
            l=l.next;
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new AddTwoNumbers().addTwoNumbers(ListNodeBuilder.build(new int[]{7,2,4,3}), ListNodeBuilder.build(new int[]{5,6,4})));
//        System.out.println(new AddTwoNumbers().addTwoNumbers(ListNodeBuilder.build(new int[]{3,9,9,9,9,9,9,9,9,9}), ListNodeBuilder.build(new int[]{7})));
//
//        Queue<TreeNode> queue = new LinkedList<>();//通过队列实现bfs
//        queue.poll();
        int i = 1;
        System.out.println(i<<1);
        System.out.println(i<<1|1);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
