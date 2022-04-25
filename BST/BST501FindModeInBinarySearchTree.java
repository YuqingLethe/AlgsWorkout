package BST;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given the root of a binary search tree (BST) with duplicates,
 *   return all the mode(s) (i.e., the most frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 * Input: root = [1,null,2,2]
 * Output: [2]
 *
 * Example 2:
 * Input: root = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: root = [5,1,9,1,null,9,null]
 * Output:[1,9]
 */
public class BST501FindModeInBinarySearchTree {
    /**
     * 自己的思路: HashMap记录node和frequency, 最后挑最大frequency组成数组
     * 自己思路的缺点: 挑最大frequency还要遍历一遍
     *
     * 类似思路 https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98103/Java-AC-Solution
     *
     * 仔细读题答案可以有多个node, 所以必须用List<Integer>来记录, 最后转换成int[]
     */
    class ApplyAllTraverse_InOrderPreOrderPostOrder {
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // Pair<Node Value, Frequency>
        public int[] findMode(TreeNode root) {
            traverseAndUpdateList(root);

            // Get list of all nodes and the frequency
            ArrayList<Integer> list = new ArrayList<>();
            for(Integer key : map.keySet()) { //map的性质要记熟练
                if (map.get(key) == max) {
                    list.add(key);
                }
            }

            // The return type makes the code messy!!!!
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                result[i] = list.get(i);
            }
            return result;
        }

        private void traverseAndUpdateList(TreeNode root) {
            if (root == null) {
                return;
            }
            int tmpFrequency = map.getOrDefault(root.val, 0);
            map.put(root.val, ++tmpFrequency); // 注意这里一定要加自己不能用tmpFrequency + 1 因为后面还要判断!
            if (tmpFrequency > this.max) {
                this.max = tmpFrequency;
            }
            traverseAndUpdateList(root.left);
            traverseAndUpdateList(root.right);
        }
    }

    /**
     * 别人思路: 一个list一直保存最大frequency. 用max动态记录最大frequency, 一旦更新则list也要删掉更新.
     * 为什么我没想到/别人思路的assumption: 用inorder遍历方法确保相同value一次性遍历全部, 所以可用count记录, 而不需要hashmap记录frequency
     * https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98100/Java-4ms-Beats-100-Extra-O(1)-solution-No-Map
     *
     * 我的错误思路:
     * 三种情况 1) prev == null 2) prev.val == root.val  3) else 在第三种情况通过prev来登录进list
     *
     */
    class inOrderTraversal {
        int max = 0;
        int count = 0;
        TreeNode prev = null;
        ArrayList<Integer> list = new ArrayList<>(); // Pair<Node Value, Frequency>

        public int[] findMode(TreeNode root) {
            traverseAndUpdateList(root);

            // The return type makes the code messy!!!!
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                result[i] = list.get(i);
            }
            return result;
        }

        /**
         * April2022 错误思路日记:
         * 以下这个思路是错误的, 其实在prev.val == root.val的时候, 也许要进行count和max的比较. 比如最后遍历的数最大的时候.
         *  否则最后遍历的数字(如果frequency最高) 没有结束点!!!
         *  然而纠错以后我还是习惯性考虑完prev != null以后开始算count
         */
        private void traverseAndUpdateList_wrong(TreeNode root) {
            if (root == null) {
                return;
            }
            System.out.println("root: " + root.val);
            traverseAndUpdateList_wrong(root.left);

            if (prev == null) {                // Handle first node(leftmost node), initiate everything
                this.max = 1;
                this.count = 1;
                this.list.add(root.val);
            } else if (prev.val == root.val) { // continue with the same value
                this.count ++;
            } else {                           // Start the next number
                if (this.count > this.max) {
                    // new number frequency is the maximum. clear the list and add new.
                    list.clear();
                    list.add(prev.val);
                    this.max = this.count;
                } else if (this.count == this.max) {
                    // New number frequency is same with the maximum frequent number. Add extra to the list.
                    list.add(root.val);
                }
                this.count = 1;

            }
            this.prev = root;
            System.out.println("prev.val=" + prev.val + " count=" + count + " max=" + max);
            traverseAndUpdateList_wrong(root.right);
        }

        /**
         * April 2022 日记:
         * 这个是别人的答案, 先判断prev来更新count, 再比较count和max来更新list, 两个步骤没有纠缠在一起.
         * @param root
         */
        private void traverseAndUpdateList(TreeNode root) {
            if (root == null) {
                return;
            }
            // System.out.println("root: " + root.val);
            traverseAndUpdateList(root.left);

            if (prev == null) { //或者不要这个, 把count最初设置为1 则自动掉落下面那个if里面
                count = 1;
                max = 1;
            } else if (prev != null) {
                if (prev.val == root.val) {
                    count ++;
                } else {
                    count = 1;
                }
            }
            if (count > max) {
                list.clear();
                list.add(root.val);
                max = count;
            } else if (count == max) {
                list.add(root.val);
            }


            this.prev = root;
            // System.out.println("prev.val=" + prev.val + " count=" + count + " max=" + max);
            traverseAndUpdateList(root.right);
        }
    }
    public void main(String[] args) {
        Integer[] treeArr1 = {0};
        Integer[] treeArr2 = {1, null, 2, 2};
        Integer[] treeArr3 = {1, null, 2};

    }
}
