package happynumber;
/*
 * #228 Summary Ranges
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
import java.util.ArrayList;
import java.util.List;

public class Array228SummaryRanges {
  public static List<String> summaryRanges(int[] nums) {
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
  
  public static void main (String[] args) {
	  int[] x = new int[]{3, 4, 5, 7, 8, 355, 253657};
	  System.out.println(summaryRanges(x));
  }
}