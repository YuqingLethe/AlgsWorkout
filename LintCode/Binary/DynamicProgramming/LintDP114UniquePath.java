package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP114UniquePath {
    /**
     * 第一行第一列都是1 2017/7/17
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 0 && n == 0) {
            return 0;
        }

        int[][] f = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = f[i][j - 1] + f[i - 1][j];
            }
        }
        return f[n - 1][m - 1];
    }
}
