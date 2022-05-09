package Tree;

public class Tree235LowestCommonAncestorofaBinarySearchTree {
    class Recursive {
        /**
         * March 2022 Lint88
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val == p.val) {
                return root;
            }
            if (root.val == q.val) {
                return root;
            }
            if ((p.val < root.val && root.val < q.val) || (p.val > root.val && root.val > q.val)) {
                return root;
            }
            if (root.val >= p.val && root.val >= q.val) {
                if (root.left != null) {
                    return lowestCommonAncestor(root.left, p, q);
                }
            }
            if (root.val <= p.val && root.val <= q.val) {
                if (root.right != null) {
                    return lowestCommonAncestor(root.right, p, q);
                }

            }
            return null;
        }
    }

    class Iterative {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.val > q.val && curr.val > p.val) {
                    curr = curr.left;
                } else if (curr.val < p.val && curr.val < q.val) {

                    curr = curr.right;
                } else {
                    return curr;
                }
            }
            return null;
        }
    }
}
