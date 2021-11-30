package 剑指offer.day8;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *  剑指 Offer 63. 股票的最大利润
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-09 18:49:55
 */
public class Solution63 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution63 solution63 = new Solution63();
        int[] prices = {7,6,4,3,1};
        System.out.println(solution63.maxProfit(prices));
    }
}
