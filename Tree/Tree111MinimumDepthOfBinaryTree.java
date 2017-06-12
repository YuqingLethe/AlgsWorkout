package Tree;

import java.util.ArrayDeque;
import java.util.Stack;
import java.util.Queue;

/**
 * 题目要求，一定要到了leaf才算，因此root比较特殊
 * 数据结构: ArrayList does not implement Queue, but we can use LinkedListor ArrayDeque instead. and ArrayDeque is faster.
 */
public class Tree111MinimumDepthOfBinaryTree {
    //Runtime: 1ms Use: 50min 9/11/2016
    public static int minDepthByRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right != null) {
            return minDepthByRecursive(root.right) + 1;
        }
        if (root.left != null && root.right == null) {
            return minDepthByRecursive(root.left) + 1;
        }
        return Math.min(minDepthByRecursive(root.left), minDepthByRecursive(root.right)) + 1;
        //Lots of optimizations: https://discuss.leetcode.com/topic/8723/my-4-line-java-solution/4
    }
    /**
     * Runtime: 12ms Use: 30min 9/12/2016 stack处理null还是很方便的
     * * Tricks 1. 找最大值最小值问题的初始值

     Deal with the first depth as minDepth. See minDepthByBFS()
     最小值和最大值略不同。找最大值的时候随便初始化一个0中就会被代替，但是最小值只能找第一个有效值来赋值，只能初始化一个无效值。
     上述的逻辑有问题。他们都必须初始化一个无效值，或者一个小于所有possibilites的值。只不过maximum比较好找，因为tree的个数最小为0.但minimum不好找，因为tree并没有个数的峰值。
     因此minimum应该不赋值，找到第一个有效值的时候加以判断是否初始化，没有初始化则赋值。
     如果minimum必须初始化，则是个永远不会出现的数比如-1或者null作为‘有待赋值’的标志
     */
    public static int minDepthByDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> ar = new Stack<TreeNode>();
        Stack<Integer> height = new Stack<Integer>();
        int minDepth = -1;
        ar.push(root);
        height.push(1);

        while (!ar.isEmpty()) {
            TreeNode currTN = ar.pop();
            Integer currHeight = height.pop();
            if (currTN == null) {
                continue;
            }
            //Deal with the leaf
            if (currTN.left == null && currTN.right == null) {
                if (minDepth == -1) {
                    minDepth = currHeight;
                } else {
                    minDepth = Math.min(minDepth, currHeight);
                    //break; 为什么这里不能break? 因为用的DFS方法, 必须traverse完所有节点, 比如最小depth在右子树,
                    //而最先发现的叶子节点在左子树, 不一定是最小的. 如果break一定出错.
                }
            } else {
                ar.push(currTN.left); height.push(currHeight + 1);
                ar.push(currTN.right); height.push(currHeight + 1);
            }
        }
        return minDepth;

    }
    /**
     * Runtime: 3ms Use: 40min optimized from IterativeDFS
     //Use only 1 while loop and 1 queue:
     //https://discuss.leetcode.com/topic/1948/my-solution-used-level-order-traversal

     Trick1: BFS相比于DFS可以简化的地方

     https://discuss.leetcode.com/topic/1948/my-solution-used-level-order-traversal
     思路：
     因为depth变成了常量，因此只能每一层traverse一遍后加1
     而判断level结束，除了之前获取的本层size() 以外还有一个方法，就是设一个特殊值在每个level后面。这样每当==特殊值，并且queue里面还有别的tn，说明本层结束，下一层待traverse。就depth++
     level-order traversal and record current level depth, when meet a node which both child is null then return, no need to go farther再次是因为从左到右按顺序，遇到的第一个左右子树为空的节点必定为minimumDepth，因此直接return就可以了，不需要往下进行。
     细节处理请看Tree111的maxDepthByBFS()

     */
    public static int minDepthByBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        TreeNode empty = new TreeNode(0);
        q.add(empty);
        int dep = 1;

        while (!q.isEmpty()) {
            TreeNode currTN = q.remove();
            if (currTN == empty) {
                if (!q.isEmpty()) {
                    q.add(empty);
                    dep++;
                }
                continue;
            }
            if (currTN.left == null && currTN.right == null) { return dep; }
            if (currTN.left != null) { q.add(currTN.left); }
            if (currTN.right != null) { q.add(currTN.right); }
        }
        return dep;
    }
    /**
     * 6/12/2017 九章答案版本
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int min = helper(root);
        return min;
    }
    private int helper(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (root.left != null) {
            left = helper(root.left);
        }
        if (root.right != null) {
            right = helper(root.right);
        }
        return Math.min(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(0);
        a.left = b;
        b.right = c;

        System.out.println(minDepthByBFS(a));
    }
}
