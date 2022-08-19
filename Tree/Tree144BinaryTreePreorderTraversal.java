package Tree;

import java.util.*;

public class Tree144BinaryTreePreorderTraversal {

    class preorderTraversalFeb2022 {
        final List<Integer> recursiveList = new ArrayList<>();
        public List<Integer> recursive(TreeNode root) {
            if (root == null) {
                return recursiveList;
            }
            recursiveList.add(root.val);
            recursive(root.left);
            recursive(root.right);
            return recursiveList;
        }

        public List<Integer> stack(TreeNode root) {
            List<Integer> output = new ArrayList<>();
            if (root == null) {
                return output;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                output.add(curr.val);
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
            return output;
        }

        /**
         * Approach 2: Morris traversal
         * https://www.youtube.com/watch?v=wGXB9OWhPTg
         */
        public List<Integer> Morris(TreeNode root) {
            TreeNode curr = root;
            List<Integer> output = new ArrayList<>();
            while (curr != null) {
                // 什麼時候寫入output? 爲什麼是左邊null才寫?
                if (curr.left == null) {
                    output.add(curr.val);
                    curr = curr.right;
                } else { //遍歷左子樹
                    TreeNode predecessor = curr.left;
                    while (predecessor.right != curr && predecessor.right != null) { //find the predecessor
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == curr) {
                        predecessor.right = null;
                        curr = curr.right;
                    } else { // processor.right == null;爲何這個時候寫入output? 要寫左子樹了, 先把父親節點寫入
                        output.add(curr.val);
                        predecessor.right = curr;
                        curr = curr.left;
                    }
                }
            }
            return output;
        }
    }

    /**
     * Created by Administrator on 2017/7/30.
     */
    public static class LintTree66BinaryTreePreorderTraversal {
        /**
         * 7/30/2017 还是不会写, 看来要死记硬背了
         */
        public ArrayList<Integer> preorderTraversal(LintCode.Binary.Tree.TreeNode root) {
            ArrayList<Integer> results = new ArrayList<>();
            if (root == null) {
                return results;
            }
            Stack<LintCode.Binary.Tree.TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()) {
                LintCode.Binary.Tree.TreeNode tn = stack.pop();
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

        public class Recursive {
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
}
