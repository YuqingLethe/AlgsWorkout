package happynumber;

public class Array88MergeSortedArray {
	public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        while( i < m && j < n) { //finish the shorter array
        	if (nums1[i] < nums2[j]) {
        		result[k ++] = nums1[i ++];
        	} else {
        		result[k ++] = nums2[j ++];
        	}
        }
        if (i < m) {//find the longer array and finish remaining
        	while (i < m ) {
        		result[k ++] = nums1[i ++];
        	}
        } else {
        	while (j < n)
        		result[k ++] = nums2[j ++];
        }
        nums1 = result;
        return nums1;
    }
	public static void main (String[] args) {
		int m = 0, n = 1;
		int[] nums1 = new int[]{0};
		int[] nums2 = new int[]{1};
		int[] result = new int[m + n];
		result = merge(nums1, m, nums2, n);
		for (int x : result)
			System.out.print(x + " ");
	}

}
