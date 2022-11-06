package DynamicProgramming;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这个题主要是想清楚一个特征: 要想最长的subsequence, 前面的数字越小越好
 * 进一步: 如果长度不减少的情况下, 前面的数字越小越好
 * 进一步: 保证sequence不变的情况下, 用小的数字代替之前较大的数字
 */
public class DP300LongestIncreasingSubsequence {
    /**
     * Nov 2022 Crib answers 45min
     * 这个题主要是想清楚一个特征: 要想最长的subsequence, 前面的数字越小越好
     * 进一步: 如果长度不减少的情况下, 前面的数字越小越好
     * 进一步: 保证sequence不变的情况下, 用小的数字代替之前较大的数字
     */
    static class IntelligentlyBuildSubsequence {
        public int lengthOfLIS(int[] nums) {
            List<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);
            for (int i = 1; i < nums.length; ++i) {
                int curr = nums[i];
                if (curr > sub.get(sub.size() - 1)) {
                    sub.add(curr);
                } else {
                    int j = 0;
                    while (curr > sub.get(j)) {
                        j ++;
                    }
                    sub.set(j,curr);
                }
            }
            return sub.size();
        }
    }

    /**
     * 每一次的计算都是dp[i]
     * Represents the length of the longest increasing subsequence possible considering the array elements
     * up to the ith index only
     */
    static class DP {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int globalMax = 1;
            for (int i = 0; i < nums.length; ++i) {
                dp[i] = 1;
                // Get the longest LIS if array end by i
                for (int j = 0; j <= i; ++j) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                    globalMax = Math.max(dp[i], globalMax);
                }
            }
            return globalMax;
        }
    }

    /**
     * 2017 到2022已经看不懂了. TODO: lowest priority理解一下
     */
    static class memoration {

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
    }
}
