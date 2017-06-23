package LintCode.Binary.Tree;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/21.
 */
public class Tree7BinaryTreeSerialization {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }

        ArrayList<TreeNode> tnlist = new ArrayList<>();
        tnlist.add(root);
        for (int i = 0; i < tnlist.size(); i++) {
            TreeNode tmp = tnlist.get(i);
            if (tmp == null) {
                continue;
            }
            //敢这么做是因为null的下面节点不再有String的表示, string的null只出现在叶子节点的下一level
            tnlist.add(tmp.left);
            tnlist.add(tmp.right);
        }

        while (tnlist.get(tnlist.size() - 1) == null) { //别忘了去掉多余的null
            tnlist.remove(tnlist.size() - 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{"); //勿忘
        sb.append(tnlist.remove(0).val); //勿忘, 第一个的特殊性

        while (!tnlist.isEmpty()) {
            TreeNode tmp = tnlist.remove(0);
            if (tmp == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(tmp.val + "");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data.equals("{}")) { //勿忘
            return null;
        }

        String[] sa = data.substring(1, data.length() - 1).split(",");
        ArrayList<TreeNode> qu = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(sa[0])); //必须要创建root, 后面要return
        qu.add(root);

        int quIdx = 0;
        boolean isLeftChild = true;
        for (int i = 1; i < sa.length; i++) {
            if (!sa[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(sa[i]));
                if (isLeftChild) {
                    qu.get(quIdx).left = node;
                } else {
                    qu.get(quIdx).right = node;
                }
                qu.add(node);
            }
            if (!isLeftChild) {
                quIdx++;
            }
            isLeftChild = !isLeftChild; //技巧!
        }
        return root;
    }
}
