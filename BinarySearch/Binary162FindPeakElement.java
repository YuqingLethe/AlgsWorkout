package BinarySearch;

public class Binary162FindPeakElement {

    /**
     * 帶入了全部下坡和全部上坡的情況, 發現lo和hi沒有corner case可考慮.
     */
    class Solution {
        public int findPeakElement(int[] nums) {
            int lo = 0, hi = nums.length - 1;
            while (lo < hi) {
                int mid = (hi - lo) / 2 + lo;
                if (checkSlopeType(nums, mid) == 0) {
                    return mid;
                } else if (checkSlopeType(nums, mid) == -1) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return lo;
        }

        public int checkSlopeType (int[] nums, int x) {
            // -1  risingSlope
            // 0 peak
            // 1 falling slope
            boolean leftRising = false;
            boolean rightRising = false;
            if ((x == 0) || (nums[x - 1] < nums[x])) {
                leftRising = true;
            }
            if (x == nums.length - 1 || nums[x] < nums[x + 1]) {
                rightRising = true;
            }

            if (leftRising && rightRising) {
                return -1;
            }
            if (leftRising == true && rightRising == false) {
                return 0;
            }
            return 1;
        }


    }
}
