package Array;

import java.util.Arrays;

public class Array1099TwoSumLessThanK {
    class TwoPointers {
        public int twoSumLessThanK(int[] nums, int k) {
            // TODO: 低優先級.想自虐edge case可以不用result這個變量試試
            Arrays.sort(nums);
            int i = 0;
            int j = nums.length - 1;
            int result = 0;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum >= 60) {
                    j --;
                } else {
                    i ++;
                    result = Math.max(result, sum);
                }
            }
            if (i == j) {
                return -1;
            }
            return result;
        }
    }
}
