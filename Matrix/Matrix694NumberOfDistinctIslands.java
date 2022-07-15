package Matrix;

import javafx.util.Pair;

import java.util.*;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's
 *   (representing land) connected 4-directionally (horizontal or vertical.)
 *   You may assume all four edges of the grid are surrounded by water.

 An island is considered to be the same as another if and only if
     one island can be translated (and not rotated or reflected) to equal the other.

 Return the number of distinct islands.



 Example 1:


 Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 Output: 1

 Example 2:


 Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 Output: 3


 Constraints:
 m == grid.length
 n == grid[i].length
 1 <= m, n <= 50
 grid[i][j] is either 0 or 1.
 */
public class Matrix694NumberOfDistinctIslands {
    /**
     * July 2022 Self
     */
    class BruteForce {
        int m;
        int n;
        int[][] matrix;
        HashMap<int[], List<int[]>> islands;
        public int numDistinctIslands(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            matrix = new int[m][n];
            islands = new HashMap<>();

            for (int i = 0; i < m; ++i) {
                for(int j = 0; j < n; ++j) {
                    matrix[i][j] = grid[i][j];
                }
            }

            for (int i = 0; i < m; ++i) {
                for(int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 1) {
                        createCluster(i, j);
                    }
                }
            }
            return islands.size();
        }

        private void dfs(int i, int j, List<int[]> list) {
            if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] != 1) {
                return;
            }
            matrix[i][j] = -1;
            int[] cords = new int[]{i, j};
            list.add(cords);
            dfs(i + 1, j, list);
            dfs(i - 1, j, list);
            dfs(i, j + 1, list);
            dfs(i, j - 1, list);
        }

        private void createCluster(int i, int j) {

            List<int[]> cords = new ArrayList<>();
            dfs(i, j, cords);
            int[] cluster_id = new int[]{i, j};
            updateIslandsInfo(cluster_id, cords);
        }

        private void updateIslandsInfo(int[] clusterId, List<int[]> cords) {
            if (islands.isEmpty()) {
                islands.put(clusterId, cords);
                return;
            }
            Set<int[]> keySets = islands.keySet();
            for(int[] existId : keySets) {
                List<int[]> existCords = islands.get(existId);
                if (isSameIsland(existId, existCords, clusterId, cords)) {
                    return;
                }
            }
            islands.put(clusterId, cords);
            return;
        }

        private boolean isSameIsland(int[] aId, List<int[]> aCords, int[] bId, List<int[]> bCords) {
            if (aCords.size() != bCords.size()) {
                return false;
            }
            int rowDiff = bId[0] - aId[0];
            int colDiff = bId[1] - aId[1];
            for (int i = 0; i < aCords.size(); ++i) {
                int[] aCord = aCords.get(i);
                int[] bCord = bCords.get(i);
                if (bCord[0] - aCord[0] != rowDiff || bCord[1] - aCord[1] != colDiff) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * July 2022 Crib solution
     * 這個不能用Set<Set<int[]>> 只能用Set<Set<Pair<Integer, Integer>>>！
     * Pair和hashmap有點像
     */
    class HashCords {
        int m;
        int n;
        HashMap<int[], List<int[]>> islands;
        private boolean[][] seen;
        private Set<Pair<Integer, Integer>> currentIsland;
        private int currRowOrigin;
        private int currColOrigin;
        private int[][] grid;
        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.m = grid.length;
            this.n = grid[0].length;
            this.seen = new boolean[m][n];
            Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    this.currentIsland = new HashSet<>();
                    this.currRowOrigin = i;
                    this.currColOrigin = j;
                    dfs(i, j);
                    if (!currentIsland.isEmpty()) {
                        islands.add(currentIsland);
                    }
                }
            }
            return islands.size();
        }

        private void dfs(int row, int col) {
            if (row < 0 || row >= m || col < 0 || col >= n) {
                return;
            }
            if (grid[row][col] == 0 || seen[row][col]) {
                return;
            }
            seen[row][col] = true;
            // 這裏存差值, 才能保證相同shape island的set一模一樣
            currentIsland.add(new Pair<>(row - currRowOrigin, col - currColOrigin));
            dfs(row + 1, col);
            dfs(row - 1, col);
            dfs(row, col + 1);
            dfs(row, col - 1);
        }

    }
}
