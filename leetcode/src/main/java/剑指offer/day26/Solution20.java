package 剑指offer.day26;

/**
 * <p>
 *  剑指 Offer 20. 表示数值的字符串
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-27 17:10:47
 */
public class Solution20 {

    private static int convertChar2State(char ch) {
        if (ch == ' ') return 0;
        if (ch == '+' || ch == '-') return 1;
        if (ch >= '0' && ch <= '9') return 2;
        if (ch == '.') return 3;
        if (ch == 'e' || ch == 'E') return 4;
        return 5;
    }

    private static int[][] table = new int[][] {
            {0, 1, 2, 9, 10, 10},
            {10, 10, 2, 9, 10, 10},
            {8, 10, 2, 3, 5, 10},
            {8, 10, 4, 10, 5, 10},
            {8, 10, 4, 10, 5, 10},
            {10, 6, 7, 10, 10, 10},
            {10, 10, 7, 10, 10, 10},
            {8, 10, 7, 10, 10, 10},
            {8, 10, 10, 10, 10, 10},
            {10, 10, 4, 10, 10, 10},
            {10, 10, 10, 10, 10, 10}
    };

    public boolean isNumber(String s) {
        int state = 0;
        for (int i = 0; i < s.length(); ++i) {
            state = table[state][convertChar2State(s.charAt(i))];
        }
        return state == 2 || state == 3 || state == 4 || state == 7 || state == 8;
    }

    public static void main(String[] args) {
        String s = "e";
        System.out.println(new Solution20().isNumber(s));
    }
}
