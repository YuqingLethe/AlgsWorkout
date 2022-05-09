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
    /**
     * 2017/7/28 分了大类, 确定了nums[mid]在左右半截之后, 都有两种情况可以让指针移动,
     * 只写一种就TLE了
     * Failed: [1,3,5], 5
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = nums.length - 1;
        int last = nums[nums.length - 1];
        if (last == target) { //Failed: [1,3,5], 5  也可以放后面
            return nums.length - 1;
        }
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > last) {
                if (nums[mid] < target || target < last) {
                    lo = mid;
                } else {
                    hi = mid;
                }

            } else{
                if (nums[mid] > target || target > last) {
                    hi = mid;
                } else {
                    lo = mid;
                }

            }
        }
        if (nums[hi] == target) {
            return hi;
        }
        if (nums[lo] == target) {
            return lo;
        }
        return -1;

    }

}
