package 剑指offer.day5;

/**
 * <p>
 * 剑指 Offer 04. 二维数组中的查找
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-06 22:41:56
 */
public class Solution04 {
    /*
    a b c
    d e f
    g h i
    a < b < c && c < f < i
    所以：
    如果查找的数字比c大，那么一定不是a,b，即这一行可以跳过了;
    如果查找的数字比c小，那么一定不是f,i，即这一列可以跳过了。
    因此：只需要O(n + m)的时间复杂度，就可以找到答案
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) --j;
            else ++i;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        Solution04 solution = new Solution04();
        System.out.println(solution.findNumberIn2DArray(arr, 5));
        System.out.println(solution.findNumberIn2DArray(arr, 20));
    }
}
