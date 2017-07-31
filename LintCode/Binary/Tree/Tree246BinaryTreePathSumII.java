package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class Tree246BinaryTreePathSumII {
    /**
     * 7/30
     * 这一遍才写对. 答案真是聪明. 给了我们教训, recursion的list里面不仅可以存储答案, 也可以存储路径, 即便
     * 不是答案的路径. 这样for循环就可以直接找到答案啦
     */
    public class Solution {
        public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if (root == null) {
                return results;
            }
            List<Integer> list = new ArrayList<>();
            helper(root, target, 0, list, results);
            return results;
        }
        //level的作用是当前的层数，也就是他的祖先节点们在buffer数组里的位置。
        //findsum里面 for loop 表示沿着这个点往上走的路径，也就是固定了路径的终点，起点未知。
        private void helper(TreeNode root,
                            int target,
                            int level,
                            List<Integer> list,
                            List<List<Integer>> results) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            int tmpSum = target;
            for (int i = level; i >= 0; i --) {
                tmpSum -= list.get(i);
                if (tmpSum == 0) {
                    //注意这个_list.subList()函数, Returns a view of the portion of this list因此要初始化
                    List<Integer> newlist = new ArrayList<Integer>(list.subList(i, level + 1));
                    results.add(newlist);
                }
            }

            helper(root.left, target, level + 1, list, results);
            helper(root.right, target, level + 1, list, results);
            list.remove(list.size() - 1);
        }
        private void print(List<Integer> list) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
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
