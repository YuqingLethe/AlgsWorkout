package LintCode.Binary.Graph;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30.
 */
public class DirectedGraphNode {
     int label;
     ArrayList<DirectedGraphNode> neighbors;
     DirectedGraphNode(int x) {
         label = x;
         neighbors = new ArrayList<DirectedGraphNode>();
     }
}
