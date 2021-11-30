package 高效制胜.day1;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  1. 两数之和
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-03 20:12:18
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) return new int[] { map.get(nums[i]), i };
            map.put(target - nums[i], i);
        }
        return null;
    }
}
