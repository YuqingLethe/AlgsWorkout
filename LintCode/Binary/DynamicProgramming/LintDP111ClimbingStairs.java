package LintCode.Binary.DynamicProgramming;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP111ClimbingStairs {
    /**
     * 2017/7/17
     */
    public int climbStairs(int n) {
        // Failed case n = 0   直接就是top应该等于1而不是0 不跳也是一种方法
        if (n == 0) {
            return 1;
        }
        int[] sum = new int[n];
        if (n >= 1) {
            sum[0] = 1;
        }
        if (n >= 2) {
            sum[1] = 2;
        }
        for (int i = 2; i < n; i++) {
            sum[i] = sum[i - 1] + sum[i - 2];
        }
        return sum[n - 1];
    }
}
