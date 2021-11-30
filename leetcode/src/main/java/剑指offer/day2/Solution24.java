package 剑指offer.day2;

/**
 * <p>
 *  剑指 Offer 24. 反转链表
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-03 12:41:21
 */
public class Solution24 {
    public ListNode reverseList(ListNode head) {
        ListNode res = new ListNode(0); // 头指针，为了一致性

        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = res.next;
            res.next = head;
            head = next;
        }

        return res.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
