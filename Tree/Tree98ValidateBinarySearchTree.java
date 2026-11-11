package Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Lintcode95
 * 方法一, Recursive + 最大最小值，validate每個子樹
 * 方法二, 上面的思路Iterative
 * 方法三, Recursive Inorder Traversal. 只需和prev value比较即可，因为inorder traversal on BST天然保证当前值比之后的都大
 * 方法四：Iterative by stack. 只要是inorder遍歷一遍, 後加入的數字應該比之前所有的大
 */
public class Tree98ValidateBinarySearchTree {
    /**
     * 这是2026看了答案后写的。易错点：
     * 1. 仔细看题目边界 -231 <= Node.val <= 231 - 1 因此我们不能用 Integer.MAX_VALUE等作为min 和max。
     *    可以用null或者是Long，下面Divide Conquer就是long的用法
     */
    class Method1 {
        public boolean isValidBST(TreeNode root) {
            //
            return isValidBST_AI(root, null, null);
        }
        /**
         * 同样是传入null作为min和max 这个更容易想
         */
        private boolean isValidBST_AI(TreeNode root, Integer min, Integer max) {
            if (root == null) {
                return true;
            }
            if (min != null && root.val <= min) {
                return false;
            }
            if (max != null && root.val >= max) {
                return false;
            }
            return isValidBST_AI(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        }

        /**
         * 2026自己写完Method4以后写的这个，甚至没分清Method1和3的区别
         */
        private boolean isValidBST_SELF(TreeNode root, Integer min, Integer max) {
            if ((min != null &&root.val <= min)
                    || (max != null &&root.val >= max)) {
                //注意这个还是得再check一遍，或者在每一边都check curr.left.val >= min
                return false;
            }
            TreeNode curr = root;
            if (curr.left != null) {
                if (curr.left.val >= curr.val || !isValidBST_SELF(curr.left, min, curr.val)) {
                    return false;
                }
            }

            if (curr.right != null) {
                if (curr.val >= curr.right.val || !isValidBST_SELF(curr.right, curr.val, max)) {
                    return false;
                }
            }
            return true;
        }
    }
    /**
     * Divide & Conquer 九章答案, 自己想到了min和max的用法, 也算不错了....
     */
    class Method1DivideConquer2017 {
        public boolean isValidBST(LintCode.Binary.Tree.TreeNode root) {
            if (root == null) {
                return true;
            }
            return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
        }
        private boolean helper(LintCode.Binary.Tree.TreeNode root, long max, long min) {
            if (root == null) {
                return true;
            }
            if (root.val >= max || root.val <= min) {
                return false;
            }
            return helper(root.left, Math.min(root.val, max), min)
                    && helper(root.right, max, Math.max(root.val, min));
        }
    }

    class Method2 {
        private Deque<TreeNode> stack = new LinkedList<>();
        private Deque<Integer> upperLimits = new LinkedList<>();
        private Deque<Integer> lowerLimits = new LinkedList<>();
        public boolean isValidBST(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return true;
            }
            updateStacks(root, null, null);
            while(!stack.isEmpty()) { //其實如果treat null代碼更簡潔 if (root == null) continue;
                TreeNode curr = stack.poll();
                Integer upper = upperLimits.poll();
                Integer lower = lowerLimits.poll();
                // System.out.println("val=" + curr.val + " upper=" + (upper == null? " " : upper) + " lower=" + (lower == null? " " : lower));
                if (upper != null && curr.val >= upper) {
                    return false;
                }
                if (lower != null && curr.val <= lower) {
                    return false;
                }
                if (curr.left != null) {
                    updateStacks(curr.left, curr.val, lower);
                }
                if (curr.right != null) {
                    updateStacks(curr.right, upper, curr.val);
                }
            }
            return true;
        }
        private void updateStacks(TreeNode root, Integer high, Integer low) {
            stack.add(root);
            lowerLimits.add(low);
            upperLimits.add(high);
            // System.out.println("Update: val=" + root.val + " high=" + (high == null? " " : high) + " low=" + (low == null? " " : low));
        }
    }

    /**
     * March 2022 完全看答案, 下面和標準答案一模一樣.
     */
    class Method3RecursiveInorderTraversal {
        private Integer prev;
        public boolean isValidBST2022(TreeNode root) {
            prev = null;
            return inorder(root);
        }
        private boolean inorder(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!inorder(root.left)) {
                return false;
            }
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            return inorder(root.right);
        }

        /**
         * Fail case: [2147483647] 因此min和max最好用null或者Long
         * Improvement: 既然要check min & max null check,不如直接把root的null也check了
         */
        public boolean isValidBST2026(TreeNode root) {
            TreeNode curr = root;
            Integer min = null; // 仔细看题目边界 -231 <= Node.val <= 231 - 1
            Integer max = null;
            return isValidBST(root, min, max);
        }

    }

    /**
     * 2026 写的不好自己没想到怎么遍历。
     * 1. 这个第一个while负责入栈，之后只是调整指针
     * 2. 没想到全局顺序，root要比右节点所有node都小；我一直只考虑的局部，也就是recursive的局部。还是Recursive更好想
     */
    class Method4InorderTraversalIterative {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            Integer prevVal = null;
            TreeNode trav = root;
            while (!stack.isEmpty() || trav != null) {
                while (trav != null) {
                    stack.push(trav);
                    trav = trav.left;
                }
                // Pop from stack and check the node
                TreeNode curr = stack.pop();
                // System.out.println("curr=" + curr.val + " prevVal=" + prevVal);
                if (prevVal != null && curr.val <= prevVal) { //prevNull永远应该<=当前
                    return false;
                }
                prevVal = curr.val;
                trav = curr.right;
            }
            return true;
        }
    }


    public void main(String[] args) {
        Integer[] array = {5,4,6,null,null,3,7}; // 右子树有比根结点小的元素
        boolean answer = false;
        Integer[] array2 = {2,2,2};
        boolean answer2 = false;
        Integer[] array3 = {5,1,4,null,null,3,6};
        Integer[] array4 = {-2147483648,null,2147483647};

    }

}
