package LintCode.Binary.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 Find all unique triplets in the array which gives the sum of zero.
 Notice
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.
 */
public class LintTP57ThreeSum {
    /**
     * 2017/7/11 没有单写TwoSum, 稍微优化了一下步骤, 比如nums[lo] > target如此
     */
    public class Solution {

        public ArrayList<ArrayList<Integer>> threeSum(int[] nums) {
            ArrayList<ArrayList<Integer>> results = new ArrayList<>();
            if (nums == null || nums.length < 3) {
                return results;
            }
            Arrays.sort(nums);
            loop:
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i] > 0) {
                    continue;
                }

                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int lo = i + 1;
                int hi = nums.length - 1;
                int target = 0 - nums[i];
//            System.out.println(target);
                while(lo < hi) {
                    if (nums[lo] > target) { //不能用等号  Failed Case: {0,0,0}
                        break; //break只是跳出当前循环
                    }
                    if (lo > i + 1 && nums[lo] == nums[lo - 1]) {
                        lo++;
                        continue;
                    }
                    if (hi < nums.length - 1 && nums[hi] == nums[hi + 1]) {
                        hi--;
                        continue;
                    }
                    int sum = nums[lo] + nums[hi];
                    if (sum > target) {
                        hi--;
                    } else if (sum == target) {
                        ArrayList<Integer> set = new ArrayList<>();
                        set.add(nums[i]);
                        set.add(nums[lo]);
                        set.add(nums[hi]);
                        results.add(set);
                        lo++; //不要忘了移动index啊, 不然会MLE
                        hi--;
                    } else {
                        lo++;
                    }
                }
            }
            return results;
        }
    }
}
