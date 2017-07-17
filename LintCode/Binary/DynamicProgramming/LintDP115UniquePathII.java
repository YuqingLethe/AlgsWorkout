package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP115UniquePathII {
    /**
     * 应该把obstacleGrid[i][j] == 1这种情况直接一个大if
     * 情况分为0,0   第一行,   第一列   其他来处理
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        // write your code here
        if (obstacleGrid == null
                || obstacleGrid.length == 0
                || obstacleGrid[0].length == 0
                || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) { //这种情况不忘了
                    continue;
                }

                if (i == 0 && j > 0) {
                    if (obstacleGrid[i][j] == 1 || f[i][j - 1] == 0) {
                        f[i][j] = 0;
                    } else {
                        f[i][j] = 1;
                    }
                } else if (j == 0 && i > 0) {
                    //test case 上至少行列上都要各有一个1来测试, 所以至少4个cases情况
                    if (obstacleGrid[i][j] == 1 || f[i - 1][j] == 0) {
                        f[i][j] = 0;
                    } else {
                        f[i][j] = 1;
                    }
                } else if (obstacleGrid[i][j] == 1)  {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        return f[n - 1][m - 1];
    }

    /**
     * loop里面的情况为 0,0     obstacle  和非obstacle
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        // write your code here
        if (obstacleGrid == null
                || obstacleGrid.length == 0
                || obstacleGrid[0].length == 0
                || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if(obstacleGrid[i][j] == 1) {
                    f[i][j] = 0;
                    continue;
                }

                if (i == 0) {
                    f[i][j] = f[i][j - 1];
                } else if (j == 0) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
//                System.out.println(i + " " + j + " = " + f[i][j]);
            }
        }
        return f[n - 1][m - 1];
    }
}
