package BinarySearch;

/**
 * Created by Administrator on 2016/12/30.
 */
public class Binary153FindMinimumInRotatedSortedArray {
    //Only for bug tests
    public static int findMinFailedForBugTests(int[] nums) {
        int start = 0, end = nums.length - 1;
        if (nums[start] <= nums[end]) return nums[start];

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == 0) {
                if ( nums[mid] > nums[mid + 1])
                    return nums[mid+1];
                else
                    mid ++;
            }
            if (nums[mid - 1] < nums[mid]) {
                if (nums[start] <= nums[mid]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                return nums[mid];
            }
        }
        return -1;
    }

    /**
     * Runtime: 1ms  Use: 2min 1/2/2017
     */
    public static int findMin2(int[] nums) {
        int start = 0, end = nums.length - 1;
        if (nums[start] <= nums[end]) return nums[start];
        while(start + 1 != end) {
            int mid = start + (end - start)/2;
            if (nums[start] < nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] > nums[end])
            return nums[end];
        else
            return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2};
        int[] nums2 = {4, 5, 6, 1, 2, 3};
        int[] nums3 = {3,1, 2};
        int[] nums4 = {2,3,1};
        System.out.println(findMin2(nums3));
    }
}
