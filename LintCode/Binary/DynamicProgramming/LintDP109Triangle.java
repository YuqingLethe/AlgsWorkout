package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/8/4.
 */
public class LintDP109Triangle {
    public class SolutionWrong {
        /**
         * 先确认数组Triangle的i, i相邻两点, 是(i-1, i-1和(i-1, i) 与矩阵不同
         * @param triangle: a list of lists of integers.
         * @return: An integer, minimum path sum.
         */
        public int minimumTotal(int[][] triangle) {
            // if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            //     return -1;
            // }
            if (triangle == null || triangle.length == 0) {
                return -1;
            }
            if (triangle[0] == null || triangle[0].length == 0) {
                return -1;
            }


            int n = triangle.length;
            int m = triangle[n - 1].length;
            int[][] f = new int[n][m];
            //set up the f
            f[0][0] = triangle[0][0];
            for (int i = 1; i < n; i++) {
                f[i][0] = f[i - 1][0] + triangle[i][0];
                f[i][i] = f[i - 1][i - 1] + triangle[i][i]; //注意下标
            }
            //build the whole f
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m - 1; j++) {
                    f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle[i][j];
                }
            }
            //find the minimum sum
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++)  {
                ans = Math.min(ans, f[n - 1][i]);
            }
            return ans;
        }
    }
}
