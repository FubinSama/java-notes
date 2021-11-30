package 剑指offer.day21;

/**
 * <p>
 *  剑指 Offer 15. 二进制中1的个数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-22 21:20:28
 */
public class Solution15 {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n & 1;
            n >>>= 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new Solution15().hammingWeight(11));
    }
}
