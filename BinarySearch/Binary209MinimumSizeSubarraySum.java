package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/2.
 */
public class Binary209MinimumSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        Arrays.sort(nums);
        //Find the subarray < s
        int[] ar = findSubarray(s, nums);
        for(int x : ar) {
            System.out.print(x + " ");
        }
        System.out.println();

        if (ar[ar.length - 1] == s) return 1;
        else
            return 1 + findMinimumAdd(ar, s);


    }
    private static int[] findSubarray(int s, int[] nums) {
        int start = 0, end = nums.length - 1;
        if (nums[end] <= s) return nums;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == s) {
                end = mid;
                start = mid;
                break;
            } else if (nums[mid] > s) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return Arrays.copyOfRange(nums, 0, Math.max(start, end));
    }
    private static int findMinimumAdd(int[] nums, int s) {
        int i = nums.length - 1;
        if (nums.length == 1 && nums[0] != s) return
        if (Arrays.binarySearch(nums, 0, i, s - nums[i]) < 0) {
            return 1 + findMinimumAdd(Arrays.copyOf(nums, i), s - nums[i]);
        } else {
            return 1;
        }
    }
    public static void main(String[] args) {
        int[] nums1 = {1,2,6,8,9,10,11,12};
        int[] nums2 = {1,6,8,9,10};
        int[] nums3 = {1,7,8, 9, 10};
        int[] nums4 = {1,2,3,8};
        System.out.println(minSubArrayLen(7, nums4));
    }
}
