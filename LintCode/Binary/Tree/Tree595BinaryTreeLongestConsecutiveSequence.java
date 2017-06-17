package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Tree595BinaryTreeLongestConsecutiveSequence {
    /**
     * Traverse and Divide and conquer 2017/6/16.
     * 答案三种方法很全，倒着找,正着找, ResultType找:　http://www.jiuzhang.com/solutions/binary-tree-longest-consecutive-sequence/
     */
    int maxLen; //Java里面的全局变量, boolean自动为false, char为空, String 为null, 其他一律为0
    //可以加一个TreeNode全局变量, 返回maxLen对应的起始节点, 帮助debug
    public int longestConsecutiveTraverseDC(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return maxLen;
    }
    //return length of current consecutive sequence
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLen = helper(root.left);
        int rightLen = helper(root.right);
        int currLen = 1;
        if (leftLen != 0 && root.left.val - 1 == root.val) {
            currLen = Math.max(currLen, leftLen + 1);
        }
        if (rightLen != 0 && root.right.val - 1 == root.val) {
            currLen = Math.max(currLen, rightLen + 1);
        }
        if (currLen > maxLen) {
            maxLen = currLen;
        }
        return currLen;
    }
}
