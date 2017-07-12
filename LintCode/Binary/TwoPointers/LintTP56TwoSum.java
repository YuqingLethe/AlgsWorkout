package LintCode.Binary.TwoPointers;

import java.util.HashMap;

public class LintTP56TwoSum {

    /**
     * Hash 2017/7/11
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
}
