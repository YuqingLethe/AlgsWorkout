package Tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by XiaoMi on 2016/9/5.
 */
public class Tree101SymmetricTree {
    //Runtime: 6ms Use: 20min
    public boolean isSymmetricBySameTree(TreeNode root) {
        if (root == null) return true;
        TreeNode leftHalfSymmed = MirrorTree(root.left);
        TreeNode p = leftHalfSymmed;
        TreeNode q = root.right;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (p != null) { stack.push(p); }
        if (q != null) { stack.push(q); }

        while (!stack.isEmpty() && stack.size()%2 == 0) {
            TreeNode pn = stack.pop();
            TreeNode qn = stack.pop();
            if (pn.val != qn.val) return false;
            if (pn.right != null) stack.push(pn.right);
            if (qn.right != null) stack.push(qn.right);
            if (stack.size()%2 != 0) return false;
            if (pn.left != null) stack.push(pn.left);
            if (qn.left != null) stack.push(qn.left);
            if (stack.size()%2 != 0) return false;
        }
        if (stack.size()%2 != 0) { return false; }
        else { return true; }
    }
    //Use: 10min
    private static TreeNode MirrorTree (TreeNode root) {
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

    //Runtime: 2ms Use: 1hr "BFS: When enqueue, exchange left node and right node"
    public static boolean isSymmetricByBFS (TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> llLeft = new LinkedList<TreeNode>();
        LinkedList<TreeNode> llRight = new LinkedList<TreeNode>();
        if (root.left != null) llLeft.add(root.left);
        if (root.right != null)llRight.add(root.right);

        while(!llLeft.isEmpty() && !llRight.isEmpty()) {
//        while (llLeft.size() > 0 && llRight.size() > 0) {
            TreeNode l = llLeft.poll();
            TreeNode r = llRight.poll();
            if (l.val != r.val) { return false; }
            if (l.right != null) { llLeft.add(l.right); }
            if (r.left != null) { llRight.add(r.left); }
            if (llLeft.size() != llRight.size()) { return false; }
            if (l.left != null) { llLeft.add(l.left); }
            if (r.right != null) { llRight.add(r.right); }
            if (llLeft.size() != llRight.size()) { return false; }
        }
        if(!llLeft.isEmpty() || !llRight.isEmpty())
            return false;
        return true;
    }
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        a.left = b;
        a.right = c;

        a = MirrorTree(a);
        System.out.println(a.left.val);
    }
}
