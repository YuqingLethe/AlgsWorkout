package LintCode.Binary.Tree;

import java.util.ArrayList;

public class Tree474LowestCommonAncestorII {
    /**
     * 不要忘了A,B已经给了, 不要再找了. 2017/6/18.
     * http://www.jiuzhang.com/solution/lowest-common-ancestor-ii/
     * 1. A B各自build他們的parents
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A,
                                                 ParentTreeNode B) {
        // Write your code here
        ArrayList<ParentTreeNode> pathA = buildPath(A);
        ArrayList<ParentTreeNode> pathB = buildPath(B);

        //这里设root还是null, 与buildPath()里面while循环的终止条件有关. 如果while(X != null)则这里应该用null
        //为什么? 因为按照这题这么写, {1},1,1的case下,  pathA和pathB都是空的; 如果按照答案写, pathA和pathB都有一个值1
        ParentTreeNode lca = root;

        //从上倒下比较, 避免了从小到上的麻烦, 当parent不等的时候, A的parent需要和B所有ancestor比较, 太麻烦了.
        int a = pathA.size() - 1;
        int b = pathB.size() - 1;
        while (a >= 0 && b >= 0) {
            if (pathA.get(a) != pathB.get(b)) {
                break;
            }
            lca = pathA.get(a); //一直更新lca即是当前lowest的
            a--;
            b--;
        }
        return lca;
    }

    private ArrayList<ParentTreeNode> buildPath(ParentTreeNode X) {
        ArrayList<ParentTreeNode> al = new ArrayList<>();
        while (X.parent != null) {
            al.add(X);
            X = X.parent; //遍历的时候别忘了移动指针.....
        }
        return al;
    }
}
