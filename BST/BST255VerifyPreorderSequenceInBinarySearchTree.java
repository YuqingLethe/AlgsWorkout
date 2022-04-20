package BST;

import java.util.Stack;

/**
 * Given an array of unique integers preorder,
 * return true if it is the correct preorder traversal sequence of a binary search tree.

 *
 * Example 1:
 * Input: preorder = [5,2,1,3,6]
 * Output: true
 *
 * Example 2:
 * Input: preorder = [5,2,6,1,3]
 * Output: false
 *
 *
 * Constraints:

 * 1 <= preorder.length <= 104
 * 1 <= preorder[i] <= 104
 * All the elements of preorder are unique.
 */
public class BST255VerifyPreorderSequenceInBinarySearchTree {
    // https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68142/Java-O(n)-and-O(1)-extra-space
    class StackInterative_CribAnswer {
        public boolean verifyPreorder(int[] preorder) {
            if (preorder == null || preorder.length == 0) {
                return true;
            }
            Stack<Integer> stack = new Stack<>();
            int smallest = Integer.MIN_VALUE;
            for (int p : preorder) {
                if (p < smallest) {
                    return false;
                }
                while (!stack.isEmpty() && p > stack.peek()) {
                    smallest = stack.pop();
                }
                stack.push(p);
            }
            return true;
        }
    }
}
