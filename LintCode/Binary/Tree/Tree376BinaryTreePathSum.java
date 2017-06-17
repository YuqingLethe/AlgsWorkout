package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Tree376BinaryTreePathSum {
    /**
     * 2017/6/16 答案上处理方法有两个不同,大致思路是一样的 http://www.jiuzhang.com/solution/binary-tree-path-sum/
     * 一是用sum来比较和target大小
     * 二是左右节点在进入helper之前就先加入list里面了.
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        ArrayList<Integer> list = new ArrayList<>();
        helper(root, target, list, results);
        return results;
    }
    private void helper(TreeNode root,
                        int target,
                        ArrayList<Integer> list,
                        List<List<Integer>> results) {
        if (root.left == null && root.right == null) {
            //这个想法是对的, 不能等target为0再加入results, 那样左右节点会加两次
            if (target == root.val) {
                list.add(root.val);
                results.add(new ArrayList<Integer>(list));
                list.remove(list.size() - 1); //每次add进list里面后都要删掉, 即便print进了results里面也要记得删除
            }
            return;
        }
        // if (target < root.val) {  //这个不能有, 万一节点有负数呢!!!!!
        //     return;
        // }
        list.add(root.val);
        if (root.left != null)  {
            helper(root.left, target - root.val, list, results);
        }
        if (root.right != null)  {
            helper(root.right, target - root.val, list, results);
        }
        list.remove(list.size() - 1);
    }
}
