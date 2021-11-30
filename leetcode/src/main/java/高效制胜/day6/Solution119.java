package 高效制胜.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  119. 杨辉三角 II
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-08 19:54:44
 */
public class Solution119 {
    /*
    1
    1 1
    1 2 1
    1 3 3 1
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) return null;
        int[] res = new int[rowIndex + 1], back = new int[rowIndex + 1], tmp;
        res[0] = 1;
        back[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            tmp = res;
            res = back;
            back = tmp;
            for (int j = 1; j <= i; j++) {
                res[j] = back[j - 1] + back[j];
            }
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Solution119 solution119 = new Solution119();
        List<Integer> res = solution119.getRow(3);
        System.out.println(res);
    }

}
