package 高效制胜.day6;

/**
 * <p>
 *  483. 最小好进制
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-08 20:53:57
 */
public class Solution483 {
    
    /*
    k^0 + k^1 + ... + k^x = (k ^ (x+1) - 1) / (k - 1)
    即求对于任意给定的x满足 (k ^ (x+1) - 1) / (k - 1) = n 的 最小的k的值
     */
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        throw new RuntimeException("not implemented");
    }
}
