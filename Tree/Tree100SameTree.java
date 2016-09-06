package Tree;

import java.util.LinkedList;
import java.util.Stack;

public class Tree100SameTree {
    //Runtime: 1ms Use: 20min 9/5/2016 First time do tree problem...Pass at the first time! "BFS"
    public static boolean isSameTreeByBFS (TreeNode p, TreeNode q) {
        LinkedList<TreeNode> llP = new LinkedList<TreeNode>(); //store current depth and the next depth treenodes
        LinkedList<TreeNode> llQ = new LinkedList<TreeNode>();
        llP.add(p);
        llQ.add(q);
        while (llP.size() > 0 && llQ.size() >0 ) {
            //Deal with null treenodes
            if (llP.peek() == null && llQ.peek() == null) {
                llP.remove();
                llQ.remove();
            } else {
                TreeNode a = llP.peek();
                TreeNode b = llQ.peek();
                if (llP.peek() == null || llQ.peek() == null) return false;
                //deal with values of treenodes
                else if (a.val != b.val) return false;
                else {
                    llP.add(a.left); llP.add(a.right);
                    llQ.add(b.left); llQ.add(b.right);
                    llP.remove(); llQ.remove();
                }
            }
        }
        return true;
    }

    //Runtime: 0ms 9/5/2016
    // " recursive" from https://discuss.leetcode.com/topic/57170/summary-of-java-solutions
    public static boolean isSameTreeByIteration (TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return q==p;
        }
        return p.val == q.val
                && isSameTreeByIteration(p.left, q.left)
                && isSameTreeByIteration(p.right, q.right);
    }
    //runtime: 1ms Use: 0.5hr 9/6/2016
    //"Can use two stacks as BFS, but can use only one stack also
    public static boolean isSameTreeByDFS (TreeNode p, TreeNode q) {
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
    public static void main(String[] args) {
        TreeNode p = null;
        TreeNode q = null;
        System.out.println(isSameTreeByDFS(p, q));
    }
}
