package DynamicProgramming;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 You can assume that you can always reach the last index.



 Example 1:

 Input: nums = [2,3,1,1,4]
 Output: 2
 Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

 Example 2:

 Input: nums = [2,3,0,1,4]
 Output: 2
 */
public class DP45JumpGameII {
    /**
     * 理解這個可以看下面betterUnderstanding的解法。 其實就是每次jump都有一個下一個jump的範圍， 只要得到nextJumpRange就可以
     *
     * 全部crib answer and solutions July2022
     */
    class LocallyOptimalGreedy {
        public int jump(int[] nums) {
            int jumps = 0;
            int currentJumpEnd = 0;
            int farthest = 0;
            for (int i = 0; i < nums.length; ++i) {
                farthest = Math.max(farthest, i + nums[i]);
                if (i == currentJumpEnd && i != nums.length - 1) {
                    jumps ++;
                    currentJumpEnd = farthest;
                }
                System.out.println("i=" + i + " farthest=" + farthest + " currentJumpEnd=" + currentJumpEnd +" jumps=" + jumps);

            }
            return jumps;
        }
    }

    /**
     * Crib others solution July 2022
     */
    class betterUnderstanding {
        public int jump(int[] nums) {
            int left = 0, right = 0, count = 0;

            // left and right track the window of possible jumps from this current spot
            while (right < nums.length - 1){
                int farthest = 0;
                for(int i = left; i <= right; i++){ // find the farthest in this current range
                    farthest = Math.max(farthest, nums[i] + i);
                }
                left = right + 1; // set pointers for next range
                right = farthest;
                count++;
            }

            return count;

        }
    }

    public static void main (String[] args) {
        int[] arr = {4,5,0,0,0,0,1};
    }
}
