package Tree;

import java.util.LinkedList;

/**
 * Created by XiaoMi on 2016/9/24.
 */
public class Tree110BalancedBinaryTree {
    //It has logic flaw 9/24/2016 Use: 15min
    public boolean isBalancedByDifferNode(TreeNode root) {
        if (    root == null
                ||
                (root != null && root.left == null && root.right == null)
            ) {
            return true;
        }
        LinkedList<TreeNode> lltn = new LinkedList<TreeNode>();
        lltn.add(root);
        while(!lltn.isEmpty()) {
            TreeNode curr = lltn.poll();
            if (curr.left != null && curr.right != null) {
                lltn.add(curr.left);
                lltn.add(curr.right);
            //Find the Node that made the difference
            }else if (curr.left == null && curr.right != null) {
                if (curr.right.left != null || curr.right.right != null) { return false; }
                else { lltn.add(curr.right); }
            } else if (curr.right == null && curr.left != null){
                if (curr.left.left != null || curr.left.right != null) { return false; }
                else { lltn.add(curr.left); }
            }
        }
        return true;
    }

    //Use: 25min logic Flaw. Didn't understand the question. 9/24/2016
    public boolean isBalancedByTraveresal(TreeNode root) {
        if (    root == null
                ||
                (root != null && root.left == null && root.right == null)
                ) {
            return true;
        }
        LinkedList<TreeNode> lltn = new LinkedList<TreeNode>();
        lltn.add(root);
        int height = 0;
        boolean missingNodeFlag = false;

        while (!lltn.isEmpty()) {
            int size = lltn.size();
            if (size < Math.pow(2, height)) {
                missingNodeFlag = true;
            };
            //Enqueue all the children of current level nodes
            while (--size >= 0) {
                TreeNode curr = lltn.poll();
                if (curr.left != null) { lltn.add(curr.left); }
                if (curr.right != null) { lltn.add(curr.right); }

            }
            //if the current level is not full and the next level exists
            if (missingNodeFlag && lltn.size() > 0) {
                return false;
            }
            height++;
        }
        return true;
    }
    //Runtime: 3ms Use: 12min
    public static boolean isBalancedBySubHeightRecursive(TreeNode root) {
        if (root == null ||
            (root != null && root.left == null && root.right == null)
            ) {
            return true;
        }
        if (Math.abs(binaryTreeHeight(root.left) - binaryTreeHeight(root.right)) > 1) {
            return false;
        }
        if (!isBalancedBySubHeightRecursive(root.left) || !isBalancedBySubHeightRecursive(root.right)) {
            return false;
        }
        return true;
    }

    private static int binaryTreeHeight (TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(binaryTreeHeight(root.left), binaryTreeHeight(root.right));
    }

    //
    //https://discuss.leetcode.com/topic/11007/java-solution-based-on-height-check-left-and-right-node-in-every-recursion-to-avoid-further-useless-search/4

    /**
     * Runtime: 1ms Passed First Time Yay!
     * Comparing the subtree's height in the getDepth(). O(n) instead of O(n^2)
     * Get the idea in "https://discuss.leetcode.com/topic/11007/java-solution-based-on-height-check-left-and-right-node-in-every-recursion-to-avoid-further-useless-search"
     * @param root
     * @return
     */
    public static boolean isBalancedBySubHeight(TreeNode root) {
        if (root == null || (root != null && root.left == null && root.right == null)) {
            return true;
        }
        int leftHeight = getDepth(root.left);
        int rightHeight = getDepth(root.right);
        if (
                (leftHeight == -1 || rightHeight == -1)
                ||
                (Math.abs(leftHeight - rightHeight) > 1)
            ) {
            return false;
        }else {
            return true;
        }

    }

    private static int getDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = getDepth(root.left);
        //If the left child is balanced, we search the other one
        if (leftDepth != -1) {
            int rightDepth = getDepth(root.right);
            //If the right child is balanced
            if (rightDepth != -1) {
                return Math.abs(leftDepth - rightDepth) > 1 ?  -1 :  1 + Math.max(leftDepth, rightDepth);
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        a.left = b;
        System.out.println(isBalancedBySubHeight(a));
    }

}
