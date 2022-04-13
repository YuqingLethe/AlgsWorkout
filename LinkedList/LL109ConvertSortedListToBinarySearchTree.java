package LinkedList;

import Tree.TreeNode;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example 1:
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in head is in the range [0, 2 * 104].
 * -105 <= Node.val <= 105
 */
public class LL109ConvertSortedListToBinarySearchTree {
    class Recursive_April2022_CribAnswer {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode middle = findMiddleElement(head);
            TreeNode root = new TreeNode(middle.val);
            if (head == middle) { //非常重要, 看到下面root.left = sortedListToBST(head);如果middle==head的话返回一模一样的值很可怕
                return root;
            }
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(middle.next);
            return root;
        }
        /** Unbind 前面list的最后一个, 比考虑截至node方便. 因为涉及到.next.next */
        private ListNode findMiddleElement(ListNode head) {
            ListNode prev = null;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            if (prev != null) { // Handling the case when slow was equal to head. 这里也忘记了
                prev.next = null;
            }
            return slow;
        }
    }

    /** 这个思路值得反复做
     * Inorder形成Tree的代码
     * ➔ function formBst(start, end)
     * ➔      mid = (start + end) / 2
     * ➔      formBst(start, mid - 1)
     * ➔
     * ➔      TreeNode(head.val)
     * ➔      head = head.next
     * ➔
     * ➔      formBst(mid + 1, end)
     * ➔
     *
     * */
    class Inorder_April2022_CribAnswer {
        ListNode head;
        public TreeNode sortedListToBST(ListNode head) {
            this.head = head;
            int len = findLength(head);
            return formBST(0, len - 1); //注意这里-1
        }

        private int findLength(ListNode head) {
            if (head == null) {
                return 0;
            }
            int result = 1;
            while (head.next != null) {
                result ++;
                head = head.next;
            }
            return result;
        }

        private TreeNode formBST(int left, int right) {
            if (left > right) {
                return null;
            }
            int mid = left + (right - left)/2;
            TreeNode leftNode = formBST(left, mid - 1);// Once left half is traversed, process the current node
            TreeNode root = new TreeNode(this.head.val);
            head = head.next; // Maintain the invariance mentioned in the algorithm
            root.left = leftNode;
            root.right = formBST(mid + 1, right);
            return root;
        }
    }
}
