package 剑指offer.day13;

/**
 * <p>
 *  剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-15 00:32:09
 */
public class Solution21 {
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length < 2) return nums;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) left++;
            while (left < right && nums[right] % 2 == 0) right--;
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        Solution21 solution21 = new Solution21();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] result = solution21.exchange(nums);
        for (int j : result) {
            System.out.print(j + " ");
        }
    }
}
