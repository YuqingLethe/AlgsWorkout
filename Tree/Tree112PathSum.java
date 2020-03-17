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
     *
     * 20200316又没写出来这个。写出来的思路有:
     * 1. 先判断叶子节点左右都是null的条件
     * 2. 需要一个TreeNode Stack来帮助trace父节点
     * 难点(没想明白的）
     * 1. 怎样得知我们已经遍历了左节点还是右节点？还需要一个boolean stack来告诉我们吗？
     *      答：并不用，因为binaryTree只有两个子树。只要把查看右节点和pop动作写在一起，
     * 2. 什么是终结条件？
     *     答： 终结条件和中间的memory设计有关。但主旨是treenode里面还有，说明还没遍历完，所以不结束。
     * 3. 需要sum stack吗？还是只需要记录当前的值？
     *     答：强烈建议用stack，因为不断地减加容易乱，不如和node stack一起pop push看得明白。
     */
    public boolean hasPathSumByTwoStack(TreeNode root, int sum) {
        return false;

    }

    /**
     * Runtime:  1ms  Use  5min  10/7/2016
     * Self-recursive method, CHANGE VAL of each treenode as the cumulative vals of the path
     * https://discuss.leetcode.com/topic/57216/java-solution-using-recursive-1ms
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

    /**
     * 这是2020年的想法，思路很顺：1. 先写root node的逻辑，currSum是Sum减去root.val，左右子树两个if
     * 2. 再想leaf node的逻辑，如果当前val和sum相等
     * 漏掉了：如果root == null这个逻辑。在我的left note里面其实不会有这个，但不排除整个空树。
     */
    public boolean hasPathSum2020(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (sum == root.val && root.left == null && root.right == null) {
            return true;
        }
        TreeNode currNode = root;
        int currSum = sum - currNode.val;

        if (currNode.left != null && hasPathSum(currNode.left, currSum)) {
            return true;
        } else if (currNode.right != null && hasPathSum(currNode.right, currSum)) {
            return true;
        }
        return false;
    }
}
