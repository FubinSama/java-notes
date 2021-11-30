package 剑指offer.day6;

import java.util.*;

/**
 * <p>
 *  剑指 Offer 32 - II. 从上到下打印二叉树 II
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 21:24:43
 */
public class Solution32_2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>(), queueBack = new LinkedList<>(), tmp;
        queue.add(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queueBack.add(node.left);
            if (node.right != null) queueBack.add(node.right);
            if (queue.isEmpty()) {
                res.add(list);
                list = new LinkedList<>();
                tmp = queue;
                queue = queueBack;
                queueBack = tmp;
            }
        }
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
