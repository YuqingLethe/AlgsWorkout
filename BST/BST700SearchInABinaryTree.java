package BST;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
 *    If such a node does not exist, return null.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 2
 * Output: [2,1,3]
 *
 * Example 2:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: []
 */
public class BST700SearchInABinaryTree {
    class Recursive {
        /**
         * 2026版本同样把null check放前面，不过又忘记search
         */
        public TreeNode searchBST_recursive(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                return root;
            }
            TreeNode leftResult = searchBST(root.left, val);
            TreeNode rightResult = searchBST(root.right, val);

            return leftResult == null ? rightResult : leftResult;
        }

        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                return root;
            } else if (root.val < val) {
                return searchBST(root.right, val);
            } else {
                return searchBST(root.left, val);
            }
        }
    }
    class Iterative {
        /**
         * April2022 不改root是个好习惯！
         */
        public TreeNode searchBST(TreeNode root, int val) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.val == val) {
                    return curr;
                } else if (curr.val < val) {
                    curr = curr.right;
                } else {
                    curr = curr.left;
                }
            }
            return null;
        }

        // 这个写法思路更简洁
        public TreeNode searchBST_CribAnswer(TreeNode root, int val) {
            while (root != null && val != root.val)
                root = val < root.val ? root.left : root.right;
            return root;
        }

        /**
         * 2026年我竟然忘记search还用queue写了个brute force
         */
        public TreeNode searchBST_BruteForce(TreeNode root, int val) {
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode curr = q.poll();
                if (curr.val == val) {
                    return curr;
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            return null;
        }
    }
}
