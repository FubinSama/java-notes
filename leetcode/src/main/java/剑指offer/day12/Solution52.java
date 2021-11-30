package 剑指offer.day12;

/**
 * <p>
 *  剑指 Offer 52. 两个链表的第一个公共节点
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-13 22:30:08
 */
public class Solution52 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode l1 = headA, l2 = headB;
        boolean l1Flag = true, l2Flag = true;
        while (l1 != null && l2 != null) {
            if (l1 == l2) return l1;
            l1 = l1.next; l2 = l2.next;
            if (l1 == null && l1Flag) {
                l1 = headB; l1Flag = false;
            }
            if (l2 == null && l2Flag) {
                l2 = headA; l2Flag = false;
            }
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
