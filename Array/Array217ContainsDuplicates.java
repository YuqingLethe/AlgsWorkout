package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        	// 如果改成contains记得还要add！
    	}
    	return false;
         */
    }

	/**
	 * 没想到只要存在一个dup就返回true，以后要认真看题
	 */
	public boolean containsDuplicate2026(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i ++) {
			int curr = nums[i];
			if (set.contains(curr)) {
				set.remove(curr);
			} else {
				set.add(curr);
			}
		}
		if (set.isEmpty()) { //没必要
			return true;
		}
		if (set.size() < nums.length) { //没必要
			return true;
		}

		return false;
	}
	
	public static void main (String[] args) {
		int[] nums = new int[]{1,2,6,3,5,9,-1,-2};
		System.out.println(containsDuplicate(nums));
		
	}
}
