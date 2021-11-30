package 剑指offer.day14;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *  剑指 Offer 13. 机器人的运动范围
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-15 20:51:47
 */
public class Solution13 {

    public int movingCount(int m, int n, int k) {
        Deque<Integer> stack = new ArrayDeque<>(m * n);
        stack.push(getV(0, 0));
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int ans = 0;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            int i = getI(v);
            int j = getJ(v);
            ans++;
            int[][] dict = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] d : dict) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && getSum(x, y) <= k) {
                    stack.push(getV(x, y));
                    visited[x][y] = true;
                }
            }
        }
        return ans;
    }

    private static int getSum(int i, int j) {
        return getSum(i) + getSum(j);
    }

    private static int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    private static int getI(int v) {
        return v >> 8;
    }

    private static int getJ(int v) {
        return v & 0xff;
    }

    private static int getV(int i, int j) {
        return i << 8 | j;
    }

    public static void main(String[] args) {
        Solution13 solution = new Solution13();
        System.out.println(solution.movingCount(3, 2, 17));
    }

}
