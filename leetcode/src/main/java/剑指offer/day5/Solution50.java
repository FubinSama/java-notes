package 剑指offer.day5;

/**
 * <p>
 *  剑指 Offer 50. 第一个只出现一次的字符
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-06 23:30:45
 */
public class Solution50 {
    public char firstUniqChar(String s) {
        char[] chs = s.toCharArray();
        int[] count = new int[26]; // 记录每个字符出现的次数
        for (char c : chs) {
            int index = c - 'a';
            count[index]++;
        }
        for (char ch : chs) {
            int index = ch - 'a';
            if (count[index] == 1) return ch;
        }
        return ' ';
    }

    public static void main(String[] args) {
        Solution50 solution = new Solution50();
        System.out.println(solution.firstUniqChar("abaccdeff"));
    }
}
