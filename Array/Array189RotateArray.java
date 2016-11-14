package Array;

public class Array189RotateArray {

	public void rotate (int[] nums, int k) {
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
	}

	/**
	 * Runtime: Time exceed    11/13/2016
	 */
	public int[] rotateBruteForce (int[] nums, int k) {
		if (nums.length > 1 && k != 0) {
			for (int i = 0; i < k; i++) {
				int tmp = nums[nums.length - 1];
				for (int j = nums.length - 1; j > 0; j--) {
					nums[j] = nums[j - 1];
				}
				nums[0] = tmp;
			}
		}
		return nums;
	}

	/**
	 * Runtime: 2ms   See
	 * https://leetcode.com/problems/rotate-array/
	 */
	public int[] rotateExtraArray(int[] nums, int k) {
		int[] extra = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			extra[( k + i)%nums.length] = nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = extra[i];
		}
		return nums;
	}

	/**
	 * Runtime: 1ms  Use 1hr  11/13/2016
	 * Pay attention of the case when k > len; And use mod wisely
	 */
	public int[] rotateCyclicReplacement(int[] nums, int k) {
		int len = nums.length;
		k = k%nums.length;
		int time = 0; //count the exchanging time
		for (int startPosition = 0; time < len; startPosition++) {
			int i = startPosition;
			int replaced = nums[(len - k + i)%len];
			do {
				int tmpValue = nums[i];
				nums[i] = replaced;
				replaced = tmpValue;
				i = (i + k)%nums.length;
				++time;
			}while(startPosition != i);
		}
		return nums;
	}

	/**
	 * Runtime: 1ms  Use: 30min 11/13/2016
	 */
	public int[] rotateByReverse(int[] nums, int k) {
		int len = nums.length;
		k = k%len;
		nums = reverseArray(nums, 0, len - 1);
		nums = reverseArray(nums, 0, k - 1);
		nums = reverseArray(nums, k, len - 1);
		return nums;
	}
	//Don't need to use return array. Can make it void
	private int[] reverseArray(int[] nums, int start, int end) {
		for (int i = start; i < (start + end + 1)/2; i++) {
			int tmp = nums[i];
			nums[i] = nums[end - i + start];
			nums[end - i + start] = tmp;
		}
		return nums;
	}


	public static void main (String[] args) {
		System.out.println(0%3);
		int[] nums = new int[] {1, 2, 3, 4};
		Array189RotateArray ra = new Array189RotateArray();
		int [] x = ra.rotateByReverse(nums, 2);
		for (int value: x)
		System.out.print(value + " ");

	}
}
