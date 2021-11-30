package 高效制胜.day5;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 *  20. 有效的括号
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 22:04:27
 */
public class Solution20 {
    public boolean isValid(String s) {
        char[] chs = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>(chs.length);
        for (char ch : chs) {
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (top == '(' && ch != ')') return false;
                if (top == '[' && ch != ']') return false;
                if (top == '{' && ch != '}') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.isValid("()"));
        System.out.println(solution20.isValid("()[]{}"));
        System.out.println(solution20.isValid("(]"));
        System.out.println(solution20.isValid("([)]"));
        System.out.println(solution20.isValid("{[]}"));
    }
}
