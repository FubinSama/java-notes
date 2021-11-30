package 剑指offer.day15;

/**
 * <p>
 *  剑指 Offer 36. 二叉搜索树与双向链表
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-11-16 22:07:41
 */
public class Solution36 {

    /*
    null root null
    null root right
    left root null
    left root right
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        // 将左节点当作前驱节点，右节点当作后继节点
        Node left = treeToDoublyList(root.left); // 获取左子树生成的双向链表
        Node right = treeToDoublyList(root.right); // 获取右子树生成的双向链表
        if (left == null && right == null) { // 如果左右子树都为空，则该节点就是双向链表的头节点，直接构建即可
            root.left = root;
            root.right = root;
            return root;
        }
        if (left == null) { // 如果左子树为空，右子树不为空，则拼接root这个节点和右子树生成的双向链表
            root.right = right; // root后继节点指向右子树头节点
            root.left = right.left; // root前驱节点指向右子树尾节点
            right.left.right = root; // 右子树尾节点的后继节点指向root
            right.left = root; // 右子树头节点的前驱节点指向root
            return root;
        }
        if (right == null) { // 如果右子树为空，左子树不为空，则拼接root这个节点和左子树生成的双向链表
            root.left = left.left; // root前驱节点指向左子树尾节点
            root.right = left; // root后继节点指向左子树头节点
            left.left.right = root; // 左子树尾节点的后继节点指向root
            left.left = root; // 左子树头节点的前驱节点指向root
            return left;
        }
        root.left = left.left; // root前驱节点指向左子树尾节点
        root.right = right; // root后继节点指向右子树头节点
        left.left.right = root; // 左子树尾节点的后继节点指向root
        right.left.right = left; // 右子树尾节点的前驱节点指向左子树
        left.left = right.left; // 左子树头节点的前驱节点指向右子树的尾节点
        right.left = root; // 右子树头节点的前驱节点指向root
        return left;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        Solution36 solution36 = new Solution36();
        Node node = solution36.treeToDoublyList(root),node1 = node;
        while (node1.right != node) {
            System.out.println(node1.val);
            node1 = node1.right;
        }
        System.out.println(node1.val);
    }

}
