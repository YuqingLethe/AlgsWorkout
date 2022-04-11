package BackTracking;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 */
public class BC113PathSumIII {
    /** Backtracking April 2022 20min */
    class Solution {
        List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return result;
            }
            ArrayList<Integer> currPath = new ArrayList<>(); //注意這裏第一個節點從helper加入, 不要自己加
            helper(root, targetSum, currPath);
            return result;
        }

        private void helper(TreeNode root, int targetSum, ArrayList<Integer> existingPath) {
            if (root == null) {
                return;
            }
            // Write to the result
            if (root.val == targetSum && root.left == null && root.right == null) {
                ArrayList<Integer> currPath = deepCopy(existingPath);
                currPath.add(root.val);
                this.result.add(currPath);
                return;
            }

            if (root.left != null) {
                existingPath.add(root.val);
                helper(root.left, targetSum - root.val, existingPath);
                existingPath.remove(existingPath.size() - 1);
            }
            if (root.right != null) {
                existingPath.add(root.val);
                helper(root.right, targetSum - root.val, existingPath);
                existingPath.remove(existingPath.size() - 1);
            }
        }

        private ArrayList<Integer> deepCopy(ArrayList<Integer> currPath) {
            ArrayList<Integer> clonedList = new ArrayList<>();
            for (Integer i : currPath) {
                clonedList.add(i);
            }
            return clonedList;
        }
    }
}
