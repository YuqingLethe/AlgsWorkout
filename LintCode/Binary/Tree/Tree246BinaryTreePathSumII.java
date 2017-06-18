package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class Tree246BinaryTreePathSumII {
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    int ultimateTarget;
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        ultimateTarget = target; //注意初始化的位置, 如果ultimateTarget为全局变量, 只能在这里找到target帮之初始化
        ArrayList<Integer> path = new ArrayList<>();
        helper(root, target, path, results);
        return results;
    }
    private void helper(TreeNode root,
                        int target,
                        ArrayList<Integer> path,
                        List<List<Integer>> results) {
        if (target == root.val) {
            path.add(root.val);
            results.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
        }
        path.add(root.val);
        if (root.left != null) {
            helper(root.left, target - root.val, path, results);
            //这里别忘了, 如果相等的话就没必要输出了, 不然会重复!! {1,-2,#,1,#,2}, 2
            //而且不能用target == ultimateTarget不然子节点就不会再往下走了 {1,2,3,4,#,2}, 6
            if (target - root.val != ultimateTarget) {
                ArrayList<Integer> tmpPath = new ArrayList<>();
                helper(root.left, ultimateTarget, tmpPath, results);
            }
        }
        if (root.right != null) {
            helper(root.right, target - root.val, path, results);
            if (target - root.val != ultimateTarget) {
                ArrayList<Integer> tmpPath = new ArrayList<>();
                //这里(本该left就写), 不能用当前的target来代替, 因为当前target在循环中会变!! {1,-2,#,1,#,2}, 2
                helper(root.right, ultimateTarget, tmpPath, results);
            }
        }
        path.remove(path.size() - 1);
    }

    /**
     * Divide & Conquer 九章答案, 自己想到了min和max的用法, 也算不错了....
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    private boolean helper(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return helper(root.left, Math.min(root.val, max), min)
                && helper(root.right, max, Math.max(root.val, min));
    }
    //TODO: ResultTYpe
    //TODO: Traverse的全局变量
}
