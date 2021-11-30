package 高效制胜.day6;

/**
 * <p>
 *  279. 完全平方数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-08 20:37:24
 */
public class Solution279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 凑成数字n的方案数
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution279 solution279 = new Solution279();
        System.out.println(solution279.numSquares(12));
    }
}
