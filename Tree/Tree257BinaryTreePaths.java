package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree257BinaryTreePaths {
    /**
     * Divide and conquer 6/18/2017
     * http://www.jiuzhang.com/solutions/binary-tree-paths/
     */
    public List<String> binaryTreePathsByDC(TreeNode root) {
        List<String> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        List<String> leftList = binaryTreePathsByDC(root.left);
        List<String> rightList = binaryTreePathsByDC(root.right);
        for (String s : leftList) {
            String tmp = root.val + "->" + s;
            results.add(tmp);
        }
        for (String s : rightList) {
            results.add(root.val + "->" + s); //比上面的处理方法简洁
        }
        // 忘记了root is a leaf的情况. 所有results.add的情况全部是leftList和rightList不为空的情况之下的, 因此要考虑到叶子节点的add
        if (results.size() == 0) {
            results.add("" + root.val);
        }
        return results;
    }


    /**
     * Runtime: 7ms   11/4/2016
     * Use a flag to node if parent node has two children
     */
    public List<String> binaryTreePathsByIteration(TreeNode root) {
        LinkedList<TreeNode> al = new LinkedList<>();
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        al.add(root);

        TreeNode both = new TreeNode(-1); //The flag if has both children
        while(!al.isEmpty()) {
            TreeNode tn = al.getLast ();
            if (tn.left == null && tn.right == null) {
                //Add in the result string array
                String str = "";
                for (TreeNode x : al) {
                    if (x != both) {
                        str = str + x.val + "->";
                    }
                }
                result.add(str.substring(0,str.length()-2));
                al.removeLast();
                //If the parent node doesn't have the other child, pop the parent nodes
                while (al.size() > 0 && al.getLast() != both) {//这个both flag很好, 因为只有两个子节点的时候, 这里需要执行另一个子节点
                    al.removeLast();
                }
                if (al.size() == 0) return result;
                al.removeLast();//pop the flag TreeNode: both
                al.add(al.getLast().right);//add the other child
            } else {
                if (tn.left != null && tn.right != null) {
                    al.add(both);
                }
                if (tn.left != null) {
                    al.add(tn.left);
                    continue;
                }
                if (tn.right != null) {
                    al.add(tn.right);
                    continue;
                }
            }
        }
        return result;
    }




    static class Recursive {
        /**
         * stringBuildingWay1 Feb 2022
         */
        final List<String> result = new ArrayList<>();
        public List<String> stringBuildingWay1(TreeNode root) {
            if (root == null) {
                return result;
            }
            String path = "";
            getPath(root, path);
            return result;
        }

        private void getPath(TreeNode root, String path) {
            if (root != null) {
                path += root.val + "";
            } else {
                return;
            }

            if (root.left == null && root.right == null) {
                result.add(path);
            } else {
                path += "->";
                getPath(root.left, path);
                getPath(root.right, path);
            }
        }

        /**
         * StringBuilderWay2:  11/4/2016
         * Use recursive, and optimized String building way
         * https://discuss.leetcode.com/topic/21474/accepted-java-simple-solution-in-8-lines
         */
        public static List<String> stringBuildingWay2(TreeNode root) {
            List<String> answer = new ArrayList<>();
            if (root != null) searchBT(root, "", answer);
            return answer;
        }
        private static void searchBT(TreeNode root, String path, List<String> answer) {
            if (root.left == null && root.right == null) answer.add(path + root.val);
            if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
            if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
        }

        /**
         * Runtime: 2017
         * Optimize recursive version by using only itself
         * https://discuss.leetcode.com/topic/23047/clean-java-solution-accepted-without-any-helper-recursive-function/2
         */
        public List<String> oneFunctionOnly(TreeNode root) {
            List<String> answer = new LinkedList<>();
            if (root == null) return answer;
            if (root.left == null && root.right == null) {
                answer.add(root.val + "");
                return answer;
            }
            for (String path : oneFunctionOnly(root.left)) {
                answer.add(root.val + "->" + path);
            }
            for (String path : oneFunctionOnly(root.right)) {
                answer.add(root.val + "->" + path);
            }
            return answer;
        }
    }


    //https://discuss.leetcode.com/topic/23114/java-solution-using-stringbuilder-instead-of-string-manipulation
    public static void main(String[] args) {
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(1);
        System.out.println(tn1 == tn1);
    }

}
