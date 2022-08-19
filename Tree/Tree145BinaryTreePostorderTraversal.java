package Tree;

import LintCode.Binary.Tree.TreeNode;

import java.util.*;

public class Tree145BinaryTreePostorderTraversal {

    public static class June2017Lint68 {
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
        public ArrayList<Integer> postorderTraversa_iterative(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<>();
            Stack<LintCode.Binary.Tree.TreeNode> stack = new Stack<>();
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
         *
         * Feb2022思路提示: List的時候倒着寫, 用LinkedList.addFirst().
         *
         * https://www.jiuzhang.com/qa/3011/
         */
        public LinkedList<Integer> postorderTraversal3(LintCode.Binary.Tree.TreeNode root) {
            LinkedList<Integer> result = new LinkedList<>();
            Deque<LintCode.Binary.Tree.TreeNode> stack = new ArrayDeque<>();
            LintCode.Binary.Tree.TreeNode p = root;
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
    /**
     * Feb 22
     * 二叉树的后序遍历 题解： 使用栈进行二叉树后序遍历，首先对左子树进行遍历压入栈中，直至左子树为空，然后访问右子树。
     * 故每个节点会被访问两次，当节点被第二次访问时，该节点出栈。
     * https://www.jiuzhang.com/solution/binary-tree-postorder-traversal/
     */
    class Feb2022Iterative {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> output = new ArrayList<>();
            if (root == null) {
                return output;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode prev = null; // previously traversed node
            TreeNode curr = root;
            stack.push(root);
            while (!stack.isEmpty()) {
                curr = stack.peek();
                if (prev == null || prev.left == curr || prev.right == curr) { // From the top peek, need to traverse left tree
                    if (curr.left != null) { //如果左子樹不爲空, 則只加左子樹
                        stack.push(curr.left);
                    } else if (curr.right != null) {
                        stack.push(curr.right);
                    }
                } else if (curr.left == prev) { // Traverse finish on left tree, need to traverse the right tree
                    if(curr.right != null) {
                        stack.push(curr.right);
                    }
                } else { // Traverse finish on right tree. Pop itself.
                    output.add(curr.val);
                    stack.pop();
                }
                prev = curr;
            }
            return output;
        }
    }
}
