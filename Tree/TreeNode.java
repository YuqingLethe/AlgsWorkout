package Tree;

/**
 * Created by XiaoMi on 2016/9/5.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (int x) { val = x;}

    public static TreeNode buildTreeByArray( int[] treeArray) {
        TreeNode[] tnArray = new TreeNode[treeArray.length];
        for (int i = 0; i < treeArray.length; i++) {
            System.out.println(treeArray[i]);
            tnArray[i].val = treeArray[i];
        }
        tnArray[0].left = tnArray[1];
        tnArray[0].right = tnArray[2];
        tnArray[1].left = tnArray[3];
        tnArray[1].right = tnArray[4];
        tnArray[2].left = tnArray[5];
        tnArray[2].right = tnArray[6];

        return tnArray[0];
    }
}

