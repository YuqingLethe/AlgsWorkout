package BinarySearch;

/**
 * Created by Administrator on 2017/6/5.
 */
public class Binary154FindMinimumInRotatedSortedArray {
    /**
     * 主要考察最坏情况能不能想到  6/5/2017
     */
    public int findMin(int[] nums) {
        int rightmost = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < rightmost) {
                return nums[i];
            }
        }
        return rightmost;
    }

    /**
     * 6/5/2017
     */
    public int findMinFakeBinary(int[] num) {
        int lo = 0;
        int hi = num.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (num[mid] == num[hi]) {
                hi--;
            } else if (num[mid] > num[hi]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (num[hi] < num[lo]) {
            return num[hi];
        } else {
            return num[lo];
        }
    }
}
