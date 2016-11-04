package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree257BinaryTreePaths {
    /**
     * Runtime: 7ms
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
                while (al.size() > 0 && al.getLast() != both) {
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
    public static void main(String[] args) {
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(1);
        System.out.println(tn1 == tn1);
    }

}
