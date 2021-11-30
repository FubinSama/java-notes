package 剑指offer.day7;

/**
 * <p>
 *  剑指 Offer 28. 对称的二叉树
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-08 19:19:46
 */
public class Solution28 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }
}
