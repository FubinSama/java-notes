package 剑指offer.day9;

/**
 * <p>
 *  剑指 Offer 47. 礼物的最大价值
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-10 20:21:52
 */
public class Solution47 {
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length]; // dp[i][j] 表示到第i行第j列的最大价值
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 5},
                {3, 2, 1}
        };
        System.out.println(new Solution47().maxValue(grid));
    }

}
