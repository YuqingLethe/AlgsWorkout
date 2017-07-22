package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/22.
 */
public class LintTP443TwoSumGreaterThanTarget {
    /**
     * 和smallerthan一个思路, 没新意
     */
    public int twoSum2(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int count = 0;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if (sum > target) {
                count += end - start;
                end--;
            } else {
                start++;
            }
        }
        return count;
    }
}
