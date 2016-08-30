package Array;

import java.util.Arrays;

public class Array1TwoSum {
	public static int[] twoSum(int[] nums, int target) {
       int[] nums2 = nums.clone();
       Arrays.sort(nums2);
       int min = 0, max = nums2.length - 1;
       while (nums2[min] + nums2[max] != target) {
    	   if (nums2[min] + nums2[max] > target) {
    		   max--;
    	   }
    	   if (nums2[min] + nums2[max] < target) {
    		   min++;
    	   }
       }
       System.out.println(nums2[min] + "," + nums2[max]);
       //Find the indices of the two values
       int[] result = new int[2];
       int k = 0;
       for ( int i = 0; i < nums.length; i++) {
    	   if (nums[i] == nums2[min] || nums[i] == nums2[max]) {
    		   result[k ++] = i;
    	   }
       }
       return result;
    }
	public static void main(String[] args) {
		int[] nums = new int[]{-5, -4, -4, -90};
		int target = -8;
		int[] result = new int[2];
		result = twoSum(nums, target);
		System.out.println("Result is " + result[0] + ", " + result[1]);
	}
}
