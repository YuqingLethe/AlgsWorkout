package Tree;

import java.util.LinkedList;

/**
 * 三种方法:
 * 一是两个recursion
 * 二是一个recursion, 返回值如果是正数就是height如果 -1说明不balance
 * 三是用resultType来记录isBalanced和Depth
 *
 * 直接運算height/depth的是top-down recurion, 而先看balanced是bottom up, 只要不balance其他就都不用看了.
 */
public class Tree110BalancedBinaryTree {
    //之前的错误思路: http://blog.csdn.net/eatbanli/article/details/52658847
    class TwoRecursion {
        /**
         * March 2022
         * 9/24/2016 & 6/13/2017
         */
        public boolean IsBalancedTwoRecursion(TreeNode root) {
            if (root == null) {
                return true;
            }
            if ((Math.abs(height(root.left) - height(root.right)) <= 1)
                    && (IsBalancedTwoRecursion(root.left) && IsBalancedTwoRecursion(root.right))
            ){
                return true;
            }
            return false;
        }
        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    static class oneRecursion {
        //
        //https://discuss.leetcode.com/topic/11007/java-solution-based-on-height-check-left-and-right-node-in-every-recursion-to-avoid-further-useless-search/4

        /**
         * Runtime: 1ms Passed First Time Yay!
         * 这个方法比IsBalancedTwoRecursion() 好的地方是, 如果左子树不balance,不再比较右子树. 更快
         * Comparing the subtree's height in the getDepth(). O(n) instead of O(n^2)
         * Get the idea in "https://discuss.leetcode.com/topic/11007/java-solution-based-on-height-check-left-and-right-node-in-every-recursion-to-avoid-further-useless-search"
         * @param root
         * @return
         */
        public static boolean isBalancedBySubHeight(TreeNode root) {
            if (root == null || (root != null && root.left == null && root.right == null)) {
                return true;
            }
            int leftHeight = getDepth(root.left);
            int rightHeight = getDepth(root.right);
            if ((leftHeight == -1 || rightHeight == -1)
                ||(Math.abs(leftHeight - rightHeight) > 1)
                ) {
                return false;
            }else {
                return true;
            }
        }
        private static int getDepth(TreeNode root) {
            if (root == null) return 0;
            int leftDepth = getDepth(root.left);
            //If the left child is balanced, we search the other one
            if (leftDepth != -1) {
                int rightDepth = getDepth(root.right);
                //If the right child is balanced
                if (rightDepth != -1) {
                    return Math.abs(leftDepth - rightDepth) > 1 ?  -1 :  1 + Math.max(leftDepth, rightDepth);
                }
            }
            return -1;
        }
        public void main(String[] args) {
            TreeNode a = new TreeNode(1);
            TreeNode b = new TreeNode(2);
            a.left = b;
            System.out.println(isBalancedBySubHeight(a));
        }
    }

    /**
     * March 2022
     * Method 3: http://www.jiuzhang.com/solutions/balanced-binary-tree/
     */
    class resultType {
        class ResultType {
            public boolean isBalanced = false;
            public Integer depth = 0;
            public ResultType(boolean isBalanced, Integer depth) {
                this.isBalanced = isBalanced;
                this.depth = depth;
            }
        }
        public boolean isBalanced(TreeNode root) {
            return getSubtreeInfo(root).isBalanced;
        }

        public ResultType getSubtreeInfo(TreeNode root) {
            if (root == null) {
                return new ResultType(true, 0);
            }
            if (root.left == null && root.right == null) {
                return new ResultType(true, 1);
            }
            ResultType left = getSubtreeInfo(root.left);
            if (!left.isBalanced) {
                return new ResultType(false, -1);
            }
            ResultType right = getSubtreeInfo(root.right);
            if (!right.isBalanced) {
                return new ResultType(false, -1);
            }
            if (Math.abs(left.depth - right.depth) > 1) {
                return new ResultType(false, -1);
            }
            return new ResultType(true, Math.max(left.depth, right.depth) + 1);
        }
    }
}
