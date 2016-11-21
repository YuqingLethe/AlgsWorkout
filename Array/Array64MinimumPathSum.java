package Array;

public class Array64MinimumPathSum {
    /**
     * Runtime: 4ms  Use: 15min One-time success 11/20/2016
     * Brute Force
     * Another solution https://discuss.leetcode.com/topic/5459/my-java-solution-using-dp-and-no-extra-space/14
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] +=  grid[i][j - 1];
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                else {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * https://discuss.leetcode.com/topic/3184/ac-java-dp-solution-v-s-tle-dijstra-solution
     */
    public static int minPathSumByDijstra(int[][] grid) {
        return 1;
    }


    public static void main(String[] args) {
        int[][] grid1 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] grid2 = {{2, 4, 6}};
        int[][] grid3 = {{}};//invalid input?
        int[][] grid4 = null;
        System.out.println(grid3.length);
        System.out.println(grid3[0].length);
        System.out.println(minPathSum(grid4));
    }
}
