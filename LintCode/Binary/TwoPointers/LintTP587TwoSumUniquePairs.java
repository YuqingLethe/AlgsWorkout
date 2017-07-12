package LintCode.Binary.TwoPointers;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/11.
 */
public class LintTP587TwoSumUniquePairs {
    /**
     * 用continue来处理重复比较直观
     */
    public int twoSum6(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        while(i < j) {
            int sum = nums[i] + nums[j];
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            if (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                j--;
                continue;
            }
            if (sum == target)  {
                count++;
                i++;
                j--;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }

        }
        return count;
    }
}
