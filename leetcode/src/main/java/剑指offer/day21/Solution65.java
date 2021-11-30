package 剑指offer.day21;

/**
 * <p>
 *  剑指 Offer 65. 不用加减乘除做加法
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-22 21:23:03
 */
public class Solution65 {
    /*
    加法器：
    a   b   c   d   c'
    0   0   0   0   0
    0   0   1   1   0
    0   1   0   1   0
    0   1   1   0   1
    1   0   0   1   0
    1   0   1   0   1
    1   1   0   0   1
    1   1   1   1   1
    所以：
    d = ~a & ~b & c | ~a & b & ~c | a & ~b & ~c | a & b & c = a ^ b ^ c
    c' = ~a & b & c | a & ~b & c | a & b & ~c | a & b & c = a & b | (a ^ b) & c
     */
    public int add(int a, int b) {
        int res = 0;
        int carry = 0;
        int a0, b0;
        for (int i=0; i<32; i++) {
            a0 = a & 0x1;
            b0 = b & 0x1;
            res |= (a0 ^ b0 ^ carry) << i;
            carry = a0 & b0 | (a0 ^ b0) & carry;
            a >>>= 1; b >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int add = new Solution65().add(996614456, 893139330);
        System.out.println(add);
    }
}
