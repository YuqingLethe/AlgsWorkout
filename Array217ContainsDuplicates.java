package happynumber;

import java.util.Arrays;

public class Array217ContainsDuplicates {
	public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for ( int i = 0; i < nums.length - 1; i++) {
        	if (nums[i] == nums[i + 1]) {
        		return true;
        	}
        }
        return false;
        
        //Should use hashset to do it
        /*
         HashSet<Integer> set = new HashSet<Integer>();
    	for(int i=0;i<nums.length;i++){
        	if(!set.add(nums[i])) return true;
    	}
    	return false;
         */
    }
	
	public static void main (String[] args) {
		int[] nums = new int[]{1,2,6,3,5,9,-1,-2};
		System.out.println(containsDuplicate(nums));
		
	}
}
