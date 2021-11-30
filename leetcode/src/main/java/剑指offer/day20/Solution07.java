package 剑指offer.day20;

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  剑指 Offer 07. 重建二叉树
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-21 20:12:51
 */
public class Solution07 {

    // 用于快速定位根节点
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 前序遍历：NLR
        // 中序遍历：LNR
        // 前序遍历的第一个节点就是根节点，找到根节点的位置，然后把左右子树分别构造出来
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preSt, int preEd, int[] inorder, int inSt, int inEd) {
        if (preSt > preEd || inSt > inEd) return null;
        TreeNode root = new TreeNode(preorder[preSt]); // 前序遍历的第一个节点就是根节点
        int inIndex = indexMap.get(root.val); // 根节点在中序遍历中的位置
        root.left = buildTree(preorder, preSt + 1, preSt + inIndex - inSt, inorder, inSt, inIndex - 1);
        root.right = buildTree(preorder, preSt + inIndex - inSt + 1, preEd, inorder, inIndex + 1, inEd);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Solution07 solution = new Solution07();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = solution.buildTree(preorder, inorder);
        System.out.println(Arrays.toString(preorder));
        System.out.println(Arrays.toString(inorder));
        System.out.println(root);
    }
}

