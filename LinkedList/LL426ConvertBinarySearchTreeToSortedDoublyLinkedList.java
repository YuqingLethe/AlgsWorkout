package LinkedList;

import LintCode.Binary.Tree.TreeNode;

public class LL426ConvertBinarySearchTreeToSortedDoublyLinkedList {
    /** Crib the solution */
    class DFS_April2022 {
        DoublyLinkedNode first = null;
        DoublyLinkedNode last = null;

        public DoublyLinkedNode treeToDoublyList(DoublyLinkedNode root) {
            if (root == null) {
                return null;
            }
            helper(root);
            first.left = last;
            last.right = first;
            return first;
        }
        private void helper(DoublyLinkedNode curr) {
            if (curr == null) {
                return;
            }
            helper(curr.left);
            if (last != null) {
                last.right = curr;
                curr.left = last;
            } else {
                first = curr;
            }
            last = curr;
            helper(curr.right);
        }
    }

    /** Created by Administrator on 2017/6/17.
     * Description
     * Convert a binary search tree to doubly linked list with in-order traversal.并不要求in-place
     *
     * Example
     * Given a binary search tree:
     *
     *     4
     *    / \
     *   2   5
     *  / \
     * 1   3
     * return 1<->2<->3<->4<->5
     *
     * Another solution using Stack: https://www.programminghunter.com/article/5111197184/ (NOT IN PLACE)
     * */
    public static class Lint378ConvertBinarySearchTreeToDoublyLinkedList {
        /**
         * 比较慢的recursion
         * @param root: The root of tree
         * @return: the head of doubly list node
         */
        public DoublyListNode bstToDoublyList(TreeNode root) {
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
}
