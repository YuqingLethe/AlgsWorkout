package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/7/31.
 */
public class Tree614BinaryTreeLongestConsecutiveSequenceII {
    /**
     * 7/31/2017 完全不会做 看答案
     */
    public class Solution {
        public int longestConsecutive2(TreeNode root) {
            ResultType result = helper(root);
            return result.max_length;

        }
        private ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(0, 0, 0);

            }
            ResultType left = helper(root.left);
            ResultType right = helper(root.right);

            int down = 0;
            int up = 0;
            if (root.left != null && root.left.val + 1 == root.val) {
                down = Math.max(down, left.max_down + 1);
            }
            if (root.left != null && root.left.val - 1 == root.val) {
                up = Math.max(down, left.max_up + 1);
            }
            if (root.right != null && root.right.val + 1 == root.val) {
                down = Math.max(down, right.max_down + 1);
            }
            if (root.right != null && root.right.val - 1 == root.val) {
                up = Math.max(up, right.max_up + 1);
            }
            int len = down + up + 1;
            len = Math.max(len, Math.max(left.max_length, right.max_length));
            return new ResultType(len, down, up);

        }
        class ResultType {
            public int max_length, max_down, max_up;
            ResultType(int len, int down, int up) {
                max_length = len;
                max_down = down;
                max_up = up;
            }
        }

    }
}
