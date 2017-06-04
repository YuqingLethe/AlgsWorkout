package BinarySearch;

/**
 * Created by Administrator on 2017/6/4.
 */
public class Binary33SearchInRotatedSortedArray {
    /**
     * 还是自己的算法, 并非九章的  2017/6/4.
     * LintCode62答案很值得研究
     */public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 <  hi) {
            int mid = lo + (hi - lo) / 2;
            if (target >= nums[0]) {
                if (nums[mid] < nums[0] || nums[mid] > target) {
                    hi = mid;
                } else if (nums[mid] <= target) {
                    lo = mid;
                }
            } else {
                if (nums[mid] >= nums[0] || nums[mid] < target) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
        }
        if (nums[lo] == target) {
            return lo;
        } else if (nums[hi] == target) {
            return hi;
        } else {
            return -1;
        }
    }
}
