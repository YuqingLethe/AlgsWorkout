package LintCode.Binary.Tree;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Tree453FlattenBinaryTreeToLinkedList {
    /**
     * Recursion Traverse 2017/6/16.
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
     * Recursion Divide and Conquer还有许多小细节不熟练 2017/6/18.
     * 细节1: helper考虑没有左子树节点的特殊处理
     * 细节2: 叶子节点的处理, return
     * 细节3: root.right在这道里面不需要单独储存, 可以直接连接到leftLast上
     */
    public void flatten(TreeNode root) {
        helper(root);
    }
    //Return the last node of the flattened tree
    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);
        if (leftLast != null) {
            leftLast.right = root.right;
            //以下两行虽然root没有左子树时候执行不出错, 但root无左子树时不需要执行, 所以放在if里面
            root.right = root.left;
            root.left = null;
        }

        if (rightLast != null) { //不要忘了return时候rightLast不存在则需要return leftLast
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }

        return root;//leftlast rigihtLast都不存在, return root....
    }

    /**
     * Iterative方法 2017/6/16.
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
