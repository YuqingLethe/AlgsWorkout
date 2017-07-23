package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintTP148SortColors {
    /**
     * 自己写的一遍ac  2017/7/23
     */
    class SolutionQuickSort {

        public void sortColors(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }
            int divide = twoPointers(nums, 0, nums.length - 1, 2);
            divide = twoPointers(nums, 0, divide - 1, 1);
        }
        //

        /**
         * return the division of k and other colors. return the first idx of K
         * quick sort的通用模板, O(n^2)的时间复杂度
         */
        private int twoPointers(int[] nums, int lo, int hi, int target)  {
            while(lo < hi) {
                if (nums[hi] == target) {
                    hi--;
                } else if (nums[lo] != target) {
                    lo++;
                } else if (nums[hi] != target && nums[lo] == target) {
                    int tmp = nums[hi];
                    nums[hi] = nums[lo];
                    nums[lo] = tmp;
                    lo++;
                    hi--;
                }
            }
            if (nums[hi] == target) {
                return hi;
            }
            return hi + 1;
        }
    }


    /**
     * 过程打印
     * 答案的方法, 一次分3格
     * 2017/7/23
     */
    class Solution {
        public void sortColors(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }
            int lo = 0;
            int traverse =  0;
            int hi = nums.length - 1;
            while (traverse <= hi) {
                if (nums[traverse] == 2) {
                    swap(nums, traverse, hi);
                    hi --;
                } else if (nums[traverse] == 0) {
                    swap(nums, traverse, lo);
                    lo ++;
                    traverse ++; //为什么这里traverse也要++
                } else  {
                    traverse ++;
                }
//            print(nums, traverse);
            }
        }
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        private void print(int[] nums, int traverse) {
            for (int i = 0; i < nums.length; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println("   traverse=" + traverse);
        }
    }
}
