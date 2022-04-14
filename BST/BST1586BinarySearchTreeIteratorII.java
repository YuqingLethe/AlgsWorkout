package BST;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise returns false.
 * int prev() Moves the pointer to the left, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when next()/prev() is called.
 *
 *
 * Example 1:
 * Input
 * ["BSTIterator", "next", "next", "prev", "next", "hasNext", "next", "next", "next", "hasNext", "hasPrev", "prev", "prev"]
 * [[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
 * Output
 * [null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]
 *
 * Explanation
 * // The underlined element is where the pointer currently is.
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // state is   [3, 7, 9, 15, 20]
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 3
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 3
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
 * bSTIterator.hasNext(); // return true
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 9
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 15
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 20
 * bSTIterator.hasNext(); // return false
 * bSTIterator.hasPrev(); // return true
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 15
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 9
 *
 * **
 *  * Your BSTIterator object will be instantiated and called as such:
 *  * BSTIterator obj = new BSTIterator(root);
 *  * boolean param_1 = obj.hasNext();
 *  * int param_2 = obj.next();
 *  * boolean param_3 = obj.hasPrev();
 *  * int param_4 = obj.prev();
 *
 */
public class BST1586BinarySearchTreeIteratorII {
    class BSTIterator_flatten {
        private ArrayList<Integer> array = new ArrayList<>();
        private int currIdx;
        private int arrayLen;
        public BSTIterator_flatten(TreeNode root) {
            convertTreeToArrayInOrder(root);
            this.currIdx = -1;
            arrayLen = array.size();
        }

        private void convertTreeToArrayInOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                convertTreeToArrayInOrder(root.left);
            }
            array.add(root.val);
            if (root.right != null) {
                convertTreeToArrayInOrder(root.right);
            }
        }

        public boolean hasNext() {
            return this.currIdx < this.arrayLen - 1;
        }

        public int next() {
            currIdx ++;
            return this.array.get(currIdx); //不要用this.array.get(currIdx++); 这里应该是先移动指针再print.而不是先print再移动指针.
        }

        public boolean hasPrev() {
            return this.currIdx > 0;
        }

        public int prev() {
            currIdx --;
            return this.array.get(currIdx);
        }
    }

    /**
     * 自己miss的地方:我们用stack找next很好找, 找prev不好找的话, 直接存一个array不就好了吗因为都遍历过了
     * 自己的思路
     * 1. 什么情况用next() -> next()是在stack里面的pop出来, 还是stack里面pop出的下一个.
     * 2. prev怎么找? 能不能用空间换 => 直接用array来存遍历过的值
     * 3. 怎么让stack的inorder traversal进行下去? pop以后存哪里? 存last里面吗
     *
     * 答案的思路
     * Constructor in \mathcal{O}(1)O(1):
     * Initialize the last processed node as root: last = root.
     * Initialize a list to store already processed nodes: arr.
     * Initialize service data structure stack to be used during the iterative inorder traversal.
     * Initialize pointer: pointer = -1. This pointer serves as indicator if we're in the already parsed area or not.
     *                     We're in the parsed area if pointer + 1 < len(arr).
     */
    class BSTIterator_dynamic {
        private Stack<TreeNode> stack ; //初始化要在constructor里面进行.
        private ArrayList<Integer> list;
        private TreeNode last;
        private int pointer;

        public BSTIterator_dynamic(TreeNode root) {
            stack = new Stack<>();
            list = new ArrayList<>();
            pointer = -1;
            last = root;

        }

        public boolean hasNext() {
            if (!stack.isEmpty() || pointer < list.size() - 1 || last != null) {
                return true;
            }
            return false;
        }

        public int next() {
            ++ pointer;
            if (pointer == list.size()) { //如果后面没有遍历过的节点, 则需要从stack里面找下一个
                while (last != null) {
                    stack.push(last);
                    last = last.left;
                }
                TreeNode curr = stack.pop();
                list.add(curr.val);
                // 必须分清, last是维护stack的, 永远要比array的最新值快一步.
                // next()拿的永远是stack里面的, 所以last要提前指向下一个要遍历的.这两个无关
                last = curr.right;
            }
            return list.get(pointer);
        }

        public boolean hasPrev() {
            return pointer > 0;
        }

        public int prev() {
            -- pointer;
            return list.get(pointer);
        }
    }
}
