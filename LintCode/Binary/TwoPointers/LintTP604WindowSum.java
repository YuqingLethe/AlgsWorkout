package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/7/10.
 */
public class LintTP604WindowSum {
    /**
     * 2017/7/10
     */
    public class Solution {

        public int[] winSum(int[] nums, int k) {
            if (nums == null || nums.length < k || k <= 0) //标准的corner cases
                return new int[0];

            int threeSum = 0;
            int[] ans = new int[nums.length - k + 1];
            for (int i = 0; i < k; i++) {
                threeSum += nums[i];
            }
            ans[0] = threeSum;
            for (int i = k; i < nums.length; i++) {
                threeSum += nums[i] - nums[i - k];
                ans[i - k + 1] = threeSum;
            }
            return ans;
        }
    }

}
