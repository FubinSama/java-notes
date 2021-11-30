package 剑指offer.day11;

/**
 * <p>
 *  剑指 Offer 18. 删除链表的节点
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-12 19:09:06
 */
public class Solution18 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        ListNode res = new ListNode(0), pre = res;
        res.next = head;

        while (pre.next != null && pre.next.val != val) pre = pre.next;
        if (pre.next == null) return head; // 表明没有找到，此时不需要删除
        pre.next = pre.next.next; // 删除对应节点
        return res.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
