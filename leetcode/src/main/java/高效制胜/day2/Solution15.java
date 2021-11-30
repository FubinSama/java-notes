package 高效制胜.day2;

import java.util.*;

/**
 * <p>
 *  15. 三数之和
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-04 09:14:03
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums.length < 3) return new LinkedList<>(res);
        Arrays.sort(nums); // 排序

        int li, hi, target, sum;
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0 && nums[i] <= nums[nums.length - 1]; ++i) {
            // 从已排序的数组中找两个和为-nums[i]的二元组
            li = i + 1; hi = nums.length - 1; target = -nums[i];
            while (li < hi) {
                sum = nums[li] + nums[hi];
                if (sum > target) hi --;
                else if (sum < target) li ++;
                else res.add(Arrays.asList(nums[i], nums[li++], nums[hi--]));
            }
        }
        return new LinkedList<>(res);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1,0,1,2,-1,-4};
        Solution15 solution15 = new Solution15();
        System.out.println(solution15.threeSum(arr));
    }
}
