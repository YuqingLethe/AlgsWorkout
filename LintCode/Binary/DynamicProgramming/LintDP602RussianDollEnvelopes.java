package LintCode.Binary.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Administrator on 2017/7/17.
 */
public class LintDP602RussianDollEnvelopes {
    /**
     * 2017/7/17 中规中矩的DP方法, TLE on lintcode, fine on leetcode
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        //应该是envelopes[0].length != 2 不是< 2
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length != 2) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr1[1] - arr2[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            int k = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    k = Math.max(k, dp[j]);
                }
            }
            dp[i] = k + 1;
            ans = ans < dp[i] ? dp[i] : ans;
        }

        return ans;
    }

    /**
     * DP + BS  直接看的答案, 可以print出来看一下过程, 非常好
     * 因为在这个信封之前的信封width必然小于它,
     * 直接用二分法找宽度最大的, 那个信封的lenth一定是最长的
     */
    public int maxEnvelopesDPBS(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            System.out.println("envelop:" + envelope[0] + " " + envelope[1]);
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -index - 1;
            dp[index] = envelope[1];
            if (index == len)
                len++;
            System.out.println("idx=" + index + " dp[idx]=" + dp[index] + " len=" + len);
        }
        return len;

    }
}
