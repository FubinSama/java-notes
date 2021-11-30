package 剑指offer.day3;

/**
 * <p>
 *  剑指 Offer 05. 替换空格
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-04 09:02:21
 */
public class Solution05 {
    public String replaceSpace(String s) {
        char[] chs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chs) {
            if (ch == ' ') sb.append("%20");
            else sb.append(ch);
        }
        return sb.toString();
    }
}
