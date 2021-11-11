package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by XiaoMi on 2016/9/5.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (int x) { val = x;}

    public static TreeNode buildTreeBy7Numbers(int[] treeArray) {
        TreeNode[] tnArray = new TreeNode[treeArray.length];
        for (int i = 0; i < treeArray.length; i++) {
            System.out.println(treeArray[i]);
            tnArray[i].val = treeArray[i];
        }
        tnArray[0].left = tnArray[1];
        tnArray[0].right = tnArray[2];
        tnArray[1].left = tnArray[3];
        tnArray[1].right = tnArray[4];
        tnArray[2].left = tnArray[5];
        tnArray[2].right = tnArray[6];

        return tnArray[0];
    }

    public static TreeNode createTreeByArray(Integer[] array) {
        if (array == null || array.length==0) {
            return null;
        }

        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        Queue<Integer> integerQueue = new LinkedList<>();
        for (int i = 1; i < array.length; i++) {
            integerQueue.offer(array[i]);
        }

        TreeNode treeNode = new TreeNode(array[0]);
        treeNodeQueue.offer(treeNode);

        while (!integerQueue.isEmpty()){
            Integer leftVal = integerQueue.isEmpty() ? null : integerQueue.poll();
            Integer rightVal = integerQueue.isEmpty() ? null : integerQueue.poll();
            TreeNode current = treeNodeQueue.poll();
            if (leftVal !=null) {
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                treeNodeQueue.offer(left);
            }
            if (rightVal !=null){
                TreeNode right = new TreeNode(rightVal);
                current.right = right;
                treeNodeQueue.offer(right);
            }
        }
        return treeNode;
    }

    public static void printTreeNodeStack(Stack<TreeNode> s) {
        Stack<TreeNode> stack = (Stack)s.clone();
        StringBuilder sb = new StringBuilder("Nodes Stack Backwards: ");
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            sb.append(curr.val + " ");
        }
        System.out.println(sb.toString());
    }
}

