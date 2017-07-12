package LintCode.Binary.TwoPointers;

public class LintTP608TwoSumInputArrayIsSorted {
    /**
     * 2017/7/11.
     */
    public class Solution {
        public int[] twoSum(int[] nums, int target) {
            // write your code here
            int lo = 0;
            int hi = nums.length - 1;
            while(lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum == target) {
                    int[] result = {lo + 1, hi + 1};
                    return result;
                } else if (sum < target) {
                    lo++;
                } else { //用了else没必要用continue啦
                    hi--;
                }
            }
            return new int[2];
        }
    }
}
