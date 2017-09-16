package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/9/16.
 */
public class LintTP461KthSmallestNumbersInUnsortedArray {
    /**
     * 9/16/2017 答案解法, 比我的简单
     */
    public class SolutionSolution {
        public int kthSmallest(int k, int[] nums) {
            if (nums == null || nums.length < k) {
                return -1;
            }
            return quickSort(k - 1, nums, 0, nums.length - 1);
        }

        private int quickSort(int k, int[] nums, int start, int end) {
            if (start == end) { //需要这个么?
                return nums[start];
            }
            int pivot = start + (end - start) / 2;
            int pivotValue = nums[pivot];
            int i = start;
            int j = end;
            while (i <= j) {
                while (i <= j && nums[i] < pivotValue) {
                    i++;
                }
                while (i <= j && nums[j] > pivotValue) {
                    j--;
                }
                if (i <= j) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    i++;
                    j--;
                }
            }

            if (j >= k && j >= start) { //其他情况else就包含了?
                return quickSort(k, nums, start, j);
            } else if (i <= k && i <= end) {
                return quickSort(k, nums, i, end);
            } else {
                return nums[k];
            }
        }
    }
}
