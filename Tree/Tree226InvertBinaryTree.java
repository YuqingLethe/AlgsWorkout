package Tree;

import java.util.LinkedList;

public class Tree226InvertBinaryTree {
    //Runtime: 1ms Same w/ Tree101MirrorTree()
    //"Change the child nodes"
    public TreeNode invertTree (TreeNode root) {
        LinkedList<TreeNode> lltree = new LinkedList<TreeNode>();
        lltree.add(root);
        while (lltree.size() > 0) {
            while (lltree.peek() == null) {
                lltree.remove();
                if (lltree.size() == 0)
                    return root;
            }
            TreeNode temp = lltree.peek().left;
            lltree.peek().left = lltree.peek().right;
            lltree.peek().right = temp;

            lltree.add(lltree.peek().left);
            lltree.add(lltree.peek().right);
            lltree.remove();
        }
        return root;
    }
    //Runtime:X
    //"Change the val only, by DFS, but CAN'T do, as child nodes need to be switch also"
/*    public static TreeNode invertTreeByVal(TreeNode root) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        if (root != null) { s.push(root); }
        while (s.size() > 0) {
            TreeNode sn = s.pop();
            if (sn.left == null && sn.right != null) {
                sn.left = sn.right;
                sn.right = null;
                s.push(sn.left);
            } else if (sn.left != null && sn.right == null) {
                sn.right = sn.left;
                sn.left = null;
                s.push(sn.right);
            } else if (sn.left != null && sn.right != null){
                int temp = sn.left.val;
                sn.left.val = sn.right.val;
                sn.right.val = temp;
                s.push(sn.left);
                s.push(sn.right);
            }
        }
        return root;
    }
*/
    //Runtime: 0ms  Use: 20min
    public static TreeNode invertTreeByRecursive(TreeNode root) {
        if (root == null) return root;
        if (root.left != null || root.right != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        if (root.left != null) root.left = invertTreeByRecursive(root.left);
        if (root.right != null) root.right = invertTreeByRecursive(root.right);
        return root;
    }

    //https://discuss.leetcode.com/topic/51588/java-very-simple-basic-recursion
    public static TreeNode invertTreeByRecursive2(TreeNode root) {
        if (root == null) return root;
        root.left = invertTreeByRecursive2(root.left);
        root.right = invertTreeByRecursive2(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public static void main(String[] args) {
        int[] ar = {4,2,7,1,3,6,9};
//        TreeNode tn = TreeNode.buildTreeByArray(ar);
        TreeNode root = null;
        System.out.println(invertTreeByRecursive(root).val);
    }
}

