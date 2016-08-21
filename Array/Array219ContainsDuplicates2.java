package Array;

public class Array219ContainsDuplicates2 {
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums.length == 0) // null array, no duplicates
			return false;
		if (nums.length >= k) {
	        for (int i = 0; i < nums.length - k; i ++) {
	        	for ( int j = i + 1; j < i + k; j ++) {
	        		if (nums[i] == nums[j])
	        			return true;
	        	}
	        }
	        for (int i = nums.length - k + 1; i < nums.length - 1; i ++) {
	        	for (int j = i + 1; j < nums.length; j ++) {
	        		if (nums[i] == nums[j])
	        			return true;
	        	}
	        }
		} else { //k > nums.length
			for ( int i = 0; i < nums.length - 1; i++) {
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[i] == nums[j])
						return true;
				}
			}
		}
        return false;
    }
	public static void main(String[] args) {
		int[] nums = new int[]{3,6,5,4,6,3,3};
		int k = 77;
		System.out.println(containsNearbyDuplicate(nums, k));

	}
}
