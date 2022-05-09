package Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Lintcode95
 * 方法一, Recursive, validate每個子樹
 * 方法二, 上面的思路Iterative
 * 方法二, Recursive Inorder Traversal. Iterative by stack. 只要是inorder遍歷一遍, 後加入的數字應該比之前所有的大
 */
public class Tree98ValidateBinarySearchTree {
    class Method1 {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }
        private boolean isValidBST(TreeNode root, Integer upperLimit, Integer lowerLimit) {
            if (root == null) {
                return true;
            }
            if ((upperLimit != null && root.val >= upperLimit) || (lowerLimit != null && root.val <= lowerLimit)) {
                return false;
            }
            if (!isValidBST(root.left, root.val, lowerLimit)) {
                return false;
            }
            if (!isValidBST(root.right, upperLimit, root.val)) {
                return false;
            }
            return true;
        }
    }

    public void main(String[] args) {
        Integer[] array = {5,4,6,null,null,3,7};
        boolean answer = false;
        Integer[] array2 = {2,2,2};
        boolean answer2 = false;
        Integer[] array3 = {5,1,4,null,null,3,6};
        Integer[] array4 = {-2147483648,null,2147483647};

    }

    class Method2 {
        private Deque<TreeNode> stack = new LinkedList<>();
        private Deque<Integer> upperLimits = new LinkedList<>();
        private Deque<Integer> lowerLimits = new LinkedList<>();
        public boolean isValidBST(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return true;
            }
            updateStacks(root, null, null);
            while(!stack.isEmpty()) { //其實如果treat null代碼更簡潔 if (root == null) continue;
                TreeNode curr = stack.poll();
                Integer upper = upperLimits.poll();
                Integer lower = lowerLimits.poll();
                // System.out.println("val=" + curr.val + " upper=" + (upper == null? " " : upper) + " lower=" + (lower == null? " " : lower));
                if (upper != null && curr.val >= upper) {
                    return false;
                }
                if (lower != null && curr.val <= lower) {
                    return false;
                }
                if (curr.left != null) {
                    updateStacks(curr.left, curr.val, lower);
                }
                if (curr.right != null) {
                    updateStacks(curr.right, upper, curr.val);
                }
            }
            return true;
        }
        private void updateStacks(TreeNode root, Integer high, Integer low) {
            stack.add(root);
            lowerLimits.add(low);
            upperLimits.add(high);
            // System.out.println("Update: val=" + root.val + " high=" + (high == null? " " : high) + " low=" + (low == null? " " : low));
        }
    }
    class DevideConquer2017WithMaxMin {
        /**
         * Divide & Conquer 九章答案, 自己想到了min和max的用法, 也算不错了....
         */
        public boolean isValidBST(LintCode.Binary.Tree.TreeNode root) {
            if (root == null) {
                return true;
            }
            return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
        }
        private boolean helper(LintCode.Binary.Tree.TreeNode root, long max, long min) {
            if (root == null) {
                return true;
            }
            if (root.val >= max || root.val <= min) {
                return false;
            }
            return helper(root.left, Math.min(root.val, max), min)
                    && helper(root.right, max, Math.max(root.val, min));
        }
    }

    /**
     * March 2022 完全看答案, 下面和標準答案一模一樣.
     */
    class Method3 {
        private Integer prev;
        public boolean isValidBST(TreeNode root) {
            prev = null;
            return inorder(root);
        }
        private boolean inorder(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!inorder(root.left)) {
                return false;
            }
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            return inorder(root.right);
        }
    }
}
