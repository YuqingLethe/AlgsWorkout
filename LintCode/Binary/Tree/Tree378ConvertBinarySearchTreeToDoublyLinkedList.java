package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/6/17.
 */
//Definition for Doubly-ListNode.

public class Tree378ConvertBinarySearchTreeToDoublyLinkedList {
    /**
     * 比较慢的recursion
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        // Write your code here
        if (root == null) {
            return null;
        }
        DoublyListNode dln = new DoublyListNode(root.val);

        if (root.left != null) {
            DoublyListNode tmpp = bstToDoublyList(root.left);
            while (tmpp.next != null) {
                tmpp = tmpp.next;
            }
            tmpp.next = dln;
            dln.prev = tmpp;
        }
        if (root.right != null) {
            DoublyListNode tmp = bstToDoublyList(root.right);
            dln.next = tmp;
            tmp.prev = dln;
        }
        while (dln.prev != null) { //直接记住leftHead也可以
            dln = dln.prev;
        }
        return dln;
    }
}
