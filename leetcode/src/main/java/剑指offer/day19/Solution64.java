package 剑指offer.day19;

/**
 * <p>
 *  剑指 Offer 64. 求1+2+…+n
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-20 23:43:16
 */
public class Solution64 {
    public int sumNums(int n) {
        boolean flag = (n > 0) && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
