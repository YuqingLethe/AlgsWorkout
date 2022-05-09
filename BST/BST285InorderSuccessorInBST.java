package BST;

import Tree.TreeNode;

public class BST285InorderSuccessorInBST {
    class Solution {
        /** 这是个错误答案, 简化在下面. 但这个为什么错呢? April2022
         * findLeftMost_wrong() 为什么不对? 自己看!
         *
         * */
         public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
             return inorderSuccessor(root, p, null);
         }
         private TreeNode findLeftMost_wrong(TreeNode root, TreeNode prev) {
             TreeNode leftMost = root.right;
             if (leftMost != null && leftMost.left != null) {
                 leftMost = leftMost.left;
             }
             return leftMost == null ? prev : leftMost;
         }
         private TreeNode findLeftMost_correct(TreeNode root, TreeNode prev) {
             if (root.right == null) {
                 return prev;
             }
             TreeNode leftMost = root.right;
             while (leftMost.left != null) {
                 leftMost = leftMost.left;
             }
             return leftMost;
         }

         private TreeNode inorderSuccessor(TreeNode root, TreeNode p, TreeNode prev) {
             if (root == null) {
                 return null;
             }
             if (root.val == p.val) {
                 return findLeftMost_wrong(root, prev);

             } else if (root.val > p.val) {
                 return inorderSuccessor(root.left, p, root);
             } else {
                 return inorderSuccessor(root.right, p, prev); //注意如果是右子树, prev应该传prev而不是root.
             }
         }
        /** Crib Answer april 2022*/
        public TreeNode successor(TreeNode root, TreeNode p) {
            if (root == null)
                return null;

            if (root.val <= p.val) {
                return successor(root.right, p);
            } else {
                TreeNode left = successor(root.left, p);
                return (left != null) ? left : root;
            }
        }
    }
    public void main(String[] args) {
        Integer[] arr1 = {5,3,6,2,4,null,null,1};
        TreeNode target1 = new TreeNode(6);
        Integer[] arr2 = {5, null, 15, 8,17,7, 13, null, null, 6, null, 10};
        TreeNode target2 = new TreeNode(13);
    }
}
