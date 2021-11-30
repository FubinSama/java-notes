package 剑指offer.day15;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  剑指 Offer 34. 二叉树中和为某一值的路径
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-16 21:53:16
 */
public class Solution34 {

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        pathSum(root, target, res, path);
        return res;
    }

    private void pathSum(TreeNode root, int target, List<List<Integer>> res, List<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null && target == root.val) {
            res.add(new ArrayList<>(path));
        }
        if (root.left != null) pathSum(root.left, target - root.val, res, path);
        if (root.right != null) pathSum(root.right, target - root.val, res, path);
        path.remove(path.size() - 1);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
