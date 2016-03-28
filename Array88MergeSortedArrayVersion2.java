package happynumber;

public class Array88MergeSortedArrayVersion2 {
	public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
		//insert from the end of nums1
		int i = m - 1, j = n - 1, k = 0;
		while (i >= 0 && j >= 0) {
			if (nums1[i] > nums2[j]) {
				nums1[n + m - k - 1] = nums1[i--];
				k++;
			} else {
				nums1[n + m - k - 1] = nums2[j--]; 
				k++;
			}	
		}
		//copy the longer remaining array
		while (j >= 0) {
			nums1[n + m - k++ - 1] = nums2[j--];
		}
		return nums1;
	}
	public static void main (String[] args) {
		int m = 3, n =2;
		int[] nums1 = new int[]{3,3,5,0,0};
		int[] nums2 = new int[]{1 , 2};
		int[] result = new int[m + n];
		result = merge(nums1, m, nums2, n);
		for (int x : result)
			System.out.print(x + " ");
	}
}
