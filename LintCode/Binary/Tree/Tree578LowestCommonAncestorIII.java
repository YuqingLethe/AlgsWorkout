package LintCode.Binary.Tree;

/**
 * Created by Administrator on 2017/6/17.
 */
class ResultTypeLCA {
    boolean findA;
    boolean findB;
    TreeNode lca;
    public ResultTypeLCA(boolean a, boolean b, TreeNode c) {
        this.findA = a;
        this.findB = b;
        this.lca = c;
    }
}
public class Tree578LowestCommonAncestorIII {
    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null || A == null || B == null) {
            return null;
        }
        ResultTypeLCA curr = helper(root, A, B);
        if (curr.findA && curr.findB) {
            return curr.lca;
        }
        return null;
    }
    private ResultTypeLCA helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultTypeLCA(false, false, null);
        }
        ResultTypeLCA leftRT = helper(root.left, A, B);
        ResultTypeLCA rightRT = helper(root.right, A, B);

        boolean aExist = leftRT.findA || rightRT.findA || root == A; //这个处理技巧比几个if清晰多了
        boolean bExist = leftRT.findB || rightRT.findB || root == B; //别忘了root == B这个条件
        if (root == A || root == B) {
            return new ResultTypeLCA(aExist, bExist, root);
        }
        if (leftRT.lca != null && rightRT.lca != null) {
            return new ResultTypeLCA(aExist, bExist, root);
        }
        if (leftRT.lca != null) {
            return leftRT;
        }
        if (rightRT.lca != null) {
            return rightRT;
        }
        return new ResultTypeLCA(aExist, bExist, null);

    }
}
