package Tree;

import java.util.*;

public class Tree102BinaryTreeLevelOrderTraversal {
    //Runtime: 3ms Use: 13min 9/11/2016 Add to the list level by level
    public  List<List<Integer>> levelOrderByBFS(TreeNode root) {

        List<Integer> currLevel = new LinkedList<Integer>();//其实可以在while里面定义的，没必要在这里定义
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int currSize = queue.size();
            while (currSize-- > 0) {
                TreeNode tempTN = queue.remove();
                currLevel.add(tempTN.val);
                if (tempTN.left != null) { queue.add(tempTN.left); }
                if (tempTN.right != null) { queue.add(tempTN.right); }
            }
            result.add(currLevel);
            currLevel = new LinkedList<Integer>();
        }
        return result;
    }



    //9/11/2016 Runtime: 4ms Use: 1hr Use BFS but add to the list node-by-node
    //HOLD 9/7/2016 Cannot use index and 2*n - 1
    public  List<List<Integer>> levelOrderByBFSWithAddToList(TreeNode root) {

        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) {
            return result;
        }

        int level = 0; //index of level order
        //store the TreeNodes of the current Level and the lower level
        Queue<TreeNode> treeLL = new LinkedList<TreeNode>();
        treeLL.add(root);

        while (!treeLL.isEmpty()) {
            int size = treeLL.size(); //get the number of nodes in the current level
            while (size-- > 0) {
                TreeNode tn = treeLL.remove();
                addToList(result, tn, level);
                if (tn.left != null) {
                   treeLL.add(tn.left);
                }
                if (tn.right != null) {
                    treeLL.add(tn.right);
                }
            }
            level++;
        }
        return result;
    }

    private static List<List<Integer>> addToList(List<List<Integer>> list, TreeNode tn, int height) {
        if (tn == null) { return list; }
        List<Integer> currLevel = new LinkedList<Integer>();//The (height + 1)th level
        if (list.size() >= height + 1) {
            currLevel = list.remove(height);
            currLevel.add(tn.val);
            list.add(height, currLevel);
        } else {
            currLevel.add(tn.val);
            list.add(currLevel);
        }
        return list;
    }

    //Runtime: 1ms  Use: 30min+   Recursive DFS
    //https://discuss.leetcode.com/topic/7332/java-solution-using-dfs
    public List<List<Integer>> LevelOrder(TreeNode root) {
        List<List<Integer>> resultLL = new ArrayList<List<Integer>>();
        levelHelper(resultLL, root, 0);
        return resultLL;
    }

    private static void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }

    //Runtime: 4ms Done on 9/24/2016. Use stack but push right node first.
    //Pay attention when result.size() <= currHeight
    public List<List<Integer>> LevelOrderByDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) { return result; }
        Stack<TreeNode> tnStack = new Stack<TreeNode>();
        Stack<Integer> heightStack = new Stack<Integer>();
        tnStack.push(root);
        heightStack.push(0);

        while(!tnStack.isEmpty()) {
            TreeNode currTN = tnStack.pop();
            Integer currHeight = heightStack.pop();
            if(result.size() > currHeight) {
                result.get(currHeight).add(currTN.val);
            } else {
                List<Integer> tempList = new ArrayList<Integer>();
                tempList.add(currTN.val);
                result.add(tempList);
            }

            if (currTN.left != null) {
                tnStack.push(currTN.left); heightStack.push(currHeight + 1);
            }
            if (currTN.right != null) {
                tnStack.push(currTN.right); heightStack.push(currHeight + 1);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] ar = {0,1,2,3,4,5,6};
        TreeNode root = TreeNode.buildTreeByArray( ar);
    }

}
