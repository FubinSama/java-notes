package 高效制胜.day3;

/**
 * <p>
 *  70. 爬楼梯
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-05 19:33:51
 */
public class Solution70 {
    public int climbStairs(int n) {
        int a = 1, b = 1, c;
        while (n-- > 0) {
            c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        Solution70 solution70 = new Solution70();
        System.out.println(solution70.climbStairs(3));
    }
}
