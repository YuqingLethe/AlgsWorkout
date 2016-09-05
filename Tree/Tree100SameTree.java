package Tree;

import java.util.LinkedList;

/**
 * Created by XiaoMi on 2016/9/5.
 */
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
}
