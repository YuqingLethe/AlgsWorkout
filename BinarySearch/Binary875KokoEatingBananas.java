package BinarySearch;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 */
public class Binary875KokoEatingBananas {
    /**
     * 有想到优化一下最大最小值， 但没想到binary search
     * August 2022基本自己做的。
     */
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            // Get the leftmost speed
            int N = piles.length;
            int sum = 0;
            int max = 0;
            for (int k : piles) {
                sum += k;
                max = Math.max(max, k);
            }
            // int left = sum/h; //注意这里有可能为0， 应该变成最小为1。 BUG
            int left = sum > h ? sum / h : 1;

            int right = max;
            while (left < right) {
                int mid = left + (right - left)/2;

                int currHours = getHourSpent(piles, mid);
                // BUG 完全没想到， 即便currHours=h也有可能不是最优解。要找的是最小的。 Binary Search一定记得dup存在
                // if (currHours == h) {
                //     return currHours;
                // } else if (currHours < h) {
                //     right = mid;
                // } else {
                if (currHours <= h) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        private int getHourSpent(int[] piles, int speed) {

            int result = 0;
            for (int p : piles) {
                result += p/speed;
                if (p%speed != 0) {
                    result  += 1;
                }
            }
            return result;
        }
    }

    public static void main (String[] args) {
        // Test case 1
        // [312884470]
        /// 968709470
    }
}
