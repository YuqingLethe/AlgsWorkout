package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Tree597SubtreeWithMaximumAverage {
    double maxAvg = Integer.MIN_VALUE; //不要忘了用double, 题目就说了比较大小, 没说取整数的事儿, 不能自己设条件
    TreeNode maxTree;
    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return maxTree;
    }
    private ResultType2 helper (TreeNode root) {//这个地方必须用public不能用private否则Result Type
        if (root.left == null && root.right == null) {
            if (root.val > maxAvg) {
                maxAvg = root.val;
                maxTree = root;
            }
            return new ResultType2(root.val, 1);
        }
        int sum = root.val;
        int total = 1;
        if (root.left != null) {
            ResultType2 leftRT = helper(root.left);
            sum += leftRT.sum;
            total += leftRT.total;
        }
        if (root.right != null) {
            ResultType2 rightRT = helper(root.right);
            sum += rightRT.sum;
            total += rightRT.total;
        }
        if (maxAvg < (double) sum / total) { //这里也可以变为乘法! 就不需要double了
            maxAvg = (double) sum / total;
            maxTree = root;
        }
        return new ResultType2(sum, total);
    }


}
class ResultType2 {
    public int sum;
    public int total;//取名为size更贴切!
    public ResultType2(int a, int b) { //注意没有返回值, 不要手欠写上了...
        this.sum = a;
        this.total = b;
    }
}
