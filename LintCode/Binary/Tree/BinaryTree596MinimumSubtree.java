package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/6/16.
 */
public class BinaryTree596MinimumSubtree {
    /**
     * 方法1: Traverse + divide conquer
     */
    private TreeNode subtree = null;
    private int subtreeSum = Integer.MAX_VALUE;

    public TreeNode findSubtreeTraverse(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return subtree;
    }
    private int helper(TreeNode root) {//为什么return type要是int, 因为需要minSum来比较
        if (root == null) {
            return 0;
        }
        int sum = helper(root.left) + helper(root.right) + root.val;
        if (sum < subtreeSum) {
            subtreeSum = sum;
            subtree = root;
        }
        return sum;//注意不是返回subtreeSum, 因为遍历只是为了更新全局变量, 返回sum是为了寻找父节点向上的最小值

    }



    /**
     *方法2 pure divide conquer
     */
    public TreeNode findSubtree(TreeNode root) {
        if (root == null) {
            return null;
        }
        ResultType rootRT = search(root);
        return rootRT.minSubtree;

    }
    private ResultType search(TreeNode root) {
        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        TreeNode minSubtree = root;
        if (root.left == null && root.right == null) {
            minSum = root.val;
            sum = root.val;
            ResultType tmp = new ResultType(minSubtree, minSum, sum);
            return tmp;
        }
        if (root.left != null) {
            ResultType leftRT = search(root.left);
            sum += leftRT.sum;
            if (minSum > leftRT.minSum) {
                minSum = leftRT.minSum;
                minSubtree = leftRT.minSubtree;
            }

        }
        if (root.right != null) {
            ResultType rightRT = search(root.right);
            sum += rightRT.sum;
            if (minSum > rightRT.minSum) {
                minSum = rightRT.minSum;
                minSubtree = rightRT.minSubtree;
            }
        }
        sum += root.val;
        if (sum < minSum) {
            minSum = sum;
            minSubtree = root;
        }
        ResultType currRT = new ResultType(minSubtree, minSum, sum);
        return currRT;

    }
}
class ResultType {
    public TreeNode minSubtree;
    public int sum, minSum;
    public ResultType(TreeNode minSubtree, int minSum, int sum) {
        this.minSubtree = minSubtree;
        this.minSum = minSum;
        this.sum = sum;
    }
}

