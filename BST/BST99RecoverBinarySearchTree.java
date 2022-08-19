package BST;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BST99RecoverBinarySearchTree {
    /** April 2022 */
    class Almost_Sorted_Array_April2022 {
        public void recoverTree(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            inorderToList(root, list);

            Integer first = -1;
            Integer second = -1;
            boolean foundFirst = false;

            for (int i = 0; i < list.size() - 1; ++i) {
                if (list.get(i).val > list.get(i + 1).val ) {
                    second = list.get(i + 1).val;
                    if (!foundFirst) {
                        first = list.get(i).val;
                        foundFirst = true;
                    } else {
                        break;
                    }
                }
            }
            fixTree(root, 2, first, second);
        }

        private void inorderToList(TreeNode root, List<TreeNode> list) {
            if (root == null) {
                return;
            }
            inorderToList(root.left, list);
            list.add(root);
            inorderToList(root.right, list);
        }

        private void fixTree(TreeNode root, int count, Integer x, Integer y) {
            //注意他们是有parent的, 只调换这两个node是不行的
            if (root == null) {
                return;
            }
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                count --;
                if (count == 0) { return;}
            }
            fixTree(root.left, count, x, y);
            fixTree(root.right, count, x, y);
        }
    }

    /**
     * April 2022 Crib the answer
     * 不能用validation rule因为这个BST不完善 衰. 只能形成个inorder array
     * 这个算法的找两个abnormal的思路是一样的
     * Aug 2022再看这道题还想用validation rule, 呵呵......
     */
    class Iterative_Inorder_Traversal_April2022 {
        public void recoverTree(TreeNode root) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode a = null, b = null, prev = null;
            TreeNode curr = root; //也可以全程直接用Root
            while (!stack.isEmpty() || curr != null) {
                while (curr != null) {
                    stack.addLast(curr);
                    curr = curr.left;
                }
                curr = stack.removeLast();
                if (prev != null && prev.val > curr.val) {
                    b = curr;
                    if (a == null) {
                        a = prev;
                    } else {
                        break;
                    }
                }
                prev = curr;
                curr = curr.right;
            }
            swapValues(a, b);
        }

        private void swapValues(TreeNode a, TreeNode b) {
            int tmp = a.val;
            a.val = b.val;
            b.val = tmp;
        }

    }
}
