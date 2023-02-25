package PriorityQueue;

import java.util.*;

/**
 * Created on 7/17/22.
 */
public class PQ1102PathWithMaximumMinimumValue {
    class BFS_BinarySearch {
        public int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m;
        int n;

        public int maximumMinimumPath(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int left = 0, right = Math.min(grid[0][0], grid[m - 1][n - 1]);

            while (left < right) {
                int middle = (left + right + 1) / 2;
                if (pathExists(grid, middle)) {
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            // 這裏是個優化。 其實只選Math.min(grid[0][0], grid[m - 1][n - 1])也可以。要是找不到path就-1.
            return left;
        }
        private boolean pathExists(int[][] grid, int currScore) {
            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;

            Queue<int[]> deque = new LinkedList<>();
            deque.offer(new int[]{0, 0});
            while (!deque.isEmpty()) {
                int[] curr = deque.poll();
                int curRow = curr[0];
                int curCol = curr[1];

                if (curRow == m - 1 && curCol == n - 1) {
                    return true;
                }
                for (int[] dir : dirs) {
                    int newRow = curRow + dir[0];
                    int newCol = curCol + dir[1];
                    if (newRow < 0 || newRow >= m | newCol < 0 || newCol >= n || visited[newRow][newCol] == true || grid[newRow][newCol] < currScore) {
                        continue;
                    }
                    visited[newRow][newCol] = true;
                    deque.offer(new int[]{newRow, newCol});
                }
            }
            return false;
        }
    }
    class DFS_BinarySearch {
        private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int m;
        private int n;
        public int maximumMinimumPath(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            int left = 0, right = Math.min(grid[0][0], grid[m - 1][n - 1]);

            while (left < right) {
                int middle = (left + right + 1) / 2;
                boolean[][] visited = new boolean[m][n];
                if (pathExists(grid, middle, 0, 0, visited)) {
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            return left;
        }
        private boolean pathExists(int[][] grid, int currScore, int currRow, int currCol,
                                   boolean[][] visited) {
            if (currRow == m - 1 && currCol == n - 1) {
                return true;
            }

            visited[currRow][currCol] = true;
            for (int[] dir : dirs) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n
                        || grid[newRow][newCol] < currScore || visited[newRow][newCol]) {
                    continue;
                }
                if (pathExists(grid, currScore, newRow, newCol, visited)) {
                    return true;
                }
            }
            return false;
        }
    }

    class BFS_PriorityQueue {
        private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int m;
        private int n;
        public int maximumMinimumPath(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            Queue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> Integer.compare(
                            grid[b[0]][b[1]], grid[a[0]][a[1]]
                    )
            );
            boolean[][] visited = new boolean[m][n];
            pq.offer(new int[]{0, 0});
            visited[0][0] = true;

            int ans = grid[0][0];

            while(!pq.isEmpty()) {
                int[] currGrid = pq.poll();
                int currRow = currGrid[0];
                int currCol = currGrid[1];
                ans = Math.min(ans, grid[currRow][currCol]);
                if (currRow == m - 1 && currCol == n - 1) {
                    break;
                }
                for (int[] dir : dirs) {
                    int newRow = currRow + dir[0];
                    int newCol = currCol + dir[1];
                    if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n
                            || visited[newRow][newCol]) {
                        continue;
                    }
                    pq.offer(new int[] {newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
            return ans;
        }

    }

    class BFS_UnionFind {
        private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int m;
        private int n;

        private class UnionFind {
            private int N;
            private int[] rank;
            private int[] root;
            public UnionFind(int N) {
                rank = new int[N];
                root = new int[N];
                for (int i = 0; i < N; ++i) {
                    root[i] = i;
                }
            }
            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    if (rank[rootX] > rank[rootY]) {
                        root[rootY] = rootX;
                    } else if (rank[rootX] < rank[rootY]) {
                        root[rootX] = rootY;
                    } else {
                        root[rootX] = rootY;
                        rank[rootX] ++;
                    }
                }
            }
            public int find(int x) {
                if (x != root[x]) {
                    root[x] = find(root[x]);
                }
                return root[x];
            }
        }
        public int maximumMinimumPath(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            List<int[]> vals = new ArrayList<>();
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    vals.add(new int[]{grid[i][j], i, j});
                }
            }
            Collections.sort(vals, (a, b) -> (b[0] - a[0]));
            // 只存row和col也行， 不需要存value。 只需要變換下面的comparator
//            Collections.sort(vals, (a, b) -> {
//                return grid[b[0]][b[1]] - grid[a[0]][a[1]];


            UnionFind uf = new UnionFind(m * n);

            for (int[] val : vals) {
                int currRow = val[1];
                int currCol = val[2];
                int currPos = currRow * n + currCol;
                visited[currRow][currCol] = true;

                for (int[] dir : dirs) {
                    int newRow = currRow + dir[0];
                    int newCol = currCol + dir[1];
                    int newPos = newRow * n + newCol;
                    if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || visited[newRow][newCol] == false) {
                        continue;
                    }
                    uf.union(currPos, newPos);
                }

                if (uf.find(0) == uf.find(m * n - 1)) {
                    return grid[currRow][currCol];
                }
            }
            return -1;
        }

    }





}
