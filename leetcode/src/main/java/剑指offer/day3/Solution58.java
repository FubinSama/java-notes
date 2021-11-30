package 剑指offer.day3;

/**
 * <p>
 *  剑指 Offer 58 - II. 左旋转字符串
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-04 09:04:50
 */
public class Solution58 {
    public String reverseLeftWords(String s, int n) {
        n %= s.length();
//        return s.substring(n) + s.substring(0, n); // 内存消耗太大了，换一种
        char[] res = new char[s.length()];
        char[] chs = s.toCharArray();
        int x = chs.length - n;
        System.arraycopy(chs, n, res, 0, x);
        System.arraycopy(chs, 0, res, x, n);
        return new String(res);
    }
}
