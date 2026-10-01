package DynamicProgramming;

public class DP416PartitionEqualSubsetSum {
    /**
     * 这个理解不了为什么只拿最后一个，另一个interative的算法更是理解不了
     * TODO：以后还得再看
     */
    class Solution {
        public boolean canPartition(int[] nums) {
            final int n = nums.length;
            int totalSum = 0;
            for (int i = 0; i < n; i ++) {
                totalSum += nums[i];
            }
            if (totalSum % 2 != 0) {
                return false;
            }
            int subSetSum = totalSum / 2;
            Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];
            return dfs(nums, subSetSum, n, memo);
        }

        private boolean dfs(int[] nums, int subSetSum, int n, Boolean[][] memo) {
            if (subSetSum < 0) {
                return false;
            }
            if (subSetSum == 0) {
                return true;
            }
            if (memo[n][subSetSum] != null) {
                return memo[n][subSetSum];
            }
            boolean result = dfs(nums, subSetSum - nums[n - 1], n - 1, memo)
                    || dfs(nums, subSetSum, n - 1, memo);
            memo[n][subSetSum] = result;
            return result;
        }
    }
}
