package LintCode.Binary.DynamicProgramming;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP513PerfectSquares {
    /**
     * 2017/7/17 看答案坐的
     */
    public int numSquares(int n) {
        // Write your code here
        int[] dp = new int[n + 1]; //要一直算到n所以初始化别搞错了是n + 1
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i * i <= n; ++i)
            dp[i * i] = 1;

        for (int i = 0; i <= n; ++i) {
            System.out.println(i + " : dp[i]=" + dp[i]);
            for (int j = 0; i + j * j <= n; ++j) {
                dp[i + j * j] = Math.min(dp[i] + 1, dp[i + j * j]);
                System.out.println("  i + j * j=" + (i + j * j) + " dp[i + j * j]=" + dp[i + j * j]);
            }
            System.out.println();
        }
        return dp[n];
    }

    //todo: Math
}
