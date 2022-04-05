package DynamicProgramming;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** 這是BST, 前情提要請看BC95 */
public class DP95UniqueBinarySearchTreeII {
    /**
     * 看答案
     * https://leetcode.wang/leetCode-95-Unique-Binary-Search-TreesII.html#%E8%A7%A3%E6%B3%95%E4%B8%89-%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92
     *
     */
    class solution {
        public List<TreeNode> generateTrees(int n) {
            ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
            dp[0] = new ArrayList<TreeNode>();
            if (n == 0) {
                return dp[0];
            }
            dp[0].add(null);


            for (int i = 1; i <= n; ++i) {
                dp[i] = new ArrayList<TreeNode>();
                for (int j = 1; j <= i; ++j) { //注意這裏纔是根節點的創造. 上面的循環i是爲了同時save dp用的數據
                    int leftLen = j - 1;
                    int rightLen = i - j;
                    for (TreeNode leftTree: dp[leftLen]) {
                        for (TreeNode rightTree: dp[rightLen]) {
                            TreeNode curr = new TreeNode(j);
                            curr.left = leftTree;
                            curr.right = cloneTree(rightTree, j);
                            dp[i].add(curr); // Memorize
                        }
                    }
                }
            }
            return dp[n];
        }

        private TreeNode cloneTree(TreeNode root, int offset) {
            if (root == null) {
                return null;
            }
            TreeNode newRoot = new TreeNode(root.val + offset);
            newRoot.left = cloneTree(root.left, offset);
            newRoot.right = cloneTree(root.right, offset);
            return newRoot;
        }
    }
}
