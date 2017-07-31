package LintCode.Binary.Tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Administrator on 2017/6/29.
 */
public class Tree67BinaryTreeInorderTraversal {
    /**
     * 主要分清, 不是postOrder 就不能向results里面放, 要先放stack里面, 所以需要一个指针tn来遍历
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack =  new Stack<>();
        TreeNode tn = root;
        while (tn != null || !stack.empty()) {//是或啊!
            //6/29/2017 又不会写了.....  注意这里加入和指针移动的先后
            while (tn != null) {//一定要记得先push自己, 而不是左边的
                stack.push(tn);
                tn = tn.left;
            }
            tn = stack.pop();
            result.add(tn.val);
            tn = tn.right; //6/29/2017 这里又忘了, 不能先push, 必须到左边尽头才能push
        }
        return result;
    }
    /**
     * 7/30/2017
     */
    public ArrayList<Integer> inorderTraversalDC(TreeNode root) {
        ArrayList<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        results.addAll(inorderTraversalDC(root.left));
        results.add(root.val);
        results.addAll(inorderTraversalDC(root.right));
        return results;
    }
}
