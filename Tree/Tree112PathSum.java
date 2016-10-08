package Tree;

import java.util.Stack;

/**
 * Created by XiaoMi on 2016/10/7.
 */
public class Tree112PathSum {
    /**
     * leafSum leafSum2 Runtime: 1ms   10/7/2016
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumRecursive(TreeNode root, int sum) {
        if (root == null && sum == 0) return true;
        else if (root == null) return false;

        return leafSum(root, 0, sum);
    }

    private static boolean leafSum (TreeNode root, int parSum, int sum) {
        int currSum = parSum + root.val;
        if (root.left == null && root.right == null && currSum == sum) {
            return true;
        }
        if (root.left != null && leafSum(root.left, currSum, sum)) {
            return true;
        }else if (root.right != null && leafSum(root.right, currSum, sum)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean leftSum2(TreeNode root, int parSum, int sum) {
        int currSum = parSum + root.val;
        if (root.left == null && root.right == null && currSum == sum) {
            return true;
        }
        return root.left != null && leafSum(root.left, currSum, sum)
            || root.right != null && leafSum(root.right, currSum, sum);
    }

    //TODO: try this method. One reference provided.

    /**
     * My Original Thoughts
     * https://discuss.leetcode.com/topic/59924/non-recursive-using-two-stacks-java
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumByTwoStack(TreeNode root, int sum) {
        return false;
    }

    /**
     * Runtime:  1ms  Use  5min  10/7/2016
     * Self-recursive method, CHANGE VAL of each treenode as the cumulative vals of the path
     * https://discuss.leetcode.com/topic/57216/java-solution-using-recursive-1ms
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        //If this is leaf node and find the path
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        //If not, search subtrees
        boolean leftAns = false, rightAns = false;
        if (root.left != null) {
            root.left.val += root.val;
            leftAns = hasPathSum(root.left, sum);
        }
        if (root.right != null) {
            root.right.val += root.val;
            rightAns = hasPathSum(root.right, sum);
        }
        return leftAns || rightAns;
    }
}
