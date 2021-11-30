package 剑指offer.day24;

import java.util.*;

/**
 * <p>
 *  剑指 Offer 57 - II. 和为s的连续正数序列
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-25 20:13:40
 */
public class Solution57_2 {
    public int[][] findContinuousSequence(int target) {
        Deque<int[]> res = new LinkedList<>();
        for (int i = 2; (i - 1) * i / 2 < target; i++) {
            int temp = target - (i - 1) * i / 2;
            if (temp % i == 0) {
                int x = temp / i;
                int[] arr = new int[i];
                for (int j = 0; j < i; j++) {
                    arr[j] = x + j;
                }
                res.addFirst(arr);
            }
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] ints = new Solution57_2().findContinuousSequence(9);
        System.out.println(Arrays.deepToString(ints));
    }

}
