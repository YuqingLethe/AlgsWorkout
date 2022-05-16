package Graph;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b]
 *    indicates 2 indices(0-indexed) of the string.
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 * Example 2:
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 *
 * Example 3:
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 */
public class Graph1202SmallestStringWithSwaps {
    /**
     * April 2022 Crib the answer
     */
    class DFS {
        private final static int N = 100001;
        List<Integer>[] adj = new ArrayList[N];
        private boolean[] visited = new boolean[N];

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            // Build the adj list
            for (int i = 0; i < s.length(); ++i) {
                adj[i] = new ArrayList<>();
            }
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int destination = edge.get(1);
                adj[source].add(destination);
                adj[destination].add(source);
            }

            char[] answer = new char[s.length()];
            for (int i = 0; i < s.length(); ++i) {
                if (!visited[i]) {
                    List<Character> characters = new ArrayList<>();
                    List<Integer> indices = new ArrayList<>();
                    //根据DFS的写法, 从这里执行一次DFS其实是找出关于i节点的整个图. 相关的节点全部都变成visited了.
                    DFS(s, i, characters, indices);

                    Collections.sort(characters);
                    Collections.sort(indices);

                    for (int j = 0; j < characters.size(); ++j) {
                        answer[indices.get(j)] = characters.get(j);
                    }
                }
            }
            return new String(answer);
        }
        private void DFS(String s, int vertex, List<Character> characters, List<Integer> indices) {
            characters.add(s.charAt(vertex));
            indices.add(vertex);
            visited[vertex] = true;

            for (int adjacent : adj[vertex]) {
                if (!visited[adjacent]) {
                    DFS(s, adjacent, characters, indices);
                }
            }
        }
    }

    /**
     * April 2022 Crib the answer
     */
    class Union_Find {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            // Build the union find graph
            UnionFind uf = new UnionFind(s.length());
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int destination = edge.get(1);
                uf.union(source, destination);
            }
            // build a map of a vertex and all its adjacent vertex
            Map<Integer, List<Integer>> rootToComponent = new HashMap<>();
            for (int i = 0; i < s.length(); ++i) {
                int root = uf.find(i);
                rootToComponent.putIfAbsent(root, new ArrayList<>());
                rootToComponent.get(root).add(i);
            }

            char[] answer = new char[s.length()];
            for (List<Integer> indices: rootToComponent.values()) {
                List<Character> characters = new ArrayList<>();
                for (int i: indices) {
                    characters.add(s.charAt(i));
                }
                Collections.sort(characters);
                //为什么每次都要sort然后存answer? 下次不就没了吗
                for (int i = 0; i < indices.size(); ++i) {
                    answer[indices.get(i)] = characters.get(i);
                }
            }
            return new String(answer);
        }
        class UnionFind {
            private int[] root;
            private int[] rank;

            public UnionFind(int size) {
                root = new int[size];
                rank = new int[size];
                for (int i = 0; i < size; i++) {
                    root[i] = i;
                    rank[i] = i;
                }
            }
            public int find(int x) {
                if (x == root[x]) {
                    return x;
                }
                return root[x] = find(root[x]);
            }
            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    if (rank[rootX] >= rank[rootY]) {
                        root[rootY] = rootX;
                        rank[rootX] += rank[rootY];
                    } else {
                        root[rootX] = rootY;
                        rank[rootY] += rank[rootX];
                    }
                }
            }
        }
    }
}
