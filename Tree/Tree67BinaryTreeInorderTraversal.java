package Tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Administrator on 2017/6/29.
 */
public class Tree67BinaryTreeInorderTraversal {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack =  new Stack<TreeNode>();
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
}
