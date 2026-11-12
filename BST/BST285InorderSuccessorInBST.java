package BST;

import Tree.TreeNode;

public class BST285InorderSuccessorInBST {
    /** 2022 arpil的思路和2026一模一样，总是不能看到答案的直接思路
     * */
    class Solution2022 {

         public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
             return inorderSuccessor(root, p, null);
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
                 return findLeftMost_correct(root, prev);
             } else if (root.val > p.val) {
                 return inorderSuccessor(root.left, p, root); //离 p 最近的潜在后继
             } else {
                 return inorderSuccessor(root.right, p, prev); //注意如果是右子树, prev应该传prev而不是root.
             }
         }
        /** Crib Answer april 2022*/

    }
    class Crib_answer_2022 {
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

    /**
     * 2026 我这个卡住的原因：如果下次做对了没必要看这个逻辑
     * 1. 比较完大小忘了return inorderSuccessor（） 这个应该是写完的代码逻辑检查的时候发现
     * 2. 右子树的特殊性，当前有可能p后面的数是当前curr root的prev，所以next应该传入prev，也就是next。
     * 一定原因是名字没取好，prev有误导。归根结底还是逻辑没想清楚。
     */
    class MarkNext_Recursive {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            return inorderSuccessor(root, p, null);
        }
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p, TreeNode next) {
            if (root == null)  {
                return null;
            }

            if (root.val == p.val) {
                if (root.right == null) {
                    return next;
                } else {
                    return findNextOnRightSubtree(root.right);
                }
            }
            if (root.val < p.val) {
                return inorderSuccessor(root.right, p, next);
            } else {
                return inorderSuccessor(root.left, p, root);
            }
        }
        private TreeNode findNextOnRightSubtree(TreeNode root) {
            TreeNode curr = root;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
    }
    public void main(String[] args) {
        Integer[] arr1 = {5,3,6,2,4,null,null,1};
        TreeNode target1 = new TreeNode(6);
        Integer[] arr2 = {5, null, 15, 8,17,7, 13, null, null, 6, null, 10};
        TreeNode target2 = new TreeNode(13);
    }
}
