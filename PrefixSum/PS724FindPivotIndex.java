package PrefixSum;

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Example 3:
 *
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 */
public class PS724FindPivotIndex {
    /**
     * 笨法子 Aug2022 自己想的.
     */
    class ThreeLoops {
        public int pivotIndex(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            int N = nums.length;
            int[] sumFromLeft = new int[N];
            int[] sumFromRight = new int[N];
            for (int i = 0; i < N; ++i) {
                if (i == 0) {
                    sumFromLeft[i] = nums[i];
                    continue;
                }
                sumFromLeft[i] = sumFromLeft[i - 1] + nums[i];
            }

            for (int i = N - 1; i >= 0; -- i) {
                if (i == N - 1) {
                    sumFromRight[i] = nums[i];
                    continue;
                }
                sumFromRight[i] = sumFromRight[i + 1] + nums[i];
            }

            // Find from left of Pivis
            for (int i = 0; i < N; ++i) {
                if (i == 0 && sumFromRight[1] == 0) {
                    return i;
                }
                if (i == N - 1 && sumFromLeft[N - 2] == 0) {
                    return N - 1;
                }
                if (i < N - 1 && i > 0 && sumFromLeft[i - 1] == sumFromRight[i + 1]) { //注意这里忘记i的范围了. 如果前面不符合要求的话, N==1的时候也会落在这里
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * Crib answer, Aug 2022
     */
    class PreSum {
        public int pivotIndex(int[] nums) {
            int sum = 0, leftSum = 0;
            for (int x : nums) {
                sum += x;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (leftSum == sum - leftSum - nums[i]) {
                    return i;
                }
                leftSum += nums[i];
            }
            return -1;
        }
    }
}
