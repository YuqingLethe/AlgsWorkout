package BackTracking;

import java.util.*;

/**
 * Created by yuqing on 7/18/22.
 */
public class BC2065MaximumPathQualityOfAGraph {
    /**
     * Crib Answer July 2022 這個算法思想就是用recursive實現backtracking。 我只關心當前路徑的兩方面：
     * 1. 是否被訪問過 （要不要加進totalQuality）
     * 2. 後面的路徑最大化
     */
    static class Solution {
        private class Node {
            int id;
            int value;
            List<Edge> edges;
            public Node(int id, int value) {
                this.id = id;
                this.value = value;
                this.edges = new ArrayList<>();
            }
        }
        private class Edge {
            int id1;
            int id2;
            int cost;
            public Edge(int id1, int id2, int cost) {
                this.id1 = id1;
                this.id2 = id2;
                this.cost = cost;
            }
        }
        public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < values.length; ++i) {
                nodes.add(new Node(i, values[i]));
            }
            for (int[] edge : edges) {
                Edge e = new Edge(edge[0], edge[1], edge[2]);
                nodes.get(edge[0]).edges.add(e);
                nodes.get(edge[1]).edges.add(e);
            }
            for (Node n : nodes) {
                Collections.sort(n.edges, (e1, e2) -> (e1.cost - e2.cost));
            }
//            printNodes(nodes);

            Set<Integer> qualityAdded = new HashSet<>();
            qualityAdded.add(0);
            return  visited(nodes, 0, qualityAdded, maxTime, nodes.get(0).value, 0);

        }
        private int visited(List<Node> nodes, int currNodeId, Set<Integer> qualityAdded, int maxTime, int qualityTillNow, int time) {
            if (time > maxTime) {
                return Integer.MIN_VALUE;
            }
            int maxQuality = Integer.MIN_VALUE;
            if (currNodeId == 0 && time <= maxTime) {
                maxQuality = Math.max(maxQuality, qualityTillNow);
            }
            printNode(nodes.get(currNodeId));
//            printEdges(nodes.get(currNodeId).edges);
            for (Edge edge : nodes.get(currNodeId).edges) {
                if (time + edge.cost > maxTime) {
                    System.out.println("Break");
                    break;
                }
                int neighborId = edge.id1 != currNodeId ? edge.id1 : edge.id2;
                boolean hasAdded = qualityAdded.contains(neighborId);
                qualityAdded.add(neighborId);
                int newQuality = hasAdded ? qualityTillNow : qualityTillNow + nodes.get(neighborId).value;

                int currTime = time + edge.cost;
                System.out.println("neighborId=" + neighborId + " currTime=" + currTime);
                maxQuality = Math.max(maxQuality, visited(nodes, neighborId, qualityAdded, maxTime, newQuality, currTime));
                System.out.println("neighborId=" + neighborId + " maxQuality=" + maxQuality);

                if (!hasAdded) {
                    qualityAdded.remove(neighborId);
                }
            }
            return maxQuality;
        }

        private void printNode(Node n) {
            System.out.println("id=" + n.id + " value=" + n.value);
            printEdges(n.edges);
        }

        private void printNodes(List<Node> list) {
            for (Node n : list) {
                printNode(n);
            }
        }
        private void printEdges(List<Edge> list) {
            for (Edge e : list) {
                System.out.println("# " + e.id1 + " " + e.id2 + " " + e.cost);
            }
        }
    }



    public static void main(String[] args) {
        int[] values = {0, 32, 10, 43};
        int [][] edges = {{0,1,10}, {1,2,15}, {0,3,10}};
        int maxTime = 49;

        Solution s = new Solution();
        System.out.println(s.maximalPathQuality(values, edges, maxTime));
    }
}
