package Array;
/*
 * #228 Summary Ranges
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
import java.util.ArrayList;
import java.util.List;

public class Array228SummaryRanges {
	/**
	 * Not right  Feb 2016  Didn't consider the last element as the single 'range'
	 */
	public static List<String> summaryRangesOld(int[] nums) {
		List<String> result = new ArrayList<String>(); //the output result
		int count = 0; // count the number of consistent integer group
		if (nums == null) {
			result.add(null);
			return result;
		}
		if (nums.length == 1) {
			result.add(Integer.toString(nums[0]));
			return result;
		}
		if (nums.length > 1) {
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i+1] - nums[i] != 1) {
					if (count == 0) {//single number
						result.add(nums[i] + "");
					}
					if (count != 0) {//the end of a consistent number group
						result.add(nums[i] - count + "->" + nums[i]);
						count = 0;
					}
				}
				if (nums[i+1] - nums[i] == 1) {
					if (count == 0) {//the begin of a consistent number group
						count++;
					}
					else //the middle numbers of a consistent number group
						count++;
				}
			}
			result.add(Integer.toString(nums[nums.length - 1]));
		}

		return result;
	}
	/**
	 * Runtime: 0ms Use: 4min then 1/24/2017
	 */
  public static List<String> summaryRangesBySingleAndRanges(int[] nums) {
	  List<String> result = new ArrayList<String>(); //the output result
	  int count = 0; // count the number of consistent integer group
	  for (int i = 0 ; i < nums.length; i++) {
		  if (i < nums.length - 1 && nums[i + 1] - nums[i] == 1) {
			  count = 0;
			  while(i < nums.length - 1 && nums[i + 1] - nums[i] == 1) {
				  i++;
				  count++;
			  }
			  result.add(nums[i - count] + "->" + nums[i]);
		  } else {
			  result.add(nums[i] + "");
		  }
	  }
	  return result;
  }

	/**
	 * Runtime: 0ms  Use: 10min 1/24/2017
	 * if and while statements are same....
	 */
	public static List<String> summaryRangesAddByRanges(int[] nums) {
		List<String> ans = new ArrayList<>();
		int i = 0; //index of the array
		while(i < nums.length) { //add one range each time
			StringBuilder sb = new StringBuilder();
			sb.append(nums[i]);
			if (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
				sb.append("->");
				while(i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) i++;
				sb.append(nums[i]);
			}
			ans.add(sb.toString());
			i++;
		}
		return ans;
	}

	/**
	 * Use: 0ms  Get rid of the duplicate conditions of IF and WHILE
	 * https://discuss.leetcode.com/topic/17151/accepted-java-solution-easy-to-understand/10
	 */
	public static List<String> summaryRangesAddByRanges2(int[] nums) {
		List<String> ans = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int tmp = nums[i];
			while(i < nums.length - 1 && nums[i + 1] == nums[i] + 1) {
				i++;
			}
			if (tmp == nums[i]) ans.add(tmp + "");
			else ans.add(tmp + "->" + nums[i]);
		}
		return ans;
	}
  
  public static void main (String[] args) {
	  int[] x1 = new int[]{3, 4, 5, 7, 8, 355, 253657};
	  int[] x2 = new int[]{3};
	  System.out.println(summaryRangesAddByRanges2(x2));
  }
}