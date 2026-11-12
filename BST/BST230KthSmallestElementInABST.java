package BST;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
 *
 * Example 3:
 * root = [1, null, 2], k = 1
 */
public class BST230KthSmallestElementInABST {
    /**
     * 这个太简洁了
     */
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
    /**
     * 我自己一直传计数器，debug了很久，最后搞清楚也是很不容易了，花了35min
     *
     * 错误点是遍历完右子树后，counter应该返回当前子树的所有数目，所以应该取右子树遍历的返回值
     */
    class Recursive_No_Global_Counter_2026 {
        private Integer answer;
        public int kthSmallest(TreeNode root, int k) {
            this.answer = null;
            kthSmallest(root, k, 0);
            return answer;
        }
        /**
         * Return the counter of nodes from current subtree
         */
        private int kthSmallest(TreeNode root, int k, int counter) {
            if (root == null) {
                return counter;
            }
            int prevCount = kthSmallest(root.left, k, counter);
            counter = prevCount + 1;
            // System.out.println("root=" + root.val + " counter=" + counter);

            if (counter == k) {
                answer = root.val;
                return counter;
            }
            if (counter >= k && answer == null) {
                throw new IllegalArgumentException("k is larger than total number of tree");
            }
            if (counter >= k || answer != null) { // 已经找到答案，不用便利后面的了直接返回
                return counter;
            }
            int latestCounter = kthSmallest(root.right, k, counter); // 右边计数器忘了加上了
            return latestCounter;
        }
    }

    /**
     * 优化计数器放入global，但远不如2022版本简洁！
     */
    class Recursive_With_Global_Counter_2026 {
        private Integer answer;
        private int counter;
        private int k;
        public int kthSmallest(TreeNode root, int k) {
            this.answer = null;
            this.counter = 0;
            this.k = k;
            helper(root);
            return answer;
        }
        private void helper(TreeNode root) {
            if (root == null || this.counter > this.k) {
                return;
            }
            helper(root.left);
            this.counter ++;
            if (this.counter == this.k) {
                this.answer = root.val;
                return;
            }
            helper(root.right);

        }
    }

    // 最烂解法但是可以熟悉一下遍历和list的应用
    class Recursive_With_List_Slowest_Solution_Answer {
        public int kthSmallest(TreeNode root, int k) {
            ArrayList<Integer> arr = new ArrayList<>();
            kthSmallest(root, arr, k);
            if (k > arr.size()) {
                throw new IllegalArgumentException("k exceeds total number of nodes");
            }
            return arr.get(k - 1);
        }

        private void kthSmallest(TreeNode root, ArrayList<Integer> arr, int k) {
            if (root == null) {
                return;
            }
            kthSmallest(root.left, arr, k);
            arr.add(root.val);
            if (arr.size() > k) {
                return;
            }
            kthSmallest(root.right, arr, k);
        }
    }
    class Iterative_inorder_traversal {
        /**
         * 如果必须先入栈一个node，则curr直接指向下一个节点再进行while.
         * ----------------------------
         * 注意以下是错误的🙅：
         * 否则的话，while开头必须保证curr指向已经入栈道latest node才行，
         * which means 遍历右节点的时候需要先入栈top的右节点
         * ----------------------------
         * 运用上面错误想法得到failed case【1，null，2】
         * curr不能一开始就指向右子树！！！指向右子树必须pop它本身节点才行！
         * 修改： while (!stack.isEmpty() || curr != null) 或者while (!stack.isEmpty() || counter != k)
         * 或者答案更绝while(true)
         */
        public int kthSmallest_2026(TreeNode root, int k) {
            TreeNode curr = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            int counter = 0;

            while (!stack.isEmpty() || curr != null) {
                // traverse left tree till smallest node
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                // Pop the smallest element in stack
                TreeNode top = stack.pop();
                if (++counter == k) {
                    return top.val;
                }
                // Point to the right child tree
                curr = top.right;
            }
            if (counter > k) {
                throw new IllegalArgumentException("k is larger than total number of the tree");
            }
            return -1;
        }
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            int count = 0;
            while (count != k || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                TreeNode curr = stack.pop();
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
