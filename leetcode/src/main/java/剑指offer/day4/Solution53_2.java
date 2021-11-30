package 剑指offer.day4;

/**
 * <p>
 *  剑指 Offer 53 - II. 0～n-1中缺失的数字
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-05 19:24:35
 */
public class Solution53_2 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        // 计算总和
        int sum = (n * (n + 1)) / 2;
        // 总和减去元素，剩下的就是缺失的数
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }
}
