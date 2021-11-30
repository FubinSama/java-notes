package 高效制胜.day4;

/**
 * <p>
 *  322. 零钱兑换
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 00:46:03
 */
public class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[i]表示兑换金额i需要的最小硬币数
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1; // 表示不可能用金币凑出这种情况
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Solution322 solution = new Solution322();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(solution.coinChange(coins, amount));
    }
}
