package 剑指offer.day22;

/**
 * <p>
 *  剑指 Offer 56 - I. 数组中数字出现的次数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-23 19:40:32
 */
public class Solution56_1 {
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) xor ^= num; // 求 x ^ y
        // 将所有的数字分为两组，使得：
        // 1. 两个只出现一次的数字在不同的组中
        // 2. 相同的数字会被分到同一组中
        int mask = xor & (-xor); // 找到最低位为1的数
        int x = 0, y = 0;
        for (int num : nums) {
            if ((num & mask) == 0) x ^= num;
            else y ^= num;
        }
        return new int[] {x, y};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        Solution56_1 solution56_1 = new Solution56_1();
        int[] ints = solution56_1.singleNumbers(nums);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
