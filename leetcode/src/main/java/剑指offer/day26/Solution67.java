package 剑指offer.day26;

/**
 * <p>
 *  剑指 Offer 67. 把字符串转换成整数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-27 19:59:12
 */
public class Solution67 {
    public int strToInt(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i == str.length()) return 0;
        int factor = 1;
        if (str.charAt(i) == '-') {
            factor = -1;
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }
        int res = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return Integer.MAX_VALUE;
            } else if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && str.charAt(i) - '0' > 8)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + factor * (str.charAt(i) - '0');
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution67 solution67 = new Solution67();
        System.out.println(solution67.strToInt("-2147483649"));
    }
}
