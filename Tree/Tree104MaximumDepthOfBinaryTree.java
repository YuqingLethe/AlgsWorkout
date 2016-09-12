package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by XiaoMi on 2016/9/7.
 */
public class Tree104MaximumDepthOfBinaryTree {
    //Rumtime: 1ms  9/11/2016
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //Runtime: 4ms Use: 20min Iterative BFS / level-order 9/11/2016
    //https://discuss.leetcode.com/topic/27017/clean-java-iterative-solution
    public int maxDepthByBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0; //The result
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode temp = q.remove();
                if (temp.left != null) { q.add(temp.left); }
                if (temp.right != null) { q.add(temp.right); }
            }
            count++;
        }
        return count;
    }

    //Runtime: 11ms    Use: 15min      IterativeDFS 9/11/2016
    //https://discuss.leetcode.com/topic/33826/two-java-iterative-solution-dfs-and-bfs
    public int maxDepthByDFS(TreeNode root) {
        if (root == null) {  return 0; }
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<Integer> value = new Stack<Integer>();
        int maxDepth = 0;
        s.push(root);
        value.push(1);
        while (!s.isEmpty()) {
            TreeNode currTN = s.pop();
            Integer currValue = value.pop();
            maxDepth = Math.max(maxDepth, currValue);

            if (currTN.left != null) {
                s.push(currTN.left);
                value.push (currValue + 1);
            }
            if (currTN.right != null) {
                s.push(currTN.right);
                value.push(currValue + 1);
            }
        }
        return maxDepth;
    }
}
