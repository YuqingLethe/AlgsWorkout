package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/7/31.
 */
public class Tree475BinaryTreeMaximumPathSumII {
    /**
     * 2017/7/31 my own solution
     */
    public class SolutionMyOwn{
        private int max = Integer.MIN_VALUE;
        public int maxPathSum2(TreeNode root) {
            int sum = root.val;
            maxPathSum2(root, 0);
            return Math.max(sum, max);
        }

        private int maxPathSum2(TreeNode root, int upSum) {
            if (root == null) {
                return upSum;
            }
            int left = maxPathSum2(root.left, upSum + root.val);
            int right = maxPathSum2(root.right, upSum + root.val);
            int sum = Math.max(left, right);
            max = Math.max(max, sum);
            return sum;
        }

    }
    /**
     * 2017/7/31 jiuzhang solution
     */
    public class Solution {
        public int maxPathSum2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftValue = maxPathSum2(root.left);
            int rightValue = maxPathSum2(root.right);
            //别忘了这个0, 至关重要!!!
            return root.val + Math.max(0, Math.max(leftValue, rightValue));
        }
    }
}
