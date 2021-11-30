package 剑指offer.day18;

/**
 * <p>
 *  剑指 Offer 55 - I. 二叉树的深度
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-20 23:38:58
 */
public class Solution55_1 {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
