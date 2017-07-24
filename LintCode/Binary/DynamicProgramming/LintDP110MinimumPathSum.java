package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP110MinimumPathSum {
    /**
     * Grid DP 2017/7/17
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; //都写了是non-negative, 所以应该返回0
        }
        int n = grid.length;
        int m = grid[0].length;

        int[][] f = new int[n][m];
        f[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            f[0][i] = f[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }

        return f[n - 1][m - 1];
    }
    private void print(int[][] grid) {
        int m = grid[0].length;
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
