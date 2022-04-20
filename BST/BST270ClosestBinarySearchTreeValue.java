package BST;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given the root of a binary search tree and a target value,
 *   return the value in the BST that is closest to the target.
 *
 * Example 1:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 *
 * Example 2:
 * Input: root = [1], target = 4.428571
 * Output: 1
 */
public class BST270ClosestBinarySearchTreeValue {
    /**
     * Approach 3: Binary Search, O(H) time
     * Intuition
     * Approach 2 works fine when index k of closest element is much smaller than the tree height H.
     * Let's now consider another limit and optimise Approach 1 in the case of relatively large k, comparable with N.
     * Then it makes sense to use a binary search: go left if target is smaller than current root value,
     *        and go right otherwise. Choose the closest to target value at each step.
     *
     * April 2022 自己总结:
     * 这个算法意识到想找到closest必须比较父亲, 当前, 和孩子节点三个.
     * 但是父亲和当前, 当前与孩子是个循环, 所以每个循环里面只需要比较俩, 就是当前和孩子. 但要知道和父亲的绝对值.
     * 因为题目返回不是specific treenode而是int, 那么我们只要存储最小的closest的值即可
     * (当然如果是treenode则存储一个Treenode即可)
     */
    class BinarySearch_CribAnswer {
        public int closestValue(TreeNode root, double target) {
            int curr;
            int closest = root.val;
            // 存储父结点和target, curr结点和target的最小值, 然后比较.
            while (root != null) {
                curr = root.val;
                closest = Math.abs(curr - target) < Math.abs(closest - target) ? curr : closest;
                root = target < root.val ? root.left : root.right;
            }
            return closest;
        }
    }

    /** April 2022 Crib the solution */
    class Linear_Comparator {
        public int closestValue(TreeNode root, double target) {
            List<Integer> list = new ArrayList<>();
            inorder(root, list);
            return Collections.min(list, new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
                }
            });

        }
        private void inorder(TreeNode root, List<Integer> nums) {
            if (root == null) {
                return;
            }
            inorder(root.left, nums);
            nums.add(root.val);
            inorder(root.right, nums);
        }
    }
}
