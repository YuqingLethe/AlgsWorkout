package BinarySearch;

/**
 * Created by Administrator on 2017/6/5.
 */
public class Binary154FindMinimumInRotatedSortedArray {
    /**
     * 主要考察最坏情况能不能想到  6/5/2017
     */
    public int findMin(int[] nums) {
        int rightmost = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < rightmost) {
                return nums[i];
            }
        }
        return rightmost;
    }

    /**
     * 6/5/2017 7/28/2017
     */
    public int findMinFakeBinary(int[] num) {
        int lo = 0;
        int hi = num.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (num[mid] == num[hi]) {
                hi--;
            } else if (num[mid] > num[hi]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (num[hi] < num[lo]) {
            return num[hi];
        } else {
            return num[lo];
        }
    }

    /**
     * To summarize, this algorithm differs to the classical binary search algorithm in two parts:
     *
     * We use the upper bound of search scope as the reference for the comparison with the pivot element, while in the classical binary search the reference would be the desired value.
     * When the result of comparison is equal (i.e. Case #3), we further move the upper bound, while in the classical binary search normally we would return the value immediately.
     */
    class BestSolution {
        public int findMin(int[] nums) {
            int N = nums.length - 1;
            int start = 0;
            int end = N;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                // System.out.println("start=" + start + " end=" + end);
                // System.out.println("mid = " + mid + " nums[mid]=" + nums[mid]);
                if (nums[mid] < nums[end]) {
                    end = mid;
                } else if (nums[mid] > nums[end]) {
                    start = mid + 1;
                } else {
                    end -= 1;
                }
                // System.out.println("Finish status: start=" + start + " end=" + end);
                // System.out.println("______");

            }
            return nums[start];
        }
    }
}
