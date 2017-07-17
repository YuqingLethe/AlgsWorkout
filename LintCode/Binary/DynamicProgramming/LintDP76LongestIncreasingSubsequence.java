package LintCode.Binary.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on .2017/7/17
 */
public class LintDP76LongestIncreasingSubsequence {
    /**
     * 2017/7/17
     * normal DP solutions
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] f = new int[len];
        for (int i = 0; i < len; i++) {
            f[i] = 1;
        }
        int ans = 1;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[i] && f[j] <= f[i]) {
                    f[j] = f[i] + 1;

                }
            }
            if (f[i] > ans) { //写在外循环比较清楚. 外循环找到了截止到当前i的最大值
                ans = f[i];
            }
        }
        return ans;
    }
}
