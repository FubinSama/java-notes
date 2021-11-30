package 高效制胜.day1;

/**
 * <p>
 *  167. 两数之和 II - 输入有序数组
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-03 20:20:24
 */
public class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int sum, li = 0, hi = numbers.length - 1;
        while (li < hi) {
            sum = numbers[li] + numbers[hi];
            if (sum < target) li ++ ;
            else if (sum > target) hi -- ;
            else return new int[] { li + 1, hi + 1 };
        }
        return null;
    }
}
