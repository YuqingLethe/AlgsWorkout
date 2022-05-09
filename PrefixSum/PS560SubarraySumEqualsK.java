package PrefixSum;

import LintCode.Binary.Tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Hint 3: What about storing sum frequencies in a hash table? Will it be useful?
 * Hint 4: sum(i,j)=sum(0,j)-sum(0,i), where sum(i,j) represents the sum of all the elements from index i to j-1.
 *         Can we use this property to optimize it.
 */
public class PS560SubarraySumEqualsK {
    class PrefixSum {
        /* April 2022 Crib from Solution */
        public int getOrDefault(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            int count = 0;
            int currSum = 0;

            for (Integer num : nums) {
                currSum += num;

                // 這裏其實還有一種寫法, 就是在循環前在hash加個(0, 1)作爲第一個點. 這樣如果prefix sum == target,也滿足map.getOrDefault(currSum - k, 0);的情況
                // 看 dummyElementInHashMap()
                if (currSum == k) {
                    count ++; // 如果當前的prefix sum == target, 則++count
                }
                count += map.getOrDefault(currSum - k, 0);

                // 爲什麼這裏不是currSum - k????
                map.put(currSum, map.getOrDefault(currSum, 0) + 1);
            }
            return count;
        }

        public int dummyElementInHashMap(int[] nums, int k) {
            int count = 0, sum = 0;
            HashMap < Integer, Integer > map = new HashMap < > ();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum - k))
                    count += map.get(sum - k);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }

    class CumulativeSum {
        /** April 2022 Crib from Solutions */
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            int[] sums = new int[nums.length + 1];
            sums[0] = 0;

            // Build the sums array
            for (int i = 1; i < nums.length + 1; ++i) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length + 1; ++j) {
                    if (sums[j] - sums[i] == k) {
                        ++count;
                    }
                }
            }
            return count;
        }
    }

    public void main(String[] args) {
        int[] array0 = {1,2,1,2,1}; int k0 = 3;
    }

}
