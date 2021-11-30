package 剑指offer.day11;

/**
 * <p>
 *  剑指 Offer 22. 链表中倒数第k个节点
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-12 19:16:03
 */
public class Solution22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        int length = 0;
        for (ListNode node = head; node != null; node = node.next) {
            length++;
        }
        if (k <= 0) throw new IllegalArgumentException("k must be greater than 0");
        int index = length - k;

        while (index-- > 0) head = head.next;

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
