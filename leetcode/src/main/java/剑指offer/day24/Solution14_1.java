package 剑指offer.day24;

import java.util.Arrays;

/**
 * <p>
 *  剑指 Offer 14- I. 剪绳子
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-25 19:48:52
 */
public class Solution14_1 {
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
        Solution14_1 solution14_1 = new Solution14_1();
        System.out.println(solution14_1.cuttingRope(10));
    }
}
