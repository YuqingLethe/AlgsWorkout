package NewDataStructure;

import java.util.ArrayList;
import java.util.List;

public class UnionFind323NumberOfConnectedComponentsInAnUndirectedGraph {
    /**
     * Sep 2022 30min
     */
    static class DisjointedSetUnion {
        class UnionFind {
            private int[] arr;
            private int[] levels;
            private int count;

            UnionFind(int n) {
                this.arr = new int[n];
                for (int i = 0; i < n; ++i) { // assign parent to itself
                    arr[i] = i;
                }
                this.levels = new int[n];
                this.count = n;
            }

            public void union(int a, int b) {
                int aParent = findParent(a);
                int bParent = findParent(b);
                if (aParent == bParent) {
                    return;
                }

                int aLevel = levels[aParent];
                int bLevel = levels[bParent];
                if (aLevel > bLevel) {
                    arr[b] = aParent;
                    arr[bParent] = aParent;
                } else if (aLevel < bLevel) {
                    arr[a] = bParent;
                    arr[aParent] = bParent;
                } else {
                    arr[b] = aParent;
                    arr[bParent] = aParent;
                    levels[aParent] ++;
                }
                count --;
            }

            /**
             * 用iterative找parent而不是recursive
             */
            public int findParent(int a) {
                int parent = arr[a]; //注意最后要return parentOf[a], 所以这里最好设置成arr[a]. 设置成a不影响while循环, 但影响output!
                while (parent != arr[parent]) {
                    parent = arr[parent];
                }
                // if (a != arr[a]) {
                //     return findParent(arr[a]);
                // }
                return parent;
            }
            public int getCount() {
                return count;
            }
        }

        public int countComponents(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for (int[] edge : edges) {
                uf.union(edge[0], edge[1]);
            }
            return uf.getCount();
        }
    }

    /**
     * Crib answer Sep 2022 30 min
     * 自己写错的地方是对于DFS的理解. build adj[]是前期工作而不是在dfs的时候build.
     * visited[]这个的设置我也没想到, 这是cluster的关键的一环, 典型的用法.
     * Build adj[]属于BFS吗? 不属于! 这属于遍历input!
     */
    static class DFS {
        private int count;
        public int countComponents(int n, int[][] edges) {
            List<Integer>[] adj = new ArrayList[n];
            boolean[] visited = new boolean[n];

            for (int i = 0; i < n; ++i) {
                adj[i] = new ArrayList();
            }

            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                adj[a].add(b);
                adj[b].add(a);
            }

            this.count = 0;
            for (int i = 0; i < n; ++i) {
                if (!visited[i]) {
                    dfs(adj, visited, i);
                    count ++;
                }
            }
            return this.count;
        }

        private void dfs(List<Integer>[] adj, boolean[] visited, int x) {
            visited[x] = true;
            List<Integer> adjacents = adj[x];

            for (Integer y : adjacents) {
                if (!visited[y]) {
                    dfs(adj, visited, y);
                }
            }
        }
    }

    public static void main(String[] args) {
        DFS mine = new DFS();
        int[][] arr;
        int n;
        int ans;

        // arr = new int[][]{{0,1}, {1,2}, {3,4}};
        // n = 5;
        // ans = mine.countComponents(n, arr);

        arr = new int[][]{{0,1}, {1,2}, {2, 3}, {3,4}};
        n = 5;
        ans = mine.countComponents(n, arr);

        // 10
        // [[5,6],[0,2],[1,7],[5,9],[1,8],[3,4],[0,6],[0,7],[0,3],[8,9]]

        System.out.println(ans);
    }
}
