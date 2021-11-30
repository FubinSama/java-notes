package 剑指offer.day2;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  剑指 Offer 35. 复杂链表的复制
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-03 12:46:19
 */
public class Solution35 {

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node res = new Node(0); // 头指针，为了一致性
        Node cur1 = head, cur2 = res;

        Map<Node, Node> map = new HashMap<>(); // 存储旧节点和新节点的映射

        while (cur1 != null) { // 先进行常规链表的复制，并记录旧节点和新节点的映射
            cur2.next = new Node(cur1.val);
            map.put(cur1, cur2.next);
            cur2 = cur2.next; cur1 = cur1.next;
        }

        // 进行random指针的修复
        cur1 = head; cur2 = res.next;
        while (cur1 != null) {
            cur2.random = map.get(cur1.random);
            cur2 = cur2.next; cur1 = cur1.next;
        }

        return res.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
