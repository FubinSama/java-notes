package 剑指offer.day20;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  剑指 Offer 16. 数值的整数次方
 *  快速幂算法
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-21 20:43:48
 */
public class Solution16 {

    public double myPow(double x, int n) {
        long m = n;
        if (m < 0) {
            m = -m;
            x = 1 / x;
        }
        double res = 1;
        while (m > 0) {
            if ((m & 1) == 1) res *= x;
            x *= x;
            m >>= 1;
        }
        return res;
    }

}
