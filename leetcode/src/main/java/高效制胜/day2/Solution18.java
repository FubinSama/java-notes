package 高效制胜.day2;

import java.util.*;

/**
 * <p>
 *  18. 四数之和
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-04 09:39:08
 */
public class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length < 4) return res;
        Arrays.sort(nums);
        int sum, target0, li, hi;
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 从已排序的数组中找两个和为target-nums[i]-nums[j]的二元组
                li = j + 1; hi = nums.length - 1;
                target0 = target - nums[i] - nums[j];
                while (li < hi) {
                    sum = nums[li] + nums[hi];
                    if (sum < target0) li ++;
                    else if (sum > target0) hi --;
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[li++], nums[hi--]));
                        // 过滤掉重复的结果集
                        while (li < hi && nums[li-1] == nums[li]) ++li;
                        while (li < hi && nums[hi+1] == nums[hi]) --hi;
                    }
                }
                while (j < nums.length - 2 && nums[j] == nums[j+1]) ++j; // 过滤掉重复的结果集
            }
            while (i < nums.length - 3 && nums[i] == nums[i+1]) ++i; // 过滤掉重复的结果集
        }
        return res;
    }

    public static void main(String[] args) {
        Solution18 s = new Solution18();
        System.out.println(s.fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }
}
