package 剑指offer.day13;

/**
 * <p>
 *  剑指 Offer 58 - I. 翻转单词顺序
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-15 00:37:30
 */
public class Solution58 {
    public String reverseWords(String s) {
        String[] strs = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].length() > 0) {
                sb.append(strs[i]);
                if (i != 0) sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution58 solution58 = new Solution58();
        System.out.println(solution58.reverseWords("Let's take LeetCode contest"));
    }

}
