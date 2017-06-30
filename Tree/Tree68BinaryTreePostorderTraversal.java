package Tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Administrator on 2017/6/29.
 */
public class Tree68BinaryTreePostorderTraversal {
    /**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;
        //如果在while之前push root, 则需要提前判断root为null的情况
        if (root == null) {
            return result;
        }

        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.add(curr.left);
                } else if (curr.right != null) { //6/29/2017这里是else if而不是if
                    stack.add(curr.right);
                }
            } else if (curr.left == prev) {
                if (curr.right != null) {
                    stack.add(curr.right);
                }
            } else {
                curr = stack.pop();
                result.add(curr.val);
            }
            prev = curr;
        }
        return result;
    }
}
