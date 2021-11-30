package 剑指offer.day19;

/**
 * <p>
 *  剑指 Offer 68 - II. 二叉树的最近公共祖先
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-21 00:02:31
 */
public class Solution68_2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 定义null表示不含有这两个节点，如果含有某个某一个节点，就返回。如果含有最近公共祖先，则返回祖先
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
