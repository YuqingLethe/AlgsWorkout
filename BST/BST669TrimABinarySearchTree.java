package BST;

import Tree.TreeNode;

/**
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high].
 * Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant).
 * It can be proven that there is a unique answer.
 *
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 *
 * Example 1:
 * Input: root = [1,0,2], low = 1, high = 2
 * Output: [1,null,2]
 *
 * Example 2:
 * Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * Output: [3,2,null,1]
 *
 *
 * Constraints:
 * The number of nodes in the tree in the range [1, 104].
 * 0 <= Node.val <= 104
 * The value of each node in the tree is unique.
 * root is guaranteed to be a valid binary search tree.
 * 0 <= low <= high <= 104
 */
public class BST669TrimABinarySearchTree {
    /**
     * Crib the answer
     */
    class Recursion_April2022 {
        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) {
                return null;
            }
            if (root.val < low) {
                return trimBST(root.right, low, high);
            }
            if (root.val > high) {
                return trimBST(root.left, low, high);
            }
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
        // 注意这里之前的想法就不对, private TreeNode formBST(TreeNode leftSubtree, TreeNode rightSubtree) {
        // 应该传一个root而不是分别传子树. 分别传子树的话root还需要其他逻辑删掉.
        // 同样错误三天后在450. Delete Node in a BST 又犯了!
        private TreeNode rebuildBSTWithRootRemoved(TreeNode root) {
            return null;
        }
    }
    public void main (String[] args) {
        Integer[] array1 = {3,1,4,null,2};
        int low1 = 1, high1 = 2;
        return;
    }
}


