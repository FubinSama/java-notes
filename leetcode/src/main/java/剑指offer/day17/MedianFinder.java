package 剑指offer.day17;

import java.util.PriorityQueue;

/**
 * <p>
 *  剑指 Offer 41. 数据流中的中位数
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-20 22:54:52
 */
public class MedianFinder {

    PriorityQueue<Integer> A, B;

    /** initialize your data structure here. */
    public MedianFinder() {
        A = new PriorityQueue<>(); // 保存较大的一半
        B = new PriorityQueue<>((x, y) -> (y - x)); // 保存较小的一半
        // 奇数个数时，多的那一个保存到B中
    }

    public void addNum(int num) {
        if (A.size() == B.size()) { // 多的一个保存到B中
            A.add(num);
            B.add(A.poll());
        } else {  // B比A多一个，此时将num保存到A中
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : B.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        mf.addNum(4);
        mf.addNum(5);
        mf.addNum(6);
        System.out.println(mf.findMedian());
    }
}
