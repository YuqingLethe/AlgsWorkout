package LintCode.Binary.Graph;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30.
 */
public class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
