package BST;

import Tree.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 *
 * 1. Search for a node to remove.
 * 2. If the node is found, delete the node.
 *
 *
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 *
 * Example 3:
 * Input: root = [], key = 0
 * Output: []
 */
public class BST450DeleteNodeInABST {
    /**
     * April_2022 傻瓜式remove 节点, 不是最优解!
     * Self solution 40min
     */
    class Iterative_MoveNode {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) { //这个必须有
                return null;
            }
            if (root.val == key) {
                return buildBSTWithoutRoot(root);
            }

            TreeNode curr = root;
            TreeNode prev = root;

            while (curr != null) {
                // curr can't be key as it is already checked.
                if (curr.val > key) {
                    if (curr.left == null) {
                        return root;
                    }
                    if (curr.left.val == key) {
                        curr.left = buildBSTWithoutRoot(curr.left);
                        return root;
                    }
                    curr = curr.left;
                } else {
                    if (curr.right == null) {
                        return root;
                    }
                    if (curr.right.val == key) {

                        curr.right = buildBSTWithoutRoot(curr.right);
                        return root;
                    }
                    curr = curr.right;
                }
            }
            // Not found
            return root;
        }
        private TreeNode buildBSTWithoutRoot (TreeNode root) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            // Move right child tree up.
            TreeNode leftMost = root.right;
            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }
            leftMost.left = root.left;
            return root.right;
        }
    }

    /**
     * April 2022 Crib Answer
     * 这个算法很巧妙, 但是只适用于exchange value允许的情况.
     * 不要有固定思维不允许update value. 因为BST主要是用于搜索, TreeNode实际意义就是val, 和graph不一样
     */
    class BalancedBST_ExchangeValue_cribAnswer {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }
            if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else {
                // 等写完下面的才发现, 我们就是用这个leave node来做delete的事情. 中间的只是换val, treenode节点地址不变!
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.right != null) { //一定要先测试right吗? 不一定, 都一样
                    root.val = successor(root);
                    root.right = deleteNode(root.right, root.val);
                } else if (root.left != null) {
                    root.val = predecessor(root);
                    root.left = deleteNode(root.left, root.val);
                }
            }
            return root;
        }
        private int successor(TreeNode root) {
            TreeNode curr = root.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr.val;
        }
        private int predecessor (TreeNode root) {
            root = root.left;
            while (root.right != null) {
                root = root.right;
            }
            return root.val;
        }

    }
}
