package Matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LintCode433NumberOfIslands July 2017
 * Take Away： 四個方向要麼用int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
 * 要麼用recursive 。 不要用new class或者別的浪費算力！！
 * dfs(r - 1, c);
 * dfs(r + 1, c);
 * dfs(r, c - 1);
 * dfs(r, c + 1);
 *
 *
 *
 *
 *
 *
 Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



 Example 1:

 Input: grid = [
 ["1","1","1","1","0"],
 ["1","1","0","1","0"],
 ["1","1","0","0","0"],
 ["0","0","0","0","0"]
 ]
 Output: 1

 Example 2:

 Input: grid = [
 ["1","1","0","0","0"],
 ["1","1","0","0","0"],
 ["0","0","1","0","0"],
 ["0","0","0","1","1"]
 ]
 Output: 3


 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 300
 grid[i][j] is '0' or '1'.
 */
public class Matrix200NumberOfIslands {
    /**
     * July 2022 25min 思路獨立
     *
     * Take away: 如果用Queue來尋找island tile， 用row * cols + col 來唯一代表tile，
     *            空間複雜度可以降低。
     */
    class DFS {
        int rows;
        int cols;
        int[][] seen;
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int result = 0;
            rows = grid.length;
            cols = grid[0].length;
            seen = new int[rows][cols];
            for (int i = 0; i < rows; ++i) { //直接比較char也很方便， 不必須
                for (int j = 0; j < cols; ++j) {
                    seen[i][j] = grid[i][j] - '0';
                }
            }

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (seen[i][j] == 1) {
                        dfs(i, j);
                        result ++;
                    }
                }
            }
            return result;
        }

        private void dfs(int r, int c) {
            if (r < 0 || r >= rows || c < 0 || c >= cols || seen[r][c] == 0) {
                return;
            }
            seen[r][c] = 0; //巧妙：直接變海水不影響後續判斷！

            dfs(r - 1, c);
            dfs(r + 1, c);
            dfs(r, c - 1);
            dfs(r, c + 1);
        }

    }

    /**
     * Crib answer July 2022
     */
    class BFS {
        int rows;
        int cols;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int rows = grid.length;
            int cols = grid[0].length;
            int result = 0;
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (grid[i][j] == '1') {
                        result ++;
                        grid[i][j] = '0';
                        Queue<int[]> neighbors = new LinkedList<>();
                        int self = i * cols + j;
                        neighbors.add(new int[]{i, j});
                        while (!neighbors.isEmpty()) {
                            int[] curr = neighbors.remove();
                            for (int[] dir : dirs) {
                                int row = curr[0] + dir[0];
                                int col = curr[1] + dir[1];
                                if (row < 0 || row >= rows || col < 0 || col >= cols
                                        || grid[row][col] != '1') {
                                    continue;
                                }
                                grid[row][col] = '0';
                                neighbors.add(new int[]{row, col});
                            }
                        }
                    }
                }
            }
            return result;
        }

    }
    class Disjoint_Set {} // Find in UnionFind200
}
