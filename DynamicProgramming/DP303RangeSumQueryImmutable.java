package DynamicProgramming;

/**
 * Created by Administrator on 2016/11/6.
 */
public class DP303RangeSumQueryImmutable {
    /**
     * Runtime:
     * https://discuss.leetcode.com/topic/29194/java-simple-o-n-init-and-o-1-query-solution
     */
    public static class NumArray {
        int[] nums;
        public NumArray(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i - 1];
            }
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            if (i == 0) return nums[j];
            return nums[j] - nums[i - 1];
        }
    }
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
    }
}
