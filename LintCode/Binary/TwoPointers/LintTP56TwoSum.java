package LintCode.Binary.TwoPointers;

import java.util.HashMap;

public class LintTP56TwoSum {

    /**
     * Hash 2017/7/11 反向思考, 存另一个值, 但为什么一定是mp里面的index小呢?
     * 因为这是一个for循环, 只要被储存的肯定比当前i小.
     */
    public class Solution {
        public int[] twoSum(int[] numbers, int target) {
            // write your code here
            HashMap<Integer, Integer> mp = new HashMap<>();
            for (int i = 0; i < numbers.length; i++) {
                if (mp.containsKey(numbers[i])) {
                    int[] result = {mp.get(numbers[i]) + 1, i + 1};
                    return result;
                }
                mp.put(target - numbers[i], i);
            }
            int[] result = {};
            return result;
        }
    }
    /**
     * 9/3/2017 比较直白的方法
     */
    public class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                return new int[2];
            }
            HashMap<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                index.put(nums[i], i);
            }

            int[] ans = new int[2];
            for (int i = 0; i < nums.length; i++) {
                int theOther = target - nums[i];
                if (index.containsKey(theOther) && index.get(theOther) != i) {
                    int theOtherIdx = index.get(theOther);
                    ans[0] = Math.min(theOtherIdx, i) + 1;
                    ans[1] = Math.max(theOtherIdx, i) + 1;
                }
            }
            return ans;
        }
    }
}
