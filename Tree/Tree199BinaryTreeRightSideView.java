package Tree;

import java.util.*;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * 想用iterative DFS 解决就必须要记录level, 否则
 */
public class Tree199BinaryTreeRightSideView {
    /**
     *  Aug 2022 Crib answer
     *  Sep 2022 similar 10:20-10:55
     */
    class DFS_Recursive {
        private List<Integer> res;
        public List<Integer> rightSideView(TreeNode root) {
            res = new ArrayList<>();
            helper(root, 0);
            return res;
        }

        //
        private void helper(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            if (res.size() == level) {
                res.add(root.val);
            }
            if (root.right != null) {
                helper(root.right, level + 1);
            }
            if (root.left != null) {
                helper(root.left, level + 1);
            }

        }
    }

    /**
     * TODO: Priority Medium, by end of Sep 2022.
     */
    class DFS_Iterative {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> nodeStack = new ArrayDeque<>();
            Deque<Integer> levelStack = new ArrayDeque<>();
            nodeStack.push(root); //记得检查root是否为null
            levelStack.push(0);
            while (!nodeStack.isEmpty()) {
                TreeNode currNode = nodeStack.pop();
                Integer currLevel = levelStack.pop();
                if (currLevel == res.size()) {
                    res.add(currNode.val);
                }
                if (currNode.left != null) {
                    nodeStack.push(currNode.left);
                    levelStack.push(currLevel + 1);
                }
                if (currNode.right != null) {
                    nodeStack.push(currNode.right);
                    levelStack.push(currLevel + 1);
                }
            }
            return res;
        }

    }

    class BFS_LevelSentinel {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Queue<TreeNode> q = new ArrayDeque<>();
            TreeNode levelEnd = new TreeNode(0);
            q.add(root);
            q.add(levelEnd);
            while (!q.isEmpty()) {
                TreeNode curr = q.poll();
                TreeNode next = q.peek();

                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
                if (next == levelEnd) {
                    res.add(curr.val);
                    q.poll();
                    q.add(levelEnd);
                }
            }
            return res;
        }
    }

    class BFS_LevelSizeMeasure {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int levelLength = q.size();
                for (int i = 0; i < levelLength; ++i) {
                    TreeNode curr = q.poll();
                    if (i == levelLength - 1) {
                        res.add(curr.val);
                    }
                    if (curr.left != null) {
                        q.offer(curr.left);
                    }
                    if (curr.right != null) {
                        q.offer(curr.right);
                    }
                }
            }
            return res;
        }

    }
}
