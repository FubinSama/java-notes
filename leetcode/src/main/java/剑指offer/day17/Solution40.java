package 剑指offer.day17;

import java.util.Arrays;

/**
 * <p>
 *  剑指 Offer 40. 最小的k个数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-20 22:36:10
 */
public class Solution40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }
}
