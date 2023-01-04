package Tree;

public class Tree549BinaryTreeLongestConsecutiveSequenceII {
    /**
     * Jan 2023
     */
    class Recursive {
        private int longestPathCount;
        class ConsecNode{
            int val;
            int increaseCount;
            int decreaseCount;
            ConsecNode(int val) {
                this.val = val;
                this.increaseCount = 1;
                this.decreaseCount = 1;
            }
        }
        public int longestConsecutive(TreeNode root) {
            if (root == null) {
                return 0;
            }
            longestPathCount = 1;
            getConsecNode(root);
            return longestPathCount;
        }

        private ConsecNode getConsecNode(TreeNode root) {
            // get left / get right / see child-parnet=child
            ConsecNode curr = new ConsecNode(root.val);
            if (root.left != null) {
                ConsecNode left = getConsecNode(root.left);
                if (left.val + 1 == root.val) {
                    curr.increaseCount = left.increaseCount + 1;
                } else if (left.val - 1 == root.val) {
                    curr.decreaseCount = left.decreaseCount + 1;
                }
            }
            if (root.right != null) {
                ConsecNode right = getConsecNode(root.right);
                if (right.val + 1 == root.val) {
                    curr.increaseCount = Math.max(curr.increaseCount, right.increaseCount + 1);
                } else if (right.val - 1 == root.val) {
                    curr.decreaseCount = Math.max(curr.decreaseCount, right.decreaseCount + 1);
                }
            }
            longestPathCount = Math.max(longestPathCount, curr.increaseCount + curr.decreaseCount - 1);
            return curr;
        }
    }
}
