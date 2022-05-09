package Tree;

public class Tree1120MaximumAverageSubtree {
    class TreeData { //注意這裏最好用public private
        protected int sum;
        protected int count;
        protected TreeData(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
    /**
     * March 2022
     * 1. 如果用global variable的話, helper class裏面根本不需要計算average
     * http://asjava.com/core-java/java-access-modifiers-public-protected-friendly-and-private/
     * 2. 注意葉子節點也要計算average!
     */
    class helperClassAndGlobalMax {
        double max = Integer.MIN_VALUE; //這裏要用最小值
        public double maximumAverageSubtree(TreeNode root) {
            if (root == null) {
                return -1;
            }
            TreeData left = new TreeData(0, 0), right = new TreeData(0, 0);
            getTreeData(root);
            return max;
        }
        public TreeData getTreeData(TreeNode root) {
            // 注意葉子節點也要計算average
            TreeData left = new TreeData(0, 0), right = new TreeData(0, 0);
            if (root.left != null) {
                left = getTreeData(root.left);
            }
            if (root.right != null) {
                right = getTreeData(root.right);
            }
            double average = (double) (left.sum + right.sum + root.val) / (left.count + right.count + 1);
            if (average > max) {
                max = average;
            }
            return new TreeData(left.sum + right.sum + root.val, left.count + right.count + 1);
        }
    }

    /**
     * June 2017. 和上面基本一樣, 沒上面簡潔. 但是property 性質都標明了! March 2022全忘了
     */
    class LintTree597June2017 {
        double maxAvg = Integer.MIN_VALUE; //不要忘了用double, 题目就说了比较大小, 没说取整数的事儿, 不能自己设条件
        TreeNode maxTree;
        public TreeNode findSubtree2(TreeNode root) {
            if (root == null) {
                return null;
            }
            helper(root);
            return maxTree;
        }
        private TreeData helper (TreeNode root) {//这个地方必须用public不能用private否则Result Type
            if (root.left == null && root.right == null) {
                if (root.val > maxAvg) {
                    maxAvg = root.val;
                    maxTree = root;
                }
                return new TreeData(root.val, 1);
            }
            int sum = root.val;
            int total = 1;
            if (root.left != null) {
                TreeData leftRT = helper(root.left);
                sum += leftRT.sum;
                total += leftRT.count;
            }
            if (root.right != null) {
                TreeData rightRT = helper(root.right);
                sum += rightRT.sum;
                total += rightRT.count;
            }
            if (maxAvg < (double) sum / total) { //这里也可以变为乘法! 就不需要double了
                maxAvg = (double) sum / total;
                maxTree = root;
            }
            return new TreeData(sum, total);
        }
    }
    public static void main(String[] args) {
        Integer[] tree = {0, 3, null,2,1};
        TreeNode tn = TreeNode.createTreeByArray(tree);
        TreeNode.printTreeByPreorderTraversal(tn);
    }
}
