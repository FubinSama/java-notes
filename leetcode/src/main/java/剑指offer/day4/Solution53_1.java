package 剑指offer.day4;

/**
 * <p>
 *  剑指 Offer 53 - I. 在排序数组中查找数字 I
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-05 19:18:10
 */
public class Solution53_1 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int li = 0, hi = nums.length - 1;
        while (li < nums.length && nums[li] != target) li ++;
        while (hi >= 0 && nums[hi] != target) hi --;
        return Math.max(hi - li + 1, 0);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,7,7,8,8,10};
        int target = 6;
        System.out.println(new Solution53_1().search(arr, target));
    }
}
