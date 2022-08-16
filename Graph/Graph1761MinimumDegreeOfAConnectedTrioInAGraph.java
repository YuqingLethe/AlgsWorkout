package Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges,
 *     where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
 *
 * A connected trio is a set of three nodes where there is an edge between every pair of them.
 * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
 * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
 *
 * Example 1:
 *
 * Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * Output: 3
 * Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
 *
 * Example 2:
 *
 * Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * Output: 0
 * Explanation: There are exactly three trios:
 * 1) [1,4,3] with degree 0.
 * 2) [2,5,6] with degree 2.
 * 3) [5,6,7] with degree 2.
 *
 * Constraints:
 *
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * There are no repeated edges.
 */
public class Graph1761MinimumDegreeOfAConnectedTrioInAGraph {
    /**
     * Crib answer Aug 2022
     * https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/discuss/2415156/Java-Solution
     *
     */
    class Graph_IterateThree {
        public int minTrioDegree(int n, int[][] edges) {
            int[][] adj = new int[n + 1][n + 1]; //注意我们把edge value对应到了idx, 从1到n, 需要n+1个
            int[] degree = new int[n + 1]; //其实这个是个isEdge的boolean

            for (int[] edge : edges) {
                adj[edge[0]][edge[1]] = 1;
                adj[edge[1]][edge[0]] = 1;
                degree[edge[0]] ++;
                degree[edge[1]] ++;
            }

            int ans = Integer.MAX_VALUE;

            for (int i = 1; i <= n; ++i) {
                for (int j = i + 1; j <= n; ++j) {
                    if (adj[i][j] != 1) {
                        continue;
                    }
                    for (int k = j + 1; k <= n; ++k) {
                        if (adj[i][k] == 1 && adj[j][k] == 1) {
                            ans = Math.min(ans, degree[i] + degree[j] + degree[k] - 6);
                        }
                    }

                }
            }
            if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }
            return ans;
        }
    }

    /**
     * 节省一些不用的iterate
     * https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/discuss/2414681/O(N3)-Java
     */
    class Graph_UseSet_IterateEdge {
        public int minTrioDegree(int n, int[][] edges) {
            Set<Integer>[] adj = new HashSet[n + 1]; // Set array
            int ans = n * (n - 1) / 2;
            // Set up Adjacent set
            for (int i = 1; i <= n; ++i) {
                adj[i] = new HashSet<>();
            }
            for (int[] edge : edges) {
                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);
            }
            // Find trios
            for (int[] edge : edges) {
                for (int i = 1; i <= n; ++i) {
                    if (i > edge[0] || i > edge[1]) {
                        break; // Beyond the edge scope, check next edge. Only leave i smaller than two edge nodes
                    }
                    if (i == edge[0] || i == edge[1] || !adj[i].contains(edge[0]) || !adj[i].contains(edge[1])) {
                        continue; // node i belongs to edge or not adjacent, coninue;
                    }
                    int currSize = adj[i].size() + adj[edge[0]].size() + adj[edge[1]].size() - 6;
                    ans = Math.min(ans, currSize);
                }
            }
            if (ans == n * (n - 1) / 2) {
                return -1;
            } else {
                return ans;
            }
        }
    }
}
