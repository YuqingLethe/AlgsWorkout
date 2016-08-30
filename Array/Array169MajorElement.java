package Array;

import java.util.Arrays;

public class Array169MajorElement {
	public static int majorityElement(int[] nums) {
		if ( nums.length == 1) return nums[0];
		Arrays.sort(nums); //natual sort the array;
		int count = 1; //to count the number of appearance of each element
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i])
				count++;
			if (nums[i - 1]!= nums[i] && count < (nums.length + 1) / 2)
				count = 1;
			if (count >= (nums.length + 1) / 2)
				return nums[i - 1];
		}
		return 0;
    }
	public static void main(String args[]) {
		int[] array=new int[]{4,5};
		System.out.println(majorityElement(array));	
	}

}
