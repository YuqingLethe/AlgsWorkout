package LintCode.Binary.Graph;

import java.util.*;

/**
 * Created by Administrator on 2017/6/30.
 */
public class LintGraph137CloneGraph {
    /**
     * 6/30/2017 自己本想把copy node和traverse放一起, 后来发现, 不build arraylist很难traverse, 需要hashset时刻防重,
     * 太麻烦了, 不如做一个arraylist用来后面traverse方便的很
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
        //copy nodes 这一步其实就是为了build arraylist, 以后就不需要防重了, hash太麻烦了.
        copyNodes(nodes, node);

        //Build the link between old and new nodes 一定要分开node和neighbors的复制, 不然太乱了
        HashMap<UndirectedGraphNode, UndirectedGraphNode> link = new HashMap<>();
        for (UndirectedGraphNode crtNode : nodes) {
            UndirectedGraphNode newNode = new UndirectedGraphNode(crtNode.label);
            link.put(crtNode, newNode);
        }

        //Copy neighbors
        for (UndirectedGraphNode crtNode: nodes) {
            UndirectedGraphNode crtNewNode = link.get(crtNode);
            for (UndirectedGraphNode crtNeighbors : crtNode.neighbors) {
                UndirectedGraphNode nextNode = link.get(crtNeighbors);
                crtNewNode.neighbors.add(nextNode);
            }
        }
        return link.get(node);
    }
    private void copyNodes(ArrayList<UndirectedGraphNode> nodes, UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        Set<UndirectedGraphNode> hash = new HashSet<>();
        q.offer(node);
        hash.add(node);
        nodes.add(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode crt = q.poll();
            for (UndirectedGraphNode n : crt.neighbors) {
                if (!hash.contains(n)) {
                    q.offer(n);
                    hash.add(n);
                    nodes.add(n);
                }
            }
        }
    }
}
