package Tree;

import java.util.ArrayDeque;
import java.util.Stack;
import java.util.Queue;

public class Tree111MinimumDepthOfBinaryTree {
    //Runtime: 1ms Use: 50min 9/11/2016
    public static int minDepthByRecursive(TreeNode root) {
        if (root == null) { return 0; }
        if (root.left == null && root.right != null) { return minDepthByRecursive(root.right) + 1; }
        if (root.left != null && root.right == null) { return minDepthByRecursive(root.left) + 1; }
        return Math.min(minDepthByRecursive(root.left), minDepthByRecursive(root.right)) + 1;
        //Lots of optimizations: https://discuss.leetcode.com/topic/8723/my-4-line-java-solution/4
    }
    //Runtime: 12ms Use: 30min 9/12/2016 stack处理null还是很方便的
    public static int minDepthByDFS(TreeNode root) {
        if (root == null) { return 0; }
        Stack<TreeNode> ar = new Stack<TreeNode>();
        Stack<Integer> height = new Stack<Integer>();
        int minDepth = -1;
        ar.push(root);
        height.push(1);

        while (!ar.isEmpty()) {
            TreeNode currTN = ar.pop();
            Integer currHeight = height.pop();
            if (currTN == null) { continue; }
            //Deal with the leaf
            if (currTN.left == null && currTN.right == null) {
                if (minDepth == -1) {
                    minDepth = currHeight;
                } else {
                    minDepth = Math.min(minDepth, currHeight);
                }
            } else {
                ar.push(currTN.left); height.push(currHeight + 1);
                ar.push(currTN.right); height.push(currHeight + 1);
            }
        }
        return minDepth;

    }
    //Runtime: 3ms Use: 40min optimized from IterativeDFS
    //Use only 1 while loop and 1 queue:
    //https://discuss.leetcode.com/topic/1948/my-solution-used-level-order-traversal
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


    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(0);
        a.left = b;
        b.right = c;

        System.out.println(minDepthByBFS(a));
    }
}
