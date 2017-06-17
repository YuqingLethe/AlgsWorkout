package LintCode.Binary.Tree;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Tree453FlattenBinaryTreeToLinkedList {
    /**
     * Recursion
     */
    public void flattenRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            if (root.right == null) {
                root.right = root.left;
                root.left = null;
            } else {
                TreeNode tmp = root.right;
                root.right = root.left;
                root.left = null;
                TreeNode goRightmost = root.right;
                while (goRightmost.right != null) {
                    goRightmost = goRightmost.right;
                }
                goRightmost.right = tmp;
            }
        }
        flattenRecursion(root.right); //Flattten right是无论有没有左子节点都要做的
    }

    /**
     * Iterative方法
     * http://www.jiuzhang.com/solutions/flatten-binary-tree-to-linked-list/
     */
    public void flattenIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.empty()) {
            TreeNode tn = s.pop();
            if (tn.right != null) {
                s.push(tn.right);
            }
            if (tn.left != null) {
                s.push(tn.left);
            }
            tn.left = null;
            if (s.empty()) { //这个判断要是忘了, 之后s.peek()就会出错, 因为若为空, 用这个peek就会返回EmptyStackException
                tn.right = null;
            } else {
                tn.right = s.peek();//这一步无论有没有子节点, 都要和stack里面的TreeNode连上, 不然就断啦
            }
        }
    }
}
