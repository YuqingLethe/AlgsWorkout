package LintCode.Binary.TwoPointers;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintTP31PartitionArray {
    /**
     * 细节背诵
     * 仿quick sort 最简洁办法, !
     * 2017/7/23
     */
    public int partitionArray2(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
            if (nums[lo] < k) {
                lo ++;
            } else if (nums[hi] >= k) {
                hi --;
            } else {
                int tmp = nums[hi];
                nums[hi] = nums[lo];
                nums[lo] = tmp;
                lo ++;
                hi --;
            }
        }
        return lo;
    }

    /**
     *自己创的quick sort方法, 没有答案的简洁. 答案一个技巧很妙
     * 2017/7/23
     */
    public int partitionArray1(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi) {
            if (nums[lo] < k) {
                lo ++;
            } else if (nums[hi] >= k) {
                hi --;
            } else {
                int tmp = nums[hi];
                nums[hi] = nums[lo];
                nums[lo] = tmp;
                lo ++;
                hi --;
            }
        }
        if (nums[hi] >= k) { //如果用lo <= hi就没有这个问题了
            return hi;
        }
        return hi + 1;
    }
}
