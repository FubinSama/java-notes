package 剑指offer.day6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 *  剑指 Offer 32 - I. 从上到下打印二叉树
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 21:17:03
 */
public class Solution32_1 {

    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>(); // 用来存储节点
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {this.val = val;}
    }
}
