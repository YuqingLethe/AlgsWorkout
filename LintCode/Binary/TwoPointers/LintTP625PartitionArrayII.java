package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/9/12.
 */
public class LintTP625PartitionArrayII {
    /**
     * OnetimeAC 9/12/2017
     */
    public class SolutionTwoWhiles {

        public void partition2(int[] nums, int low, int high) {
            if (nums == null || nums.length < 1) {
                return;
            }

            //Find the dividing of low
            int lo = 0;
            int hi = nums.length - 1;
            while (lo < hi) {
                if (nums[lo] < low) {
                    lo ++;
                    continue;
                }
                if (nums[hi] >= low) {
                    hi --;
                    continue;
                }
                if (nums[lo] >= low) {
                    exchange(nums, lo, hi);
                    lo ++;
                    hi --;
                }
            }
            //Check if lo == hi and lo < low
            if (nums[lo] < low) {
                lo++; //其实如果上面while条件变为(lo <= hi), 下面最后的if加上lo < hi这个条件, 就不需要这个判定了
            }

            //Find the dividing of high
            hi = nums.length - 1;
            while (lo < hi) {
                if (nums[lo] <= high) {
                    lo ++;
                    continue;
                }
                if (nums[hi] > high) {
                    hi --;
                    continue;
                }
                exchange(nums, lo, hi);
                lo ++;
                hi --;
            }

        }

        private void exchange(int[] nums, int i, int j) {
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
    }
    /**
     * 题目应该多练, 需要理解Two Pointers Three secions的应用
     */
    public class SolutionOneWhile {
        public void partition2(int[] nums, int low, int high) {
            // pl divides < low and >= low; pr divides <= high and > high
            if (nums == null || nums.length < 2) {
                return;
            }
            int pl = 0, pr = nums.length - 1;
            int i = 0;
            while (i <= pr) { //这个条件充分展现了对TwoPointersThreeSections的理解
                if (nums[i] < low) {
                    swap(nums, i, pl);
                    pl ++;
                    i++;
                } else if (nums[i] > high) {
                    //Should i move at this moment? NO! 如果pr也就是现在的i位置的数字 < low就分到中间去了
                    swap(nums, i, pr);
                    pr --;
                } else {
                    i++;
                }
            }
        }
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
