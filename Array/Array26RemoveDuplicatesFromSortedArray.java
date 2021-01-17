package Array;

public class Array26RemoveDuplicatesFromSortedArray {
	/**
	 * Runtime: 1 ms, faster than 25.08% of Java online submissions for Remove Duplicates from Sorted Array.
	 * Memory Usage: 40.8 MB, less than 54.68% of Java online submissions for Remove Duplicates from Sorted Array.
	 *
	 * 这个思路只用了两个指针，但实际上是index，i，i+1同时看。是一旦检测到不一样才登记之前的那个。
	 * 其实，我们需要管当前是不是个dup吗？如果不管，就是最优解了。
	 * @param nums
	 * @return
	 */
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
			} else {// 12123334
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

	/**
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
	 * Memory Usage: 40.7 MB, less than 79.72% of Java online submissions for Remove Duplicates from Sorted Array.
	 *
	 * 20210116今天做这个题用了一个小时。一开始不是最优解，二十每次处理新element会把后面的全部前移。增加了问题的复杂度！
	 * 为何这个解法最优？
	 * 1. 读题要仔细，不需要把后面设置成null，只需要返回最终那一部分的长度即可 也就是返回r+1即可。
	 * 2. 每次查找dup一定需要两个指针，左边的supervisor站在dups的首位，右面一直移动直到与当前s不一样
	 * 3. 不需要copy后面整个array，只需要把当前处理的数字（与nums[r]不一样的数字）放在最终那部分即可,
	 * 		因此只需要r++; nums[r] = nums[s]
	 * 4. 这样可以直接跳转到下一个需要处理的值上面，即s = p，s继续从p的位置开始处理，之间都不用管（最难想到的一点）
	 *
	 *
	 * 最优解的逻辑很直白，其实一次遍历就足够了。具体看开头的指针设置
	 * @param nums
	 * @return
	 */
	public int removeDuplicatesBest(int[] nums) {
		// Start 11:43 12:41   57min get the best answer
		if (nums == null) {
			return 0;
		}
		if (nums.length == 1) {
			return nums.length;
		}
		int r = 0; // Final result, index of last element of the result array.
		int v = 1; // Vanguard goes through all elements, always the rightmost
		int s = r; // supervisor, check the noted elements -> All because it is a sorted array!
		for (; v < nums.length; v ++) {
			if (nums[s] == nums[v]) {
				continue;
			}
			r++;
			nums[r] = nums[v];
			s = v;
		}
		return r + 1;
	}


	public static void main( String[] args) {
		int[] nums = new int[]{1,1,2,3};
		System.out.println(removeDuplicates(nums));
	}
}
