package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree257BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<TreeNode> al = new LinkedList<>();
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        al.add(root);

        while(!al.isEmpty()) {
            TreeNode tn = al.pop ();

        }

    }
    private static List<String> dfs(ArrayList<TreeNode> al, TreeNode root) {

    }

}
