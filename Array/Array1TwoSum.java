package Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Array1TwoSum {
	public static int[] sortAndFind(int[] nums, int target) {
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

	public static int[] onePass2020(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();


		int i = 0;
		int j = i + 1;
		while (i < nums.length) {
			int complement = nums[i];
			if (map.containsKey(complement) && map.get(complement) != i) {
				int[] result = new int[2];
				result[0] = map.get(complement);
				result[1] = i;
				return result;
			} else {
				map.put(target - nums[i], i);
				i ++;
			}
		}
		map.forEach((key, value) -> System.out.println(key + ":" + value));
		throw new IllegalArgumentException("No two sum solution");
	}

	public static int[] twoPassMap2020(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; ++ i) {
			map.put(nums[i], i);
		}
		//Output Map:
		map.forEach((key, value) -> System.out.println(key + ":" + value));

		int j = 0; //index of the second number
		while (j < nums.length) {
			int complement = target - nums[j];
			Boolean find = map.containsKey(complement);
			if (find && map.get(complement) != j) {
				int[] result = new int[]{j, map.get(target - nums[j]), };
				return result;
			} else {
				j ++;
			}
		}
		throw new IllegalArgumentException("No two sun found");
	}

	public static void main(String[] args) {
		int[] result1 = new int[2];
		result1 = onePass2020(new int[]{-5, -4, -4, -90}, -8);
		System.out.println("Result is " + result1[0] + ", " + result1[1]);

		result1 = twoPassMap2020(new int[]{3,2,4}, 6);
		System.out.println("Result is " + result1[0] + ", " + result1[1]);

		result1 = sortAndFind(new int[]{3,3,2,2,2}, 6);
		System.out.println("Result is " + result1[0] + ", " + result1[1]);
	}
}
