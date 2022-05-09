package Tree;

import LintCode.Binary.Tree.TreeNode;
//import Tree.TreeNode;
import java.util.*;

public class Tree94BinaryTreeInorderTraversal {

    class Feb2022MorrisAndStack {
        public List<Integer> morris(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            TreeNode curr = root;
            while (curr != null) {
                if (curr.left == null) {
                    result.add(curr.val);
                    curr = curr.right;
                } else { // Traverse left tree
                    TreeNode predecessor = curr.left;
                    while (predecessor.right != null && predecessor.right != curr) {
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) { //這是第一遍visit, 左邊還沒寫入, 若preorder則這個時候寫入result
                        predecessor.right = curr;
                        curr = curr.left;
                    } else { //等於curr說明是第二遍visit, 說明左子樹已經被寫入了, inorder則這時寫入result
                        predecessor.right = null;
                        result.add(curr.val);
                        curr = curr.right;
                    }
                }
            }

            return result;
        }
        public List<Integer> stack(TreeNode root) {
            List<Integer> output = new ArrayList<>();
            if (root == null) {
                return output;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (!stack.isEmpty() || curr != null) {
                while (curr != null) { // Note that this should be curr != null or curr.left != null
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                output.add(curr.val);
                curr = curr.right;
            }
            return output;
        }
    }

    /**
     * 20211107 10min
     */
    class Nov2021SolutionDivideAndConquer {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper(root, result);
            return result;
            //10:23-10:30-
        }

        private void helper(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                helper(root.left, list);
            }
            list.add(root.val);
            if (root.right != null) {
                helper(root.right, list);
            }
        }
        /**
         * 7/30/2017 Without Helper
         */
        public ArrayList<Integer> inorderTraversalNoHelper(TreeNode root) {
            ArrayList<Integer> results = new ArrayList<>();
            if (root == null) {
                return results;
            }

            results.addAll(inorderTraversalNoHelper(root.left));
            results.add(root.val);
            results.addAll(inorderTraversalNoHelper(root.right));
            return results;
        }
    }

    /**
     * Created by Administrator on 2017/6/29.
     */
    public class LintTree67BinaryTreeInorderTraversal {
        /**
         * 主要分清, 不是postOrder 就不能向results里面放, 要先放stack里面, 所以需要一个指针tn来遍历
         */
        public ArrayList<Integer> inorderTraversal(LintCode.Binary.Tree.TreeNode root) {
            ArrayList<Integer> result = new ArrayList<>();
            Stack<LintCode.Binary.Tree.TreeNode> stack =  new Stack<>();
            LintCode.Binary.Tree.TreeNode tn = root;
            while (tn != null || !stack.empty()) {//是或啊!
                //6/29/2017 又不会写了.....  注意这里加入和指针移动的先后
                while (tn != null) {//一定要记得先push自己, 而不是左边的
                    stack.push(tn);
                    tn = tn.left;
                }
                tn = stack.pop();
                result.add(tn.val);
                tn = tn.right; //6/29/2017 这里又忘了, 不能先push, 必须到左边尽头才能push
            }
            return result;
        }

        public List<Integer> leetcode答案(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
            return result;
        }

    }
}

