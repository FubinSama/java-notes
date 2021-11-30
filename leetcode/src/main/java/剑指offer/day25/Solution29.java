package 剑指offer.day25;

/**
 * <p>
 *  剑指 Offer 29. 顺时针打印矩阵
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-26 19:37:43
 */
public class Solution29 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int[][] da = new int[][] {
                {0, 1}, // 右
                {1, 0}, // 下
                {0, -1}, // 左
                {-1, 0} // 上
        };
        int d = 0;
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;
        int i = 0, j = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        while (index < res.length) {
            res[index++] = matrix[i][j];
            visited[i][j] = true;
            i += da[d][0];
            j += da[d][1];
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]) {
                i -= da[d][0];
                j -= da[d][1];
                d = (d + 1) % 4;
                i += da[d][0];
                j += da[d][1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] res = solution29.spiralOrder(matrix);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
