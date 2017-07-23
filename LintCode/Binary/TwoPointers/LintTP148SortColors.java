package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintTP148SortColors {
    /**
     * z自己写的一遍ac  2017/7/23
     */
    class Solution {

        public void sortColors(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }
            int divide = twoPointers(nums, 0, nums.length - 1, 2);
            divide = twoPointers(nums, 0, divide - 1, 1);
        }
        //

        /**
         * return the division of k and other colors. return the first idx of K
         * quick sort的通用模板, O(n^2)的时间复杂度
         */
        private int twoPointers(int[] nums, int lo, int hi, int target)  {
            while(lo < hi) {
                if (nums[hi] == target) {
                    hi--;
                } else if (nums[lo] != target) {
                    lo++;
                } else if (nums[hi] != target && nums[lo] == target) {
                    int tmp = nums[hi];
                    nums[hi] = nums[lo];
                    nums[lo] = tmp;
                    lo++;
                    hi--;
                }
            }
            if (nums[hi] == target) {
                return hi;
            }
            return hi + 1;
        }
    }
}
