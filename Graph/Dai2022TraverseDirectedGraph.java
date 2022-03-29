package Graph;


import java.util.*;

public class Dai2022TraverseDirectedGraph {

    public static class DirectedGraphNode {
        int val;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            val = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /** March 2022 20min */
    public static class IterativeSolution {
        public static ArrayList<Integer> traverseDirectedGraph (DirectedGraphNode root, boolean BFS) {
            if (root == null) {
                return null;
            }
            ArrayList<Integer> result = new ArrayList<>();

            Deque<DirectedGraphNode> deque = new ArrayDeque<DirectedGraphNode>();
            HashSet<DirectedGraphNode> set = new HashSet<>();


            deque.push(root);
            set.add(root);

            while (!deque.isEmpty()) {
                DirectedGraphNode curr = BFS ? deque.pollFirst() : deque.pollLast();
                result.add(curr.val);

                if (!curr.neighbors.isEmpty()) {
                    for (DirectedGraphNode neighbor : curr.neighbors) {
                        if (!set.contains(neighbor)) {
                            deque.push(neighbor);
                            set.add(neighbor);
                        }
                    }
                }
            }
            return result;
        }
    }

    /** March 2022 20min */
    public static class RecursiveSolution {
        static ArrayList<Integer> result = new ArrayList<>();
        static Set<DirectedGraphNode> set = new HashSet<>();
        public ArrayList<Integer> traverseDirectedGraphRecursively (DirectedGraphNode root) {
            if (root == null || set.contains(root)) {
                return null;
            }

            this.set.add(root);
            this.result.add(root.val);

            for (DirectedGraphNode neighbor : root.neighbors) {
                traverseDirectedGraphRecursively(neighbor);
            }
            return this.result;
        }
    }


    /**
     *             -> 4
     *  2 -> 1 -> 3 @ (轉回自己)
     *  | <-      |
     *  -> 0    <-
     * @param args
     */
    public static void main (String[] args) {
        DirectedGraphNode node2 = new DirectedGraphNode(2);
        DirectedGraphNode node1 = new DirectedGraphNode(1);
        DirectedGraphNode node3 = new DirectedGraphNode(3);
        DirectedGraphNode node0 = new DirectedGraphNode(0);
        DirectedGraphNode node4 = new DirectedGraphNode(4);

        node2.neighbors.add(node0);
        node2.neighbors.add(node1);
        node0.neighbors.add(node2);
        node1.neighbors.add(node3);
        node3.neighbors.add(node3);
        node3.neighbors.add(node4);
        node3.neighbors.add(node0);


        // Use Iterative
//        ArrayList<Integer> iterativeResult = IterativeSolution.traverseDirectedGraph(node2, false);
        ArrayList<Integer> iterativeResult = IterativeSolution.traverseDirectedGraph(node3, false);

        // Use Recursive (DFS)
        RecursiveSolution rs = new RecursiveSolution();
        ArrayList<Integer> recursiveResult = rs.traverseDirectedGraphRecursively(node2);

        // Print the result
        ArrayList<Integer> showResult = iterativeResult;
//        ArrayList<Integer> showResult = recursiveResult;
        System.out.println(showResult.isEmpty() ? "empty" : showResult.toString());


    }
}
