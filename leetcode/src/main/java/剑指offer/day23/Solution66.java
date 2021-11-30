package 剑指offer.day23;

/**
 * <p>
 *  剑指 Offer 66. 构建乘积数组
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-24 20:42:52
 */
public class Solution66 {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        int[] b = new int[a.length];
        // 计算下三角
        b[0] = 1;
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        // 计算上三角
        int temp = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            temp *= a[i + 1];
            b[i] *= temp;
        }
        return b;
    }

    public static void main(String[] args) {
        Solution66 solution66 = new Solution66();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = solution66.constructArr(a);
        for (int i : b) {
            System.out.println(i);
        }
    }
}
