package BST;

import Tree.TreeNode;

import java.util.Stack;

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 */
public class BST230KthSmallestElementInABST {
    class RecursiveInorderTraversal_april2022 {
        private int count = 0;
        private int result;
        public int kthSmallest(TreeNode root, int k) {
            if (root.left != null) {
                kthSmallest(root.left, k);
            }

            count ++;
            if (count == k) {
                result = root.val;
            } else if (root.right != null) {
                kthSmallest(root.right, k);
            }
            return result;
        }
    }
    class Iterative_inorder_traversal {
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            int count = 0;
            while (count != k || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                TreeNode curr = stack.pop(); //这里也可以写作root=stack.pop();
                count ++;
                if (count == k) {
                    return curr.val;
                } else {
                    root = curr.right;
                }
            }
            return -1;
        }
    }
}
