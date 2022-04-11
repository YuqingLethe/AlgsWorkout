package BackTracking;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 *
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 */
public class BC437PathSumIII {
    /**
     * July 2017
     * 这一遍才写对. 答案真是聪明. 给了我们教训, recursion的list里面不仅可以存储答案, 也可以存储路径, 即便
     * 不是答案的路径. 这样for循环就可以直接找到答案啦
     */
    public static class Lint246BinaryTreePathSumII {
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

    class CopyAnswer_April2022 {
        int count = 0;
        int target;
        HashMap<Integer, Integer> h = new HashMap();

        public int pathSum(TreeNode root, int targetSum) {
            this.target = targetSum;
            preOrder(root, 0);
            return count;
        }

        private void preOrder(TreeNode root, int currSum) {
            if (root == null) {
                return;
            }
            currSum += root.val;

            if (currSum == target) {
                count ++;
            }
            count += h.getOrDefault(currSum - target, 0);

            h.put(currSum, h.getOrDefault(currSum, 0) + 1);
            preOrder(root.left, currSum);
            preOrder(root.right, currSum);
            h.put(currSum, h.get(currSum) - 1);
        }
    }

    /**
     * Created by Administrator on 2017/8/1.
     */
    public static class Tree472BinaryTreePathSumIII {

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
}
