package UnionFind;

/**
 * Created by yuqing on 7/13/22.
 */
public class UnionFind200NumberOfIslands {


    // What is union find https://www.youtube.com/watch?v=VJnUwsE4fWA
    // Notice the optimization!
    class Disjoint_Set {
        int rows;
        int cols;
        class UnionFind {
            int count;
            int[] parent;
            int[] rank;

            public UnionFind(char[][] grid) {
                count = 0;
                int rows = grid.length;
                int cols = grid[0].length;
                parent = new int[rows * cols];
                rank = new int[rows * cols];
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        int self = i * cols + j;
                        if (grid[i][j] == '1') {
                            parent[self] = self;
                            ++count;
                        }
                        rank[self] = 0;
                    }
                }
            }
            public void union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);
                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) { //取大的那個 Rank Merge
                        parent[rooty] = rootx;
                    } else if (rank[rootx] < rank[rooty]) {
                        parent[rootx] = rooty;
                    } else {
                        parent[rooty] = rootx;
                        rank[rootx] += 1;
                    }
                    --count;
                }
            }

            public int find(int i) {
                if (parent[i] != i) {
                    parent[i] = find(parent[i]); // Path compression
                }
                return parent[i];
            }
            public int getCount() {
                return count;
            }
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int rows = grid.length;
            int cols = grid[0].length;
            UnionFind uf = new UnionFind(grid);
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (grid[i][j] == '1') {
                        grid[i][j] = '0';
                        int self = i * cols + j;
                        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                            uf.union(self, (i - 1) * cols + j);
                        }
                        if (i + 1 < rows && grid[i + 1][j] == '1') {
                            uf.union(self, (i + 1) * cols + j);
                        }
                        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                            uf.union(self, i * cols + j - 1);
                        }
                        if (j + 1 < cols && grid[i][j + 1] == '1') {
                            uf.union(self, i * cols + j + 1);
                        }
                    }
                }
            }
            return uf.getCount();
        }

    }

    // Check the other solutions in Matrix200NumberOfIslands
    class DFS {

    }
    class BFS {

    }
}
