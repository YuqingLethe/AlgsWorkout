package happynumber;

public class Array26RemoveDuplicatesFromSortedArray {
	public static int removeDuplicates(int[] nums) {
		int index = 0; //the pointer for the new length
		byte dup = 0; //indicate if the value has been counted duplicate
		if (nums.length == 1) {//if just one element
			return 1;
		}
		for (int i = 0; i < nums.length - 1; i++) {
			if ( i == nums.length - 2) {//if i and i + 1 is the last two in array
				if (nums[i] != nums[i + 1] && dup == 0) {
					nums[index++] = nums[i];
					nums[index++] = nums[i + 1];
				}
				else if (nums[i] != nums[i+1] && dup == 1) {
					nums[index++] = nums[i + 1];
				}
				else if (nums[i] == nums[i+1] && dup == 0) {
					nums[index++] = nums[i];
				}
			} else {
				//i is the first of duplicated i's
				if (nums[i] == nums[i+1] && dup == 0) {
					nums[index++] = nums[i];
					dup = 1;
				}
				//i is the one that different from the one before and after it
				if (nums[i] != nums[i+1] && dup == 0) {
					nums[index++] = nums[i];
					dup = 0;
				}
				//i is the last of duplicated i's
				if (nums[i] != nums[i+1] && dup == 1) {
					dup = 0;
				}
			}
				
		}
		for (int i = 0; i < index; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		
		return index;
	
	}
	public static void main( String[] args) {
		int[] nums = new int[]{1,1,2,3};
		System.out.println(removeDuplicates(nums));
	}
}
