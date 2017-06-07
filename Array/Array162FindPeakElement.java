package Array;

/**
 * Created by Administrator on 2016/12/26.
 */

/**
 A peak element is an element that is greater than its neighbors.
 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 You may imagine that num[-1] = num[n] = -∞.
 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class Array162FindPeakElement {
    /**
     * Runtime: 1ms  Use: 30 min Understand of neighbours 12/26/2016
     */
    public static int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums.length == 1 || i == 0 && nums[i] > nums[i + 1])
                return 0;
            else if (i > 0 && i < nums.length - 1 && nums[i - 1] < nums[i] && nums[i] > nums[i + 1])
                return i;
            else if (i == nums.length - 1 && nums[nums.length - 2] < nums[nums.length - 1])
                return i;
        }
        return -1;
    }

    /**
     * Runtime: 0ms  Use: 1hr 12/26/2016
     * logarithmic complexity
     * Refer: https://discuss.leetcode.com/topic/5848/o-logn-solution-javacode
     */
    public static int findPeakElementByBinaryRecursive(int[] nums) {
        if (nums.length == 1) return 0;
        return isPeak(nums, 0, nums.length - 1);
    }
    private static int isPeak (int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        if (start == end) return start;
        else if (mid == start || mid == end) // end = start + 1
            return nums[start] < nums[end] ? end : start;
        else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
            return mid;
        else if (nums[mid - 1] < nums[mid] && nums[mid] <  nums[mid + 1])
            return isPeak(nums, mid + 1, end);
        else
            return isPeak(nums, start, mid - 1);
    }

    /**
     * Use: 3min 6.5.2017
     */
    public int findPeakElementBinaryTemplate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (nums[lo] > nums[hi]) {
            return lo;
        } else if (nums[lo] < nums[hi]) {
            return hi;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4, 3, 6};
        int[] nums2 = {1, 2};
        int[] nums3 = {3, 2};
        int[] nums4 = {3};
        System.out.println(findPeakElementByBinaryRecursive(nums2));
    }
}
