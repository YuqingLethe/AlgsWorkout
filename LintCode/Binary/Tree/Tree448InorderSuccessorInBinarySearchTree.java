package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/8/1.
 */
public class Tree448InorderSuccessorInBinarySearchTree {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root != p) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return successor;
        }
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
