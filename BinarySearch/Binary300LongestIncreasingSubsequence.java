package BinarySearch;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/7.
 * 四种算法都没有想出来, 直接参考: https://leetcode.com/articles/longest-increasing-subsequence/
 */
public class Binary300LongestIncreasingSubsequence {

    //Method1: Brute
    public int lengthOfLISBrute1(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }
    private int lengthOfLIS (int[] nums, int priv, int curr) {
        if (curr == nums.length)
            return 0;
        int taken = 0, nottaken;
        if (nums[curr] > priv)
            taken = 1 + lengthOfLIS(nums, nums[priv], curr + 1);
        nottaken = lengthOfLIS(nums, priv, curr + 1);
        return taken > nottaken ? taken : nottaken;
    }
    //Method2: Memorization 不要忘了 privIndex < 0 的情况
    public int lengthOfLISMemo(int[] nums) {
        int[][] memo = new int[nums.length + 1][nums.length + 1];
        for (int[] i : memo) {
            Arrays.fill(i, -1); //Learn: how to fill in the initial values into array
        }
        return lengthOfLIS(nums, -1, 0, memo);
    }

    private int lengthOfLIS (int[] nums, int privIndex, int currIndex, int[][] memo) {
        if (currIndex == nums.length)
            return 0;
        if (memo[privIndex + 1][currIndex] > 0)
            return memo[privIndex][currIndex];

        int taken = 0, nottaken;
        if (privIndex < 0 || nums[currIndex] > nums[privIndex])
            taken = 1 + lengthOfLIS(nums, currIndex, currIndex + 1, memo);
        nottaken = lengthOfLIS(nums, privIndex, currIndex + 1, memo);
        memo[privIndex + 1][currIndex] = Math.max(taken, nottaken);
        return memo[privIndex + 1][currIndex];
    }
    //找错误, 这个方法是错的
    public int lengthOfLISDPwrong (int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int currLIS = dp[i - 1];
            for (int j = 0; j < i; j++) { //其实可以只从dp[i] == currLIS时候才开始比较
                if (nums[j] < nums[i] && dp[j] == currLIS) {
                    currLIS = dp[j] + 1;
                }
            }
            dp[i] = Math.max(currLIS, dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
    //每一次的计算都是dp[i]
    //represents the length of the longest increasing subsequence possible considering the array elements
    // upto the i^{th}i​th index only
    public int lengthOfLISDP (int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxAns = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[j]) {
                    maxVal = Math.max(dp[j], maxVal);
                }
            }
            dp[i] = maxVal + 1;
            maxAns = Math.max(maxAns, dp[i]);

        }
        return maxAns;
    }

    public int lengthOfLISDPBinary (int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int i : nums) {
            int pos = Arrays.binarySearch(dp, 0, len, i);
            if (pos < 0)
                pos = -(pos + 1);
            dp[pos] = i;
            if (pos == len) {
                len++;
            }
        }
        return len;

    }

}
