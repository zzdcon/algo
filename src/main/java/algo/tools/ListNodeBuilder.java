package algo.tools;

import algo.dataStructure.ListNode;

public class ListNodeBuilder {
    public static ListNode build(int[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        for (int i : data) {
            head.next = new ListNode(i);
            head = head.next;
        }
        return dummy.next;
    }

}
