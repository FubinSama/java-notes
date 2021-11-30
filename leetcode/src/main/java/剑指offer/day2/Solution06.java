package 剑指offer.day2;

/**
 * <p>
 * 剑指 Offer 06. 从尾到头打印链表
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-03 12:35:34
 */
public class Solution06 {
    public int[] reversePrint(ListNode head) {
        ListNode cur = head;
        // 第一次循环求的链表的长度，也是结果集的长度
        int cnt = 0;
        while(cur != null) {
            cnt++;
            cur = cur.next;
        }
        int[] res = new int[cnt];
        // 第二次循环顺序遍历链表，将结果倒序放入结果集中
        cur = head;
        while (cur != null) {
            res[--cnt] = cur.val;
            cur = cur.next;
        }
        return res;
    }
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
