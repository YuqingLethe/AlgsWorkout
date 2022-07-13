package DynamicProgramming;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

 Return true if you can reach the last index, or false otherwise.


 Example 1:

 Input: nums = [2,3,1,1,4]
 Output: true
 Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

 Example 2:

 Input: nums = [3,2,1,0,4]
 Output: false
 Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
public class DP55JumpGame {
    /**
     * July2022 直接一個test case timeout了
     * 其實這種一開始就應該想到從後往前運算而不是從前望往後
     * self solve 30min
     */
    class Greedy_BackTracking {
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return true;
            }
            for (int i = 0; i <= nums[0]; ++i) {
                if(helper(nums, i)) {
                    return true;
                };
            }
            return false;
        }
        private boolean helper(int[] nums, int curr) {
            if (curr == nums.length - 1) {
                return true;
            } else if (nums[curr] == 0) {
                return false;
            }
            for (int i = 1; i <= nums[curr]; ++i) {
                if (helper(nums, curr + i)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Self solve 15min
     */
    class TwoPointers {
        public boolean canJump(int[] nums) {
            // [4l,2,1,0,4r]
            if (nums == null || nums.length <= 1) {
                return true;
            }
            int left = nums.length - 2, right = nums.length - 1;
            while (left >= 0) {
                int currValue = nums[left];
                int gap = right - left;
                if (currValue >= gap) {
                    if (left == 0) {
                        return true;
                    }
                    right = left;
                    left = right - 1;

                } else {
                    left = left - 1;
                }
            }
            return false;
        }
    }
}
