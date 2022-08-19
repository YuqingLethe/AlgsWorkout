package Tree;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 *    A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

 The path sum of a path is the sum of the node's values in the path.

 Given the root of a binary tree, return the maximum path sum of any non-empty path.

 Example 1:

 Input: root = [1,2,3]
 Output: 6
 Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

 Example 2:


 Input: root = [-10,9,20,null,null,15,7]
 Output: 42
 Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.


 Constraints:

 The number of nodes in the tree is in the range [1, 3 * 104].
 -1000 <= Node.val <= 1000

 注意要點就是：
 什麼時候應該算自己， 什麼時候不算
 */
public class Tree124BinaryTreeMaximumPathSum {

    /**
     * Self 2022 兩個test case沒想到提交了4次才過。
     *
     * 標準答案有個優化， 就是如果做子樹值小於0則返回0，這樣方便計算它自己
     */
    class Solution {
        int globalMax = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            helper(root);
            return globalMax;
        }

        public int helper(TreeNode root) {
            if (root  == null) {
                return 0;
            }

            int leftValue = helper(root.left);
            int rightValue = helper(root.right);

            int pathThroughSelf = leftValue + rightValue + root.val;
            int maxSide = Math.max(leftValue, rightValue);
            int currMax = root.val;
            currMax = Math.max(currMax, root.val + maxSide);
            int singlePathMax = currMax;

            currMax = Math.max(currMax, pathThroughSelf);
            globalMax = Math.max(globalMax, currMax);
            // 注意這裏不能是globalMax和currMax的最大值， 因爲path只能折一次，折過的不能參與後續計算！！
            return singlePathMax;
        }
    }

    class Standard_solution {
        int max_sum = Integer.MIN_VALUE;

        public int max_gain(TreeNode node) {
            if (node == null) return 0;

            // max sum on the left and right sub-trees of node
            int left_gain = Math.max(max_gain(node.left), 0);
            int right_gain = Math.max(max_gain(node.right), 0);

            // the price to start a new path where `node` is a highest node
            int price_newpath = node.val + left_gain + right_gain;

            // update max_sum if it's better to start a new path
            max_sum = Math.max(max_sum, price_newpath);

            // for recursion :
            // return the max gain if continue the same path
            return node.val + Math.max(left_gain, right_gain);
        }

        public int maxPathSum(TreeNode root) {
            max_gain(root);
            return max_sum;
        }
    }

    public static void main(String[] args) {
        Integer[] treeBuild1 = {5,4,8,11,null,13,4,7,2,null,null,null,1};
        int expectAnswer1 = 48;
        Integer[] treeBulid2 = {9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6};
        int expectAnswer2 = 16;
    }
}
