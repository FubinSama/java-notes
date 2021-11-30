package 剑指offer.day8;

/**
 * <p>
 *  剑指 Offer 10- I. 斐波那契数列
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-09 18:40:56
 */
public class Solution10_1 {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1, c = a + b;
        for (int i = 3; i <= n; i++) {
            a = b;
            b = c;
            c = (a + b) % 1000000007;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new Solution10_1().fib(5));
    }
}
