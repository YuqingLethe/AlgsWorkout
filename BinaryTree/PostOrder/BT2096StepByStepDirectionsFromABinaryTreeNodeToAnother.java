package BinaryTree.PostOrder;

import Tree.TreeNode;

/**
 * 一些写Tree的技巧
 * 1. 遍历的时候很麻烦, 能用value就别传TreeNode
 * 2. 一些问题可以简化写法, 就直接问考官, 最好得到更简单的要求. 比如这个题就是, 所有的treenode value都不同, 保证唯一性.
 * 3. PostOrder就不要用Interative写, 除非直接打印可以用deque.
 *
 finalize the requiremrents
 1. is it possible startValue and destValue not existed
 2. can StartValue and destValue be same value
 3. One node tree
 4. 忘了问: 整个tree有没有重复还是unique
 */
public class BT2096StepByStepDirectionsFromABinaryTreeNodeToAnother {
    /**
     finalize the requiremrents
     1. is it possible startValue and destValue not existed
     2. can StartValue and destValue be same value
     3. One node tree
     4. 忘了问: 整个tree有没有重复还是unique

     Nov 2022 Crib Answer https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/solutions/1612105/3-steps/
     */
    class Solution {
        public String getDirections(TreeNode root, int startValue, int destValue) {
            StringBuilder s = new StringBuilder();
            StringBuilder d = new StringBuilder();
            find(root, s, startValue);
            find(root, d, destValue);
            int i = 0;
            int max_i = Math.min(d.length(), s.length());
            //从后往前找不同
            while (i < max_i && s.charAt(s.length() - 1 - i) == d.charAt(d.length() - i - 1)) {
                ++i;
            }
            StringBuilder result = new StringBuilder();
            // 下面一个效果 result.append("U".repeat(s.length() - i));
            for (int x = 0; x < s.length() - i; ++x) {
                result.append("U");
            }

            result.append(d.reverse().toString().substring(i));
            return result.toString();
        }

        private boolean find(TreeNode root, StringBuilder sb, int target) {
            if (root.val == target) {
                return true;
            }
            if (root.left != null && find(root.left, sb, target)) {
                sb.append("L");
            } else if (root.right != null && find(root.right, sb, target)) {
                sb.append("R");
            }
            return sb.length() > 0;
        }
    }
}
