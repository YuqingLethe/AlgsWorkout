package DynamicProgramming;

/**
 * You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
 *
 * Return the sum of all subarray ranges of nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [2], range = 2 - 2 = 0
 * [3], range = 3 - 3 = 0
 * [1,2], range = 2 - 1 = 1
 * [2,3], range = 3 - 2 = 1
 * [1,2,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
 *
 * Example 2:
 *
 * Input: nums = [1,3,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [3], range = 3 - 3 = 0
 * [3], range = 3 - 3 = 0
 * [1,3], range = 3 - 1 = 2
 * [3,3], range = 3 - 3 = 0
 * [1,3,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
 *
 * Example 3:
 *
 * Input: nums = [4,-2,-3,4,1]
 * Output: 59
 * Explanation: The sum of all subarray ranges of nums is 59.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 */
public class DP2104SumOfSubarrayRanges {
    /**
     * Sep 2022 Crib answers
     * https://leetcode.com/problems/sum-of-subarray-ranges/discuss/1624222/JavaC%2B%2BPython-O(n)-solution-detailed-explanation
     * 由于遍历从左到右, 之前的值可以直接拿来用在下一步骤用, 不需要通过cache存储
     */
    static class DP_AccumulatedComparison {
        public long subArrayRanges(int[] nums) {
            long result = 0;

            // Get max and min of range from i to j, calculate result at same time.
            for (int i = 0; i < nums.length; ++i) {
                int maxFromI = nums[i]; int minFromI = nums[i];
                for (int j = i; j < nums.length; ++j) {
                    maxFromI = Math.max(maxFromI, nums[j]); //看了提示也没想到这么做
                    minFromI = Math.min(minFromI, nums[j]);
                    result += maxFromI - minFromI;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] arr;
        long ans;

        arr = new int[]{1,3,3};
        printResult(1, arr, 4);
        arr = new int[]{1,2,3};
        printResult(2, arr, 4);
        arr = new int[]{4,-2,-3,4,1};
        printResult(3, arr, 59);
    }
    private static void printResult (int test, int[] input, int expect) {
        DP_AccumulatedComparison solution = new DP_AccumulatedComparison();

        long ans = solution.subArrayRanges(input);
        System.out.println("Answer: " + ans + " Test Case " + test);
        System.out.println("Expect: " + expect);
    }
}
