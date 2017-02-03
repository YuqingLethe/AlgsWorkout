package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/2.
 */
public class Binary209MinimumSizeSubarraySum {
    /**
     * O(n)
     *
     */
    public static int minSubArrayLen2(int s, int[] nums) {
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while (j < nums.length) {
            while (sum < s && j < nums.length) sum += nums[j++];
            System.out.println("after the first while, sum = " + sum);
            if(sum>=s) {
                while (sum >= s && i < j) sum -= nums[i++];
                System.out.println("after the second while, sum = " + sum);
                min = Math.min(min, j - i + 1);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }




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
        if (nums.length == 1 && nums[0] != s) return -1;
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
        int[] nums4 = {1,2,3,5};
        System.out.println(minSubArrayLen2(7, nums4));
    }
}
