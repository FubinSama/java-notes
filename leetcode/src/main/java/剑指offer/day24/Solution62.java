package 剑指offer.day24;

/**
 * <p>
 *  剑指 Offer 62. 圆圈中最后剩下的数字
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-25 20:32:08
 */
public class Solution62 {
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        return (lastRemaining(n-1, m) + m) % n;
    }

    public static void main(String[] args) {
        Solution62 solution = new Solution62();
        System.out.println(solution.lastRemaining(5, 3));
    }
}
