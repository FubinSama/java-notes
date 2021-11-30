package 高效制胜.day4;

import java.util.Arrays;

/**
 * <p>
 *  416. 分割等和子集
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-06 23:51:11
 */
public class Solution416 {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1]; // 是否可以用数组中的元素凑出该数（数用索引表示）
        /*
        对于数列a, b, c，我们可以用它们生成：
        0   a?  b?  c?
            0
                0
                    0
                    c
                b
                    b
                    b+c
            a
                a
                    a
                    a+b
                a+b
                    a+b
                    a+b+c
        如果我们用dp[i]来表示前j个数字组成的序列能否表示出数值i,
        那么引入第j+1个数字后，dp[i] = dp[i](表示不选用第j+1个数字) || dp[i - nums[j]]（表示选用第j+1个数字）
         */
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Solution416 solution416 = new Solution416();
        int[] nums = {1, 2, 5};
        System.out.println(solution416.canPartition(nums));
    }
}
