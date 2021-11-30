package 剑指offer.day15;

/**
 * <p>
 *  剑指 Offer 54. 二叉搜索树的第k大节点
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-17 20:22:36
 */
public class Solution54 {

    public int kthLargest(TreeNode root, int k) {
        int[] res = new int[1];
        inOrder(root, k, res);
        return res[0];
    }

    private int inOrder(TreeNode root, int k, int[] res) {
        if (root == null) return 0;
        int rc = inOrder(root.right, k, res);
        if (rc + 1 == k) {
            res[0] = root.val;
        }
        int lc = inOrder(root.left, k - rc - 1, res);
        return rc + lc + 1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(2);
        System.out.println(new Solution54().kthLargest(root, 1));
    }

}
