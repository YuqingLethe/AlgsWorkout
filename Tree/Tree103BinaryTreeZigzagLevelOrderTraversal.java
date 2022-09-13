package Tree;

import java.util.*;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 *   (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 *
 * Input: root = []
 * Output: []
 */
public class Tree103BinaryTreeZigzagLevelOrderTraversal {
    /**
     * Sep 2022 9:25-9:55
     */
    class BFS_Deque {
        private List<List<Integer>> result;
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            this.result = new ArrayList<>();
            if (root == null) {
                return this.result;
            }

            Deque<TreeNode> deque = new ArrayDeque<>();
            Boolean fromLeftToRight = true;
            deque.add(root);

            while (!deque.isEmpty()) {
                if (fromLeftToRight) {
                    deque = getNextLevelDequeReadFromLeft(deque);
                } else {
                    deque = getNextLevelDequeReadFromRight(deque);
                }
                fromLeftToRight = !fromLeftToRight;
            }
            return result;
        }

        private Deque<TreeNode> getNextLevelDequeReadFromLeft(Deque<TreeNode> currLevel) {
            if (currLevel == null) {
                return null;
            }
            final int Size = currLevel.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0 ; i < Size; ++i) {
                TreeNode curr = currLevel.pollFirst();
                levelValues.add(curr.val);
                if (curr.left != null) {
                    currLevel.addLast(curr.left);
                }
                if (curr.right != null) {
                    currLevel.addLast(curr.right);
                }
            }
            result.add(levelValues);
            return currLevel;
        }
        private Deque<TreeNode> getNextLevelDequeReadFromRight(Deque<TreeNode> currLevel) {
            if (currLevel == null) {
                return null;
            }
            final int N = currLevel.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < N; ++i) {
                TreeNode curr = currLevel.pollLast();
                levelValues.add(curr.val);
                if (curr.right != null) {
                    currLevel.addFirst(curr.right);
                }
                if (curr.left != null) {
                    currLevel.addFirst(curr.left);
                }
            }
            result.add(levelValues);
            return currLevel;
        }
    }

    /**
     * TODO: Priority: low  标准答案避免了重复代码
     * 方式: poll和add作为不同步骤, 每一步区分FIFO还是FILO. 这样可以共用addToResult的代码
     */
    class BFS_AddFirstAddLast {
            public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                if (root == null) {
                    return new ArrayList<List<Integer>>();
                }

                List<List<Integer>> results = new ArrayList<List<Integer>>();

                // add the root element with a delimiter to kick off the BFS loop
                LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
                node_queue.addLast(root);
                node_queue.addLast(null);

                LinkedList<Integer> level_list = new LinkedList<Integer>();
                boolean is_order_left = true;

                while (node_queue.size() > 0) {
                    TreeNode curr_node = node_queue.pollFirst();
                    if (curr_node != null) {
                        if (is_order_left)
                            level_list.addLast(curr_node.val);
                        else
                            level_list.addFirst(curr_node.val);

                        if (curr_node.left != null)
                            node_queue.addLast(curr_node.left);
                        if (curr_node.right != null)
                            node_queue.addLast(curr_node.right);

                    } else {
                        // we finish the scan of one level
                        results.add(level_list);
                        level_list = new LinkedList<Integer>();
                        // prepare for the next level
                        if (node_queue.size() > 0)
                            node_queue.addLast(null);
                        is_order_left = !is_order_left;
                    }
                }
                return results;
            }

    }

    /**
     * Crib answer from LC solution
     * Sep 2022 10:15-10:37
     */
    class DFS {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            addToDeque(root, 0, result);
            return result;
        }

        protected void addToDeque(TreeNode root, int level, List<List<Integer>> result) {
            if (level + 1 > result.size()) {
                LinkedList<Integer> newLevel = new LinkedList<>();
                result.add(newLevel);
            }
            if (level % 2 == 0) {
                result.get(level).add(root.val);
            } else {
                result.get(level).add(0, root.val);
            }

            if (root.left != null) {
                addToDeque(root.left, level + 1, result);
            }
            if (root.right != null) {
                addToDeque(root.right, level + 1, result);
            }
        }
    }

}
