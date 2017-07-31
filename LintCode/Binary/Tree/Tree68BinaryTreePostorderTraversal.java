package LintCode.Binary.Tree;

import Tree.*;

import java.util.*;

/**
 * Created by Administrator on 2017/6/29.
 */
public class Tree68BinaryTreePostorderTraversal {
    /**
     * Divide and conquer真的太好用了
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        results.addAll(postorderTraversal(root.left));
        results.addAll(postorderTraversal(root.right));
        results.add(root.val);

        return results;
    }

    /**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversa2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;
        //如果在while之前push root, 则需要提前判断root为null的情况
        if (root == null) {
            return result;
        }

        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.add(curr.left);
                } else if (curr.right != null) { //6/29/2017这里是else if而不是if
                    stack.add(curr.right);
                }
            } else if (curr.left == prev) {
                if (curr.right != null) {
                    stack.add(curr.right);
                }
            } else {
                curr = stack.pop();
                result.add(curr.val);
            }
            prev = curr;
        }
        return result;
    }

    /**
     * 看不懂 7/30/2017
     * 用迭代方法实现postorder
     * https://www.jiuzhang.com/qa/3011/
     */
    public LinkedList<Integer> postorderTraversal3(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.addFirst(p.val); // Reverse the process of preorder
                p = p.right; // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                p = node.left; // Reverse the process of preorder
            }
        }
        return result;
    }
}
