package BST;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 *
 *
 * Example 1:
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 *
 *  * Your BSTIterator object will be instantiated and called as such:
 *  * BSTIterator obj = new BSTIterator(root);
 *  * int param_1 = obj.next();
 *  * boolean param_2 = obj.hasNext();
 *
 */
public class BST173BinarySearchTreeIterator {
    /**
     * _april2022_self
     * TODO: Using arraylist insted of queue
     */
    class BSTIterator_queue {
        private Queue<TreeNode> q;
        public BSTIterator_queue(TreeNode root) {
            this.q = new LinkedList<>();
            inorderTraversal(root);
        }
        private void inorderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left);
            q.add(root);
            inorderTraversal(root.right);
        }

        public int next() {
            TreeNode curr = this.q.poll();
            return curr.val;
        }

        public boolean hasNext() {
            if (q.peek() != null) {
                return true;
            }
            return false;
        }
    }

    /**
     * April 2022 Crib the answer
     */
    class BSTIterator_dynamicByStack {
        private Stack<TreeNode> stack;

        public BSTIterator_dynamicByStack(TreeNode root) {
            this.stack = new Stack<>();
            pushAllNodesTillFurthest(root);
        }
        private void pushAllNodesTillFurthest(TreeNode root) {
            while(root != null) {
                this.stack.add(root); // 注意这里包括root本身了. next()里面没必要在加自己了
                root = root.left;
            }
        }

        public int next() {
            TreeNode curr = this.stack.pop();
            if (curr.right != null) {
                pushAllNodesTillFurthest(curr.right);
            }
            return curr.val;
        }


        public boolean hasNext() {
            return !this.stack.empty();
        }
    }
}
