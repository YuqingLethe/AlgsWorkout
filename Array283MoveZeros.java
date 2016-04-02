package happynumber;

public class Array283MoveZeros {
	public static void moveZeroes(int[] nums) {
		
		/*** Method 1 ***/ //Move from the end
		/*
		int k = 0; //the number of zeros been found
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] == 0) {
				//copy the behind elements
				for (int j = i; j < nums.length - k - 1; j++) {
					nums[j] = nums[j + 1];
				}
				k++;
				nums[nums.length - k] = 0;
			}
				
		}
		*/
		/*** Method 2 ***/ //Two pointers
		int k = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0)
				nums[k++] = nums[i];
		}
		while (k < nums.length)
			nums[k++] = 0;
		
		
		//Print it out
		for (int x : nums) 
			System.out.print(x + " ");
        
    }
	
	public static void main (String[] args) {
		int[] nums = new int[]{1,3,0,4,99,0};
		moveZeroes(nums);
	}
}
