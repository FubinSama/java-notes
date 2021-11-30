package 剑指offer.day10;

import java.util.*;

/**
 * <p>
 *  剑指 Offer 48. 最长不含重复字符的子字符串
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-11 20:04:28
 */
public class Solution48 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chs = s.toCharArray();
        int max = 0, tmp = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chs.length; i++) {
            int j = map.getOrDefault(chs[i], -1);
            map.put(chs[i], i);
            tmp = tmp < i - j ? tmp + 1 : i - j;
            max = Math.max(max, tmp);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution48 solution48 = new Solution48();
        System.out.println(solution48.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution48.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution48.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution48.lengthOfLongestSubstring(" "));
        System.out.println(solution48.lengthOfLongestSubstring("au"));
        System.out.println(solution48.lengthOfLongestSubstring("dvdf"));
    }
}
