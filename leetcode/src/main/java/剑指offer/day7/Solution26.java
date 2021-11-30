package 剑指offer.day7;

import javax.swing.tree.TreeNode;

/**
 * <p>
 *  剑指 Offer 26. 树的子结构
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-08 19:05:21
 */
public class Solution26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        return isSame(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSame(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && isSame(A.left, B.left) && isSame(A.right, B.right);
    }

    static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {this.val = val;}
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        TreeNode B = new TreeNode(4);
        B.left = new TreeNode(1);
        System.out.println(new Solution26().isSubStructure(root, B));
    }
}
