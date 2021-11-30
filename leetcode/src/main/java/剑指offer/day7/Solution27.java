package 剑指offer.day7;

/**
 * <p>
 *  剑指 Offer 27. 二叉树的镜像
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-08 19:13:03
 */
public class Solution27 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = mirrorTree(root.right);
        res.right = mirrorTree(root.left);
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }
}
