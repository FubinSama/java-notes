package 剑指offer.day8;

/**
 * <p>
 *  剑指 Offer 10- II. 青蛙跳台阶问题
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-09 18:47:41
 */
public class Solution10_2 {

    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        int a = 1, b = 1, c = a + b;
        for (int i = 3; i <= n; i++) {
            a = b;
            b = c;
            c = (a + b) % 1000000007;
        }
        return c;
    }

    public static void main(String[] args) {
        Solution10_2 solution = new Solution10_2();
        System.out.println(solution.numWays(7));
    }
}
