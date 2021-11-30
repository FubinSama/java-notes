package 剑指offer.day18;

/**
 * <p>
 *  剑指 Offer 55 - II. 平衡二叉树
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-20 23:40:30
 */
public class Solution55_2 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
