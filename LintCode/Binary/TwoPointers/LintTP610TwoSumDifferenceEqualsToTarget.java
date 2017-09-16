package LintCode.Binary.TwoPointers;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/12.
 */
public class LintTP610TwoSumDifferenceEqualsToTarget {
    public class Solution {
        /*
         * @param nums an array of Integer
         * @param target an integer
         * @return [index1 + 1, index2 + 1] (index1 < index2)
         */
        public int[] twoSum7(int[] nums, int target) {
            int[] ans = new int[2];
            if (nums == null || nums.length <= 1) {
                return ans;
            }
            Arrays.sort(nums);
            int lo = 0;
            int hi = 1;
            while(lo < hi) {
                int diff = nums[hi] - nums[lo];
                if (diff > target) {
                    lo--;
                } else if (diff < target) {
                    hi++;
                } else {
                    ans[0] = lo + 1;
                    ans[1] = hi + 1;
                    return ans;
                }
            }
            return ans;
        }
    }
    /**
     * 9/11/2017 OneTimeAC
     */
    public class SolutionHashMap {
        public int[] twoSum7(int[] nums, int target) {
            // write your code here
            if (nums == null || nums.length < 2) {
                return new int[2];
            }
            HashMap<Integer, Integer> hm = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (hm.containsKey(nums[i])) {
                    int[] ans = new int[2];
                    ans[0] = hm.get(nums[i]) + 1;
                    ans[1] = i + 1;
                    return ans;
                }
                hm.put(nums[i] + target, i);
                hm.put(nums[i] - target, i);
            }
            return new int[2];
        }
    }
}
