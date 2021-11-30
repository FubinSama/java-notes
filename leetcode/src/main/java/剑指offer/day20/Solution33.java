package 剑指offer.day20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  剑指 Offer 33. 二叉搜索树的后序遍历序列
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-21 20:57:09
 */
public class Solution33 {

    // 后续遍历：LRN，二叉搜索树：所有的左子树都比根节点小，所有的右子树都比根节点大
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) return true;
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int st, int ed) {
        if (ed - st <= 1) return true; // 只有一个元素，则是二叉搜索树
        int root = postorder[ed];
        int i = st;
        while (postorder[i] < root) i++; // 找到第一个大于根节点的元素，即右子树的最左节点，其左边即为左子树
        // 验证右子数是否满足二叉搜索树的性质
        for (int j = i; j < ed; j++) {
            if (postorder[j] < root) return false;
        }
        return verifyPostorder(postorder, st, i - 1) // 验证左子数是否满足二叉搜索树的性质
                && verifyPostorder(postorder, i, ed - 1); // 验证右子数是否满足二叉搜索树的性质
    }

}
