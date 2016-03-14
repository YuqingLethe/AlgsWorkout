package happynumber;

public class Array189RotateArray {
	public int[] rotate (int[] nums, int k) {
		/****Method 1 Normal: Use Space O(k%nums.length), Time****/
		if (nums.length > k) {
			int[] rotatenums = new int[nums.length]; //the array to store the rotate part
			//copy the rotated part of array nums
			for (int i = 0; i < k; i++) {
				rotatenums[i] = nums[nums.length - k + i];
			}
			//copy the non rotate part
			int x = 0; //start index of nums
			for (int j = nums.length - k; j < nums.length; j++) {
				rotatenums[j] = nums[x];
				x++;
			}
			nums = rotatenums;
		}
		return nums;
	
	}
	public static void main (String[] args) {
		int[] nums = new int[] {1,2};
		Array189RotateArray ra = new Array189RotateArray();
		int [] x = ra.rotate(nums, 1);
		for (int value: x)
		System.out.print(value + " ");
	}
}
