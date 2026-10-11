package DynamicProgramming;

public class DP53MaximumSubarray {
    /**
     * 我之前想的是recursive，从左到右每一个startIdx之后求最大的从startIdx+1开始的subarray，但写的磕磕绊绊。
     * 后来看了答案，从后往前算更方便，只要前面的sum比当前的sum小，我们就能舍弃前面。
     * 我的想法天然比这个多了一个loop就写乱了。
     * 最重要的就是currSum = Math.max(nums[i], currSum + nums[i]);这一步。
     */
    class CopyAnswer2026 {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            final int n = nums.length;
            int currSum = nums[0];
            int maxSum = nums[0];
            for (int i = 1; i < n; i ++) {
                currSum = Math.max(nums[i], currSum + nums[i]);
                maxSum = Math.max(currSum, maxSum);
                System.out.printf("i=%d, nums[i] = %d, currSum=%d", i, nums[i], currSum);
                System.out.println();
            }
            return maxSum;
        }

    }
}
