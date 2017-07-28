package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/28.
 */
public class LintBinary617MaximumAverageSubarray {
    /**
     * 看答案做得
     */
    public class Solution {

        public double maxAverage(int[] nums, int k) {
            if (nums == null || nums.length < k) {
                return -1;
            }
            double lo = Integer.MIN_VALUE;
            double hi = Integer.MAX_VALUE;
            double eps = 1e-6;
            for (int i = 0; i < nums.length; i++) {
                if (lo > nums[i]) {
                    lo = nums[i];
                }
                if (hi < nums[i]) {
                    hi = nums[i];
                }
            }

            while (lo + eps < hi) {
                double mid = lo + (hi - lo) / 2;
                if (valid(nums, mid, k)) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }
        private boolean valid (int[] nums, double mid, int k) {
            double[] preSum = new double[nums.length + 1];
            preSum[0] = 0;
            double min_prev = 0;
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1] - mid;
                if (i >= k && preSum[i] - min_prev >= 0) {
                    return true;
                }
                if (i >= k) {
                    min_prev = Math.min(min_prev, preSum[i - k + 1]);
                }
            }
            return false;
        }
    }
}
