package BST;

import Tree.TreeNode;

import java.util.*;

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
     * _april2022_self 用的Queue，2026用的ArrayList
     * TODO: Using arraylist instead of queue
     */
    class BSTIterator_flatten_to_list {

        private List<Integer> valueList; // 可以作为final，可以取名sortedList更精确
        private int index;
        public BSTIterator_flatten_to_list(TreeNode root) {
            this.valueList = new ArrayList<>();
            buildList(root);
            this.index = 0;
        }

        private void buildList(TreeNode root) {
            if (root == null) {
                return;
            }
            buildList(root.left);
            this.valueList.add(root.val);
            buildList(root.right);
        }

        public int next() {
            return valueList.get(this.index ++); // 这个写法第一次尝试，通过！
        }

        public boolean hasNext() {
            return this.index <= valueList.size() - 1;
        }
    }

    /**
     * April 2022 Crib the answer
     * 2026看了答案自己写
     */
    class BSTIterator_lazy_evaluation {
        private final Deque<TreeNode> stack;

        public BSTIterator_lazy_evaluation(TreeNode root) {
            this.stack = new ArrayDeque<>();
            pushAllNodesTillFurthest(root);
        }
        private void pushAllNodesTillFurthest(TreeNode root) {
            while(root != null) {
                this.stack.add(root); // 注意这里包括root本身了. next()里面没必要在加自己了
                root = root.left;
            }
        }

        public int next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException("No more element in the BSTIterator!"); // 防御型代码，虽然条件说了next肯定有值，但值得写！
            }
            TreeNode curr = this.stack.pop();
            if (curr.right != null) {
                pushAllNodesTillFurthest(curr.right);
            }
            return curr.val;
        }


        public boolean hasNext() {
            return !this.stack.isEmpty();
        }
    }
}
