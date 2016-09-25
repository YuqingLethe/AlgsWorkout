package Tree;
import java.util.ArrayList;
import java.util.List;

public class Tree107BinaryTreeLevelOrderTraversal2 {
    //Runtime: 2ms Use: 20min 9/24/2016
    public List<List<Integer>> levelOrderBottomByBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) { return result; }

        ArrayList<TreeNode> al = new ArrayList<TreeNode>();
        al.add(root);

        while (!al.isEmpty()) {
            int size = al.size();
            List<Integer> tempList = new ArrayList<Integer>();

            while (--size >= 0) {
                TreeNode temp = al.remove(0);
                tempList.add(temp.val);
                if (temp.left != null) { al.add(temp.left); }
                if (temp.right != null) { al.add(temp.right); }
            }
            result.add(0, tempList);
        }
        return result;
    }

    //Runtime:1ms    Use: 15min  9/24/2016
    public List<List<Integer>> levelOrderBottomByRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) { return result; }
        result = recursiveBFS(root, result, 0);
        for (int i = 0; i < result.size()/2; i++) {
            List<Integer> tempHead = result.get(i);
            result.set(i, result.get(result.size() - i - 1));
            result.set (result.size() - 1 - i, tempHead);
        }
        return result;
    }
    // Debug: 30min
    private static List<List<Integer>> recursiveBFS (TreeNode root, List<List<Integer>> list, int height) {
        if (root == null) return list;
        if (list.size() > height) {
            list.get(height).add(root.val);
        } else {
            List<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            System.out.println(temp.get(0));
            list.add(temp);
        }

        list = recursiveBFS(root.left, list, height + 1);
        list = recursiveBFS(root.right, list, height + 1);
        return list;
    }
}
