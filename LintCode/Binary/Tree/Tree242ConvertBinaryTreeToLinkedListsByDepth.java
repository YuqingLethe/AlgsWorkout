package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Tree242ConvertBinaryTreeToLinkedListsByDepth {
    /**
     * 6/23/2017 对lastNode 和dummy的应用
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        List<ListNode> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        ListNode dummy = new ListNode(0);
        ListNode lastNode = null; //会用lastNode吗?

        while(!qu.isEmpty()) {
            int size = qu.size();
            dummy.next = null;
            lastNode = dummy;
            for (int i = 0; i < size; i++)  { //注意while里面用size不是index, 不能为0
                TreeNode tn = qu.poll();
                lastNode.next = new ListNode(tn.val);
                lastNode = lastNode.next;
                if (tn.left != null) {
                    qu.add(tn.left);
                }
                if (tn.right != null) {
                    qu.add(tn.right);
                }
            }
            results.add(dummy.next); //技巧很好, linkedlist的特点就是可以给个fake head然后直接传递后面的值
        }
        return results;
    }
}
