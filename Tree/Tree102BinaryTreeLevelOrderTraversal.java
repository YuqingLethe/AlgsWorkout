package Tree;

import java.util.*;

public class Tree102BinaryTreeLevelOrderTraversal {
    class BFS_SizeOfLevel {
        /**
         * 比较完全的四种方法: http://www.jiuzhang.com/solutions/binary-tree-level-order-traversal/
         * Runtime: 3ms Use: 13min 9/11/2016
         * Add to the list level by level
         */
        public  List<List<Integer>> levelOrderByBFS1(TreeNode root) {

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

        /**
         * 9/11/2016 Runtime: 4ms Use: 1hr
         * Use BFS but add to the list node-by-node helper: AddToList()
         */
        public  List<List<Integer>> levelOrderByBFS3(TreeNode root) {

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
        private List<List<Integer>> addToList(List<List<Integer>> list, TreeNode tn, int height) {
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
    }

    class BFS_LevelDivider {
        /**
         * Sep 2022 8:10-8:28
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            TreeNode endOfLevel = new TreeNode(0);
            q.add(endOfLevel);
            List<Integer> levelList = new ArrayList<>();

            while (!q.isEmpty()) {
                TreeNode curr = q.poll();
                // Each level ends with a special TreeNode. If reach that node, add current levelList to the result.
                if (curr == endOfLevel) {
                    result.add(new ArrayList<>(levelList));
                    levelList = new ArrayList<>();
                    if (q.size() > 0) { //注意这里别忘记, 否则遍历结束后会死循环
                        q.add(endOfLevel);
                    } else { // Finished all nodes
                        break;
                    }
                } else { // Otherwise, add current level's treenode's children to the Queue.
                    levelList.add(curr.val);
                    if (curr.left != null) {
                        q.add(curr.left);
                    }
                    if (curr.right != null) {
                        q.add(curr.right);
                    }
                }
            }
            return result;
        }

        /**
         * 6/20/2017 BFS iterative with dummy node
         * With Special TreeNode to tell current level
         */
        public ArrayList<ArrayList<Integer>> levelOrderBFS2(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            if (root == null) {
                return results;
            }

            q.add(root);
            TreeNode sp = new TreeNode(-1); //use as a divider of each level
            q.add(sp);
            ArrayList<Integer> intList = new ArrayList<>();

            while (!q.isEmpty()) {
                TreeNode tn = q.poll();
                if (tn == sp) {
                    results.add(intList);
                    if (q.peek() == null) {
                        break;
                    }
                    intList = new ArrayList<>();
                    q.add(sp);
                    continue;
                }
                if (tn.left != null) {
                    q.add(tn.left);
                }
                if (tn.right != null) {
                    q.add(tn.right);
                }
                //一个q 一个intList, 一个在if里面加, 所以root要提前加入, 一个pop之后再加, 所以在while里面
                intList.add(tn.val);
            }
            return results;
        }
    }



    /**
     * Runtime: 1ms  Use: 30min+   Recursive DFS
     * https://discuss.leetcode.com/topic/7332/java-solution-using-dfs
     */
    class DFS_Recursive {
        public List<List<Integer>> LevelOrderDFS(TreeNode root) {
            List<List<Integer>> resultLL = new ArrayList<List<Integer>>();
            levelHelper(resultLL, root, 0);
            return resultLL;
        }

        private void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
            if (root == null) return;
            if (height >= res.size()) {
                res.add(new LinkedList<Integer>());
            }
            res.get(height).add(root.val);
            levelHelper(res, root.left, height+1);
            levelHelper(res, root.right, height+1);
        }
    }

    class DFS_Iterative {
        /**
         * Runtime: 4ms Done on 9/24/2016. DFS iterative
         * Use stack but push right node first.
         * Pay attention when result.size() <= currHeight
         */
        public List<List<Integer>> LevelOrderDFS2(TreeNode root) {
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
                    tnStack.push(currTN.left);
                    heightStack.push(currHeight + 1);
                }
                if (currTN.right != null) {
                    tnStack.push(currTN.right);
                    heightStack.push(currHeight + 1);
                }
            }
            return result;

        }
    }

    public static void main(String[] args) {
        int[] ar = {0,1,2,3,4,5,6};
        TreeNode root = TreeNode.buildTreeBy7Numbers(ar);
    }

}
