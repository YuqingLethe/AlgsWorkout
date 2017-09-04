package Array;

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
	/**
	 * 答案思想, 让left指向第一个0, right指向left后面第一个非0
	 */
	public class SolutionDaan {
		public void moveZeroes(int[] nums) {
			int left = 0;
			int right = 0;
			for (right = 0;  right < nums.length; right++) {
				if (nums[right] != 0) {
					int tmp = nums[left];
					nums[left] = nums[right];
					nums[right] = tmp;
					left++;
				}
			}

		}
	}
	/**
	 * 9/3/2017 自己写的, 过程比较复杂, 答案的简单
	 */
	public class SolutionComplicate {
		public void moveZeroes(int[] nums) {
			// Write your code here
			int firstZero = -1;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 0 && firstZero == -1) {
					firstZero = i;
				} else if (nums[i] != 0 && firstZero != -1) { //不加后半句会fail: [5,4,3,2,1]
					nums[firstZero] = nums[i];
					nums[i] = 0;
					firstZero ++;
				}
			}
		}
	}
	
	public static void main (String[] args) {
		int[] nums = new int[]{1,3,0,4,99,0};
		moveZeroes(nums);
	}
}
