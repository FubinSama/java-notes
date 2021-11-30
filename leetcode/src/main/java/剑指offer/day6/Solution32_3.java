package 剑指offer.day6;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  剑指 Offer 32 - III. 从上到下打印二叉树 III
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 21:34:14
 */
public class Solution32_3 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> cur = new LinkedList<>(), back = new LinkedList<>(), tmp;
        cur.add(root);
        LinkedList<Integer> list = new LinkedList<>();
        int order = 1; // 当前处理的行数
        while (!cur.isEmpty()) {
            TreeNode node = cur.poll();
            if (order % 2 == 1) list.addLast(node.val); // 奇数行要求从左往右，即该行的结果集顺序与队列顺序相同
            else list.addFirst(node.val); // 偶数行要求从右往左，即该行的结果集顺序与队列顺序相反
             if (node.left != null) back.add(node.left);
             if (node.right != null) back.add(node.right);
             if (cur.isEmpty()) {
                 res.add(list); // 将当前行的数据放入结果集
                 list = new LinkedList<>(); // 新建一行
                 tmp = cur;
                 cur = back;
                 back = tmp;
                 order++; // 表明下一行开始
             }
        }
        return res;
    }
}
