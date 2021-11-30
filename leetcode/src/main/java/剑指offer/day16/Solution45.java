package 剑指offer.day16;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 *  剑指 Offer 45. 把数组排成最小的数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-17 20:34:34
 */
public class Solution45 {
    public String minNumber(int[] nums) {
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted((a, b) -> (a + b).compareTo(b + a)).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        System.out.println(solution45.minNumber(new int[]{12, 121}));
    }

}
