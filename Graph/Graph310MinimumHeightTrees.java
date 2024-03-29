package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 *   In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1,
 *   and an array of n - 1 edges where edges[i] = [ai, bi] indicates that
 *   there is an undirected edge between the two nodes ai and bi in the tree,
 *   you can choose any node of the tree as the root.
 *   When you select a node x as the root, the result tree has height h.
 *   Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */
public class Graph310MinimumHeightTrees {
    /**
     * Crib answer
     * Aug 2022
     */
    class Topological_Sorting {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n < 2) {
                List<Integer> res = new ArrayList<>();
                for (int i = 0; i < n; ++i) {
                    res.add(i);
                }
                return res;
            }
            // Build the graph with the adjacency list
            ArrayList<Set<Integer>> neighbors = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                neighbors.add(new HashSet<Integer>());
            }
            for (int[] edge : edges) {
                int node1 = edge[0];
                int node2 = edge[1];
                neighbors.get(node1).add(node2);
                neighbors.get(node2).add(node1);
            }

            // Initielize the first layer of leaves
            ArrayList<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (neighbors.get(i).size() == 1) {
                    leaves.add(i);
                }
            }

            // Trim the leaves until reaching the centroids
            int remainingNodes = n;
            while (remainingNodes > 2) {
                remainingNodes -= leaves.size();
                ArrayList<Integer> newLeaves = new ArrayList<>();

                // remove current leaves along with edge
                for (Integer leaf : leaves) {
                    // The only neighbor left for the leaf node
                    Integer neighbor = neighbors.get(leaf).iterator().next();
                    // remove the edge along with the leave node
                    neighbors.get(neighbor).remove(leaf);
                    if (neighbors.get(neighbor).size() == 1) {
                        newLeaves.add(neighbor);
                    }
                }
                leaves = newLeaves;
            }
            return leaves;
        }
    }
}
