package BST;

import Tree.TreeNode;

/**
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 *    Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
 *     You can return any of them.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 *
 * Example 2:
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 *
 * Example 3:
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 */
public class BST701InsertIntoABinarySearchTree {
    /**
     * April 2022 Crib Answer
     *
     * 主要这个问题吧, 想的太复杂. 在考虑替换现有node的事情.
     * 但其实这个事情不存在, 因为虽然BST inorder是个排序好的array, 它叶子节点是均匀分布的. 叶子节点可能是15913这样, 所以目标val总能插进来
     */
    class Recursive {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            if (root.val > val) {
                root.left = insertIntoBST(root.left, val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }
            return root;
        }
    }

    /**
     * April 2022 Crib Answer
     */
    class Iterative {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.val < val) {
                    if (curr.right == null) {
                        curr.right = new TreeNode(val);
                        return root;
                    } else {
                        curr = curr.right;
                    }
                } else {
                    if (curr.left == null) {
                        curr.left = new TreeNode(val);
                        return root;
                    } else {
                        curr = curr.left;
                    }
                }
            }
            return new TreeNode(val); //注意最后这个return的情况, 别大意用return root
        }
    }
}
