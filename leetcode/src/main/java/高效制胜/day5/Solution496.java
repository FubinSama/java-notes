package 高效制胜.day5;

import java.util.*;

/**
 * <p>
 * 496. 下一个更大元素 I
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-07 22:09:02
 */
public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0); // 为了统一化处理
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            // 在插入当前元素前，为维持单调性，做处理
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) stack.pop();
            // 对于某个元素，如果单调栈中没有值比它大，说明不存在，对应位置输出-1,否则输出单调栈顶元素
            if (stack.isEmpty()) map.put(nums2[i], -1);
            else map.put(nums2[i], stack.peek());
            stack.push(nums2[i]); // 将当前元素插入栈中
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution496 solution496 = new Solution496();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] res = solution496.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
