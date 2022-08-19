package Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal.
 *   Each group of children is separated by the null value (See examples)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 * Example 2:
 *
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000.
 */
public class Tree590NaryTreePostorderTraversal {
    /**
     * Aug2022 Crib solution
     * Let's start from the root and then at each iteration pop the current node out of the stack and push its child nodes.
     * In the implemented strategy we push nodes into stack following the order Top->Bottom and Left->Right.
     * Since DFS postorder traversal is Bottom->Top and Left->Right the output list should be reverted after the end of loop.
     */
    class solution{
        public List<Integer> postorder(Node root) {
            LinkedList<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node curr = stack.pollLast();
                res.addFirst(curr.val);
                for (Node child : curr.children) {
                    stack.addLast(child);
                }
            }
            return res;
        }
    }
}
