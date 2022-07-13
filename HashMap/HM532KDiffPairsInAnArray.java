package HashMap;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yuqing on 5/28/22.
 */
public class HM532KDiffPairsInAnArray {
    /**
     * May 2022
     * 忘記了k=0的時候， 這樣就不能用set了 Crib Answer basically
     */
    class Hashmap {
        public int findPairs(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int count = 0;

            for (Integer i : map.keySet()) {
                Integer val = map.get(i);
                if (k > 0 && map.containsKey(i + k)) {
                    count ++;
                } else if (k == 0 && val > 1) {
                    count ++;
                }
                map.put(i, map.get(i) - 1);
            }
            return count;
        }
    }

    /**
     * Crib answers TODO: one more time
     */
    class TwoPointers {
        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);
            int left = 0, right = 1;
            int result = 0;
            while (left < nums.length && right < nums.length) {
                if (left == right || (nums[right] - nums[left]) < k) {
                    right++;
                } else if (nums[right] - nums[left] > k) {
                    left++;
                } else {
                    left++;
                    result++;
                    while (left < nums.length && nums[left] == nums[left - 1]) {
                        left ++;
                    }
                }

            }
            return result;
        }
    }

}
