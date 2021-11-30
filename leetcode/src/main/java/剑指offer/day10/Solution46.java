package 剑指offer.day10;

/**
 * <p>
 *  剑指 Offer 46. 把数字翻译成字符串
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-11 19:49:06
 */
public class Solution46 {
    /*
    1 2 2 5 8
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = dp[1] + dp[0] = 2: ab、j
    dp[3] = dp[2] + dp[1] = 3: abb、jb、at
     */
    public int translateNum(int num) {
        if (num < 10) return 1;
        char[] chs = String.valueOf(num).toCharArray();
        int[] dp = new int[chs.length + 1]; // dp[i] 表示 num[0, i) 的翻译结果
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= chs.length; i++) {
            int cur = chs[i - 1] - '0';
            int pre = chs[i - 2] - '0';
            if (pre > 0 && pre * 10 + cur < 26) dp[i] = dp[i - 1] + dp[i - 2];
             else dp[i] = dp[i-1];
        }
        return dp[chs.length];
    }

    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        System.out.println(solution46.translateNum(12258));
    }
}
