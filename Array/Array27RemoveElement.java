package Array;

public class Array27RemoveElement {
	public static int removeElement(int[] nums, int val) {
		int a = 0; //the pointer of the new array
		for ( int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[a++] = nums[i];
			} else {
				;
			}
		}
		for (int i = 0; i < a; i++) {
			System.out.print(nums[i] + " ");
		}
		
		return a;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{3,2,2,3};
		int val = 3;

		System.out.println(removeElement(nums, val));
	}
}
