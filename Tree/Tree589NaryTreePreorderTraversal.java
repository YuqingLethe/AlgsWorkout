package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal.
 *   Each group of children is separated by the null value (See examples)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,3,5,6,2,4]
 * Example 2:
 *
 *
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000.
 */
public class Tree589NaryTreePreorderTraversal {
    /**
     * Aug 2022 Self
     */
    class Recursive {
        public List<Integer> preorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            helper(root, res);
            return res;
        }
        private void helper(Node root, List<Integer> res) {
            res.add(root.val);
            for (Node child : root.children) {
                helper(child, res);
            }
        }
    }

    /**
     * Crib answer Aug 2022
     * 没想明白是最后加入的先提出, 所以用queue
     */
    class Iterative {
        public List<Integer> preorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Stack<Node> q = new Stack<>();
            q.push(root);
            while (!q.isEmpty()) {
                Node curr = q.pop();
                res.add(curr.val);
                int childNum = curr.children.size();
                for (int i = childNum - 1; i >= 0; --i) {
                    q.push(curr.children.get(i));
                }
            }
            return res;
        }

    }
}
