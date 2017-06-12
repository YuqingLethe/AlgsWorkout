package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by XiaoMi on 2016/9/7.
 */
public class Tree104MaximumDepthOfBinaryTree {

    /**
     * Rumtime: 1ms  9/11/2016
     * recursive比较好写，但不是自己想的，是看的CC150 Tree Q1
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Runtime: 4ms Use: 20min Iterative BFS / level-order 9/11/2016
     //https://discuss.leetcode.com/topic/27017/clean-java-iterative-solution

     iterative中的BFS/ level-order traversal
     主要就是不知道enqueue后左右两个子树比较大小怎么比较。看了discussion一个人分享的solution知道了。
     1. pop完当前level的所有节点，再Count++. 因此，
     2. ——>需要在每次count之前，储存需要pop的个数就好了
     3. 同理Tree102BinaryTreeLevelOrderTraversal也应该这么处理
     */
    public int maxDepthByBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0; //The result
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) { //while (size-- > 0) {}这样处理可以减少循环末尾自己写的size--；之类的东西. 在一个以index或者size为主要循环条件的时候可以用。
                TreeNode temp = q.remove();
                if (temp.left != null) { q.add(temp.left); }
                if (temp.right != null) { q.add(temp.right); }
            }
            count++;
        }
        return count;
    }
    /**
     * Runtime: 11ms    Use: 15min      IterativeDFS 9/11/2016
     * 不知道一个分支的depth换成另一个分支，还要count–然后再count++这样,怎么知道这是哪一层的呢？
     看了DFSsolution，有个人用的两个stack，难道是预设一个count比这个大的更新count吗？但即便如此，两个stack分右子树也不够用啊。依旧是分支问题

     * DFS: https://discuss.leetcode.com/topic/33826/two-java-iterative-solution-dfs-and-bfs
     1. 用两个stack做DFS，一个负责treeNode，另一个负责记录value
     2. 这样，所有treenodeStack的动作，value都会做一遍
     3. 设一个max作为最大的maxDepth，不停的与value值比较
     * @param root
     * @return
     */
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
                //这个value stack中，两个子节点和value一起push，但value不可用++currValue因为不能改变自己的值，另一个节点还得用呢！只能currValue + 1这样
            }
            if (currTN.right != null) {
                s.push(currTN.right);
                value.push(currValue + 1);
            }
        }
        return maxDepth;
    }

    /**
     * 6/12/2017
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode x = new TreeNode(-1);
        q.add(x);
        while (q.size() != 0) {
            TreeNode tmp = q.poll();
            if (tmp == x)  {
                count++;
                if (q.peek() == null) { //在增加x之前要先判断是不是stack的末尾了, 否则容易陷入死循环
                    break;
                }
                q.add(x);
            } else {
                if (tmp.left != null) {
                    q.add(tmp.left);
                }
                if (tmp.right != null) {
                    q.add(tmp.right);
                }
            }
        }
        return count;
    }

    /**
     * 6/12/2017 九章答案版本
     */
    int dep; //在哪里初始化别忘了
    public int maxDepthTraverse(TreeNode root) {
        dep = 0;
        helper(root, 1);
        return dep;
    }
    private void helper(TreeNode root, int currDepth) {
        if (root == null) {
            return;
        }
        if (currDepth > dep) {
            dep = currDepth;
        }
        helper(root.left, currDepth + 1);
        helper(root.right, currDepth + 1);
    }
}
