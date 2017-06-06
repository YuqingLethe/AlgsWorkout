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

    /**
     * 6/4/2017 template
     */
    public int findMinGeneralTemplate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == nums[hi]) {
                hi--;
            } else if (nums[mid] > nums[hi]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (nums[lo] < nums[hi]) {
            return nums[lo];
        } else {
            return nums[hi];
        }
    }

    /**
     * Find the first element that is smaller than nums[nums.length - 1] 6/4/2017
     * Can also make the rigihtmostValue as target and do it like BinarySearch
     * 前提条件: No Duplicates
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        int lo = 0;
        int rightmostValue = nums[nums.length - 1];
        while (lo < nums.length - 1  && nums[lo] >= rightmostValue) {
            if (lo == nums.length - 2 && nums[lo] > rightmostValue) {//Failed test: 忘记了比如[5,4]的情况
                return rightmostValue;
            }
            int mid = lo + (nums.length - 1 - lo) / 2;
            if (nums[mid] > rightmostValue) { //不能用>=
                lo = mid;
            } else {
                lo++;
            }
        }
        if (nums[lo] < rightmostValue) {
            return nums[lo];
        }
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
