package Tree;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by Administrator on 2017/6/18.
 */
public class Tree236LowestCommonAncestorOfABinaryTree {
    /** Mar 2022 遠不如divide and conquer好, 但提供了一種思路*/
    class RecursiveAnswer {
        TreeNode result;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            this.find(root, p, q);
            return this.result;
        }
        private boolean find (TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return false;
            }
            // 左子樹有P或者Q
            int leftFound = find(root.left, p, q) ? 1 : 0;
            // 右子樹有P或者Q
            int rightFound = find(root.right, p, q) ? 1 : 0;
            // 當前是P或者Q
            int midFound = (root == p || root == q) ? 1 : 0;
            // System.out.println(root.val);
            // System.out.println("left=" + leftFound + " right=" + rightFound + " mid=" + midFound);

            // 左右各一個, 或者p q其中一個是另一個的parent
            if (leftFound + rightFound + midFound >= 2) {
                this.result = root;
            }

            //只有一邊有一個, 返回結果進入上一層循環
            return leftFound + rightFound + midFound > 0;
        }
    }

    /** Divide and conquer 6/17/2017 */
    class divideAndConquer {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root  == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }
            if (left != null) {
                return left;
            }
            if (right != null) {
                return right;
            }
            return null;
        }
    }

    /** March 2022 Mostly Copy answer  20min
     * 生成一個父節點的map, 找出p的所欲父親節點, 然後和q從低到高比較找出LCA */
    class IterativeWithParentHashMap {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            Deque<TreeNode> stack = new ArrayDeque<>(); //Use to traversal
            Map<TreeNode, TreeNode> parent = new HashMap<>(); // One is current ndoe, one is its parent

            stack.push(root);
            parent.put(root, null);

            // Build the parent node map
            while (!parent.containsKey(p) || !parent.containsKey(q)) {
                TreeNode curr = stack.pop();
                if (curr.left != null) {
                    parent.put(curr.left, curr);
                    stack.push(curr.left);
                }
                if (curr.right != null) {
                    parent.put(curr.right, curr);
                    stack.push(curr.right);
                }
            }

            //Get all ancestors of P
            Set<TreeNode> pAncesters = new HashSet<>();
            TreeNode curr = p;
            while (curr != null) { //這裏不能是parent.containsKey(curr), 因爲curr有可能是null
                pAncesters.add(curr);
                curr = parent.get(curr);
            }

            // Check parents of Q to find the LCA
            while (!pAncesters.contains(q)) {
                q = parent.get(q);

            }
            return q;
        }
    }

    /**
     * March 2022 全程炒答案 40min
     * Approach 3: Iterative without parent pointers
     * 1. 用stack<Pair<TreeNode, Status_As_Integer>>來做DFS Traversal, 同時標記這個子樹是否找到目標
     * 2. 如果左邊找到了一個, 則one_node_found = true, 同時先把當前 node記作LCA. 繼續找第右邊;
     * 3. 如果都沒找到, 則直接pop繼續其他子樹
     * 4. 如果右邊也找到了, 就把當前的node直接作爲LCA結果
     */
    static class IterativeByDFSWhileTrackingStatus {
        private static int BOTH_PENDING = 2;
        private static int LEFT_DONE = 1;
        private static int BOTH_DONE = 0;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new Pair<TreeNode, Integer>(root, BOTH_PENDING));
            boolean one_node_found = false;
            TreeNode LCA = null;
            TreeNode child_node = null;

            while (!stack.isEmpty()) {
                Pair<TreeNode, Integer> top = stack.peek();
                TreeNode parentNode = top.getKey();
                Integer parentStatus = top.getValue();

                if (parentStatus != this.BOTH_DONE) {
                    // 如果左右子樹都沒查, 則從左邊開始
                    if (parentStatus == this.BOTH_PENDING) {
                        if (parentNode == p || parentNode == q) {
                            if (one_node_found) {
                                return LCA;
                            } else { // If first p or q, save the current parent node as LCA.
                                one_node_found = true;
                                LCA = stack.peek().getKey();
                            }
                        }
                        child_node = parentNode.left;
                    } else {
                        child_node = parentNode.right;
                    }

                    // Update currenet parentNode status
                    stack.pop();
                    stack.push(new Pair<TreeNode, Integer>(parentNode, parentStatus - 1)); //這個巧妙, 直接延伸到下個status

                    // 加入子節點到循環
                    if (child_node != null) {
                        stack.push(new Pair<TreeNode, Integer>(child_node, this.BOTH_PENDING));
                    }
                } else {
                    // If the parent_state of the node is both done, the top node could be popped off the stack.
                    // Update the LCA node to be the next top node.
                    if (LCA == stack.pop().getKey() && one_node_found) {
                        LCA = stack.peek().getKey();
                    }
                    // If none p or q in current node and subtree, pop node directly, included in the step above.
                }
            }
            return null;
        }
    }

    /**
     * Created on 2017/6/17. 用特殊的Class來幫忙, 與IterativeByDFSWhileTrackingStatus異曲同工
     */
    public class RecursiveUsingNewDataType_LInt578 {
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
        public TreeNode RecursiveUsingNewDataType(TreeNode root, TreeNode A, TreeNode B) {
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
}
