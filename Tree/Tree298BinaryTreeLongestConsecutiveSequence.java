package Tree;
public class Tree298BinaryTreeLongestConsecutiveSequence {
    /**
     * Created by Administrator on 2017/6/16.
     */
    public static class Tree595BinaryTreeLongestConsecutiveSequence {
        /**
         * Traverse and Divide and conquer 2017/6/16.
         * 答案三种方法很全，倒着找,正着找, ResultType找:　http://www.jiuzhang.com/solutions/binary-tree-longest-consecutive-sequence/
         */
        int maxLen; //Java里面的全局变量, boolean自动为false, char为空, String 为null, 其他一律为0
        //可以加一个TreeNode全局变量, 返回maxLen对应的起始节点, 帮助debug
        public int longestConsecutiveTraverseDC(TreeNode root) {
            if (root == null) {
                return 0;
            }
            helper(root);
            return maxLen;
        }
        //return length of current consecutive sequence
        private int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftLen = helper(root.left);
            int rightLen = helper(root.right);
            int currLen = 1;
            if (leftLen != 0 && root.left.val - 1 == root.val) {
                currLen = Math.max(currLen, leftLen + 1);
            }
            if (rightLen != 0 && root.right.val - 1 == root.val) {
                currLen = Math.max(currLen, rightLen + 1);
            }
            if (currLen > maxLen) {
                maxLen = currLen;
            }
            return currLen;
        }
    }

    class BottomUpDFS2022April {
        int result = 0;
        // DFS的時候大部分不能用原function來循環, 因爲return type不同
        public int longestConsecutive(TreeNode root) {
            helper(root);
            return result;
        }
        private int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                if (result < 1) { //這個別忘記!
                    result = 1;
                }
                return 1;
            }
            int leftLongest = helper(root.left) + 1;
            int rightLongest = helper(root.right) + 1;

            if (root.left != null && root.val + 1 != root.left.val) { //這個不能忘記, 中途斷掉後要重新計數, 比如1-2-0-3-4-5
                leftLongest = 1;
            }
            if (root.right != null && root.val + 1 != root.right.val) {
                rightLongest = 1;
            }
            int currResult =  Math.max(leftLongest, rightLongest);

            if (currResult > result) {
                result = currResult;
            }
            return currResult;
        }
    }

    /**
     * 照抄答案, 需要重做
     */
    class TopBottomBFSCopy2022 {
        public int longestConsecutive(TreeNode root) {
            return helper(root, null, 0);
        }

        private int helper(TreeNode root, TreeNode parent, int len) {
            if (root == null) {
                return len;
            }
            if (parent != null &&root.val - 1 == parent.val) {
                len = len + 1;
            } else {
                len = 1;
            }
            int leftLen = helper(root.left, root, len);
            int rightLen = helper(root.right, root, len);
            return Math.max(len, Math.max(leftLen, rightLen));
        }

    }

    public void main(String[] args) {
        Integer[] array = {1,null,3,2,4,null,null,null,5};
        Integer[] array2 = {1,2,2,3,null,null,3,4,null,null,4};
    }
}
