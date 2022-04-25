package BST;

import Tree.TreeNode;

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
        //April2022
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
    }
}
