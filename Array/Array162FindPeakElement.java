package Array;

/**
 * Created by Administrator on 2016/12/26.
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
     * Runtime: 0ms  Use: 1hr
     * logarithmic complexity
     * Refer: https://discuss.leetcode.com/topic/5848/o-logn-solution-javacode
     */
    public static int findPeakElementByBinarySearch(int[] nums) {
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

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4, 3, 6};
        int[] nums2 = {1, 2};
        int[] nums3 = {3, 2};
        int[] nums4 = {3};
        System.out.println(findPeakElementByBinarySearch(nums2));
    }
}
