package DynamicProgramming;

/**
 * 329. Longest Increasing Path in a Matrix
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 *   You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 * Example 1:
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class DP329LongestIncreasingPathInAMatrix {
    /**
     * Aug 2022 这个纯DFS也可以做.
     * 不需要visited[][] 因为只要下一个比他大, 一定比之前path上的都大. 这个是题目特殊性!
     */
    class DFS_Memorization {
        private int globalMax;
        private int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int m;
        private int n;
        private int[][] matrix;

        public int longestIncreasingPath(int[][] matrix) {
            this.m = matrix.length;
            this.n = matrix[0].length;
            this.matrix = matrix;
            this.globalMax = 0;

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int curr = dfs(i, j, new int[m][n]) + 1;
                    globalMax = Math.max(curr, globalMax);
                }
            }
            return globalMax;
        }

        private int dfs(int a, int b, int[][] lengths) {

            if (lengths[a][b] != 0) {
                return lengths[a][b];
            }
            int currMax = 0;
            for (int[] dir : directions) {
                int row = a + dir[0];
                int col = b + dir[1];
                if (row < 0 || row >= m || col < 0 || col >= n || matrix[row][col] <= matrix[a][b]) {
                    continue;
                }
                int curr = dfs(row, col, lengths) + 1;
                currMax = Math.max(curr, currMax);

            }
            lengths[a][b] = currMax;
            return currMax;
        }
    }
}
