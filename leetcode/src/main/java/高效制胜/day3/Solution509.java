package 高效制胜.day3;

/**
 * <p>
 *  509. 斐波那契数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-05 19:30:45
 */
public class Solution509 {
    public int fib(int n) {
        int a = 0, b = 1, c;
        while (n-- > 0) {
            c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new Solution509().fib(4));
    }
}
