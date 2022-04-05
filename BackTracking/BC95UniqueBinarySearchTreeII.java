package BackTracking;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BC95UniqueBinarySearchTreeII {
    /**
     * 抄答案 April 2022
     */
    class Recursive {
        public List<TreeNode> generateTrees(int n) {
            List<TreeNode> dp = new ArrayList<>();
            if (n == 0) {
                return dp;
            }
            return generateTrees(1, n);

        }

        private ArrayList<TreeNode> generateTrees(int start, int end) {
            ArrayList<TreeNode> all_trees = new ArrayList<TreeNode>();
            if (start > end) {
                all_trees.add(null);
                return all_trees;
            }

            for (int i = start; i <= end; ++i) {
                ArrayList<TreeNode> left_trees = generateTrees(start, i - 1);
                ArrayList<TreeNode> right_trees = generateTrees(i + 1, end);

                for (TreeNode left: left_trees) {
                    for (TreeNode right : right_trees) {
                        TreeNode curr = new TreeNode(i);
                        curr.left = left;
                        curr.right = right;
                        all_trees.add(curr);
                    }
                }
            }
            return all_trees;
        }
    }
}
