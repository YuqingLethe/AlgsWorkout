package Math;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/24.
 */
public class LintMath51PreviousPermutation {
    /**
     * same with next Permutation 2017/7/24
     */
    public class Solution {

        public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
            if (nums == null || nums.size() <= 1) {
                return nums;
            }
            //find the first one that bigger than its next number
            int len = nums.size();
            int index = -1;
            for (int i = len - 2; i >= 0; i--) {
                if (nums.get(i) > nums.get(i + 1)) {
                    index = i;
                    break;
                }
            }

            // sort the following numbers in decending order
            for (int i = index + 1; i < len - 1; i++) {
                int max = nums.get(i);
                for (int j = i + 1; j < len; j++) {
                    int tmp = nums.get(j);
                    if (max < tmp) {
                        nums.set(i, tmp);
                        nums.set(j, max);
                        max = tmp;
                    }
                }
            }
            if (index == -1) {
                return nums;
            }

            //Find the largest smaller number and swap with the nums[index];
            int secondBigger = index + 1;
            while(nums.get(secondBigger) >= nums.get(index)) { //必须是>=啊
                secondBigger ++;
            }
            int tmp = nums.get(secondBigger);
            nums.set(secondBigger, nums.get(index));
            nums.set(index, tmp);

            return nums;

        }
    }
}
