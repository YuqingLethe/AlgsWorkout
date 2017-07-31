package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Administrator on 2017/7/30.
 */
public class Tree66BinaryTreePreorderTraversal {
    /**
     * 7/30/2017 还是不会写, 看来要死记硬背了
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tn = stack.pop();
            results.add(tn.val);
            if (tn.right != null) {
                stack.push(tn.right);
            }
            if (tn.left != null) {
                stack.push(tn.left);
            }
        }
        return results;
    }

    public class Solution {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> results = new ArrayList<>();
            if (root == null) {
                return results;
            }
            traverse(root, results);
            return results;
        }
        private void traverse(TreeNode head, ArrayList<Integer> results) {
            if (head == null) {
                return;
            }
            results.add(head.val);
            traverse(head.left, results);
            traverse(head.right, results);
        }
    }
}
