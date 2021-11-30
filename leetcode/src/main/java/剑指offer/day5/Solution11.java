package 剑指offer.day5;

/**
 * <p>
 *  剑指 Offer 11. 旋转数组的最小数字
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-06 22:54:53
 */
public class Solution11 {
    /*
                    e
                d
            c
        b
    a
    变为：
            e
        d
    c
                    b
                a
    我们称cde序列为大数序列，ab为小数序列，而最小值一定在小数序列中
    对于这样一个序列，任何一个位置要么大于左端点的值，那么它在大数序列中，最小值一定在它的右边
    要么小于右端点的值，那么它在小数序列中，这个数要么是最小值，要么是最小值的右边的数
    要么等于左和右端点的值：
        此时要么是一个常量数列，那么取哪个值返回都可以，可以取hi -= 1;
        要么该位置位于小数序列中，那么最小值一定在其左边，可以取hi -= 1;
        要么该位置位于大数序列中，此时也可以取hi -= 1, 因为此时序列中还有值等于hi位置的数字。
     */
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) return 0;
        int li = 0, hi = numbers.length - 1;
        while (li < hi) {
            int mid = li + (hi - li) / 2;
            if (numbers[mid] > numbers[hi]) li = mid + 1;
            else if (numbers[mid] < numbers[li]) hi = mid;
            else hi--;
        }
        return numbers[hi];
    }

    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        int[] numbers = {2,2,2,0,1};
        System.out.println(solution11.minArray(numbers));
    }
}
