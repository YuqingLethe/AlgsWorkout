package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/22.
 */
public class LintTP533TwoSumClosestToTarget {
    public class Solution {
        /**
         * 想的复杂了, 其实很简单的 TwoPointers用O(n)完成就不错了
         */
        public int twoSumClosest(int[] nums, int target) {
            // Write your code here
            if (nums == null || nums.length <= 1) {
                return -1;
            }
            Arrays.sort(nums);
            int lo = 0;
            int hi = nums.length - 1;
            int min = Integer.MAX_VALUE;
            while(lo < hi)  {
                int diff = target - nums[lo] - nums[hi];
                min = Math.min(Math.abs(diff), min);
                if (min == 0) {
                    return 0;
                }
                if (diff > 0) {
                    lo++;
                } else {
                    hi--;
                }
            }
            return min;
        }
    }
}
