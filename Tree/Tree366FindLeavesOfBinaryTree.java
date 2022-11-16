package Tree;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tree366FindLeavesOfBinaryTree {
    /**
     * Nov 2022 Crib answer
     */
    class DFS_Sorting {
        private List<Pair<Integer, Integer>> pairs;
        public List<List<Integer>> findLeaves(TreeNode root) {
            pairs = new ArrayList<>();
            getHeight(root);
            Collections.sort(pairs, Comparator.comparing(p -> p.getKey()));

            int n = this.pairs.size();
            int height = 0;
            int i = 0;

            List<List<Integer>> result = new ArrayList<>();
            while (i < n) {
                List<Integer> currLevel = new ArrayList<>();
                while (i < n && this.pairs.get(i).getKey() == height) {
                    currLevel.add(this.pairs.get(i).getValue());
                    i++;
                }
                result.add(currLevel);
                height ++;
            }
            return result;
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            int currHeight = Math.max(leftHeight, rightHeight) + 1;
            this.pairs.add(new Pair<Integer, Integer>(currHeight, root.val));
            return currHeight;
        }
    }
    class DFS_NoSorting {
        private List<List<Integer>> result;
        public List<List<Integer>> findLeaves(TreeNode root) {
            result = new ArrayList<>();
            getHeight(root);
            return result;
        }
        private int getHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            int currHeight = Math.max(leftHeight, rightHeight) + 1;
            while (currHeight >= result.size()) { // 其实只有等于 if (this.result.size() == currHeight) {
                result.add(new ArrayList<>());
            }
            result.get(currHeight).add(root.val);
            return currHeight;
        }
    }
}
