package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public class Tree472BinaryTreePathSumIII {
    class ParentTreeNode {
         public int val;
        public ParentTreeNode parent, left, right;
    }
    /**
     * 如果不加father, 就会无限循环了, 就runtime error了
     */
    public class Solution {

        public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
            List<List<Integer>> results = new ArrayList<>();
            dfs(root, target, results);
            return results;
        }
        private void dfs(ParentTreeNode root, int target, List<List<Integer>> results) {
            if (root == null) {
                return;
            }
            List<Integer> list = new ArrayList<>();
            findSum(root, null, target, list, results);

            dfs(root.left, target, results);
            dfs(root.right, target, results);

        }
        private void findSum(ParentTreeNode root,
                             ParentTreeNode father,
                             int target,
                             List<Integer> list,
                             List<List<Integer>> results) {
            list.add(root.val);
            target -= root.val;
            if (target == 0) {
                results.add(new ArrayList<Integer>(list));
            }
            if (root.parent != null && root.parent != father) { //为什么一定要有father啊
                findSum(root.parent, root, target, list, results);
            }
            if (root.left != null && root.left != father) {
                findSum(root.left, root, target, list, results);
            }
            if (root.right != null && root.right != father) {
                findSum(root.right, root, target, list, results);
            }
            list.remove(list.size() - 1);
        }
        private void print(List<Integer> list) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        private void printResult(List<List<Integer>> results) {
            System.out.println("Result =====");
            for (List<Integer> l : results) {
                print(l);
            }
            System.out.println("==============");
        }
    }
}
