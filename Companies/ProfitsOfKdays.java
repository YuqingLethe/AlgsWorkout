package Companies;

import java.util.*;

/**
 * Zoom info 20261021
 * profits[i] is a store's profit on day i. Profit can be negative.
 * A shift is k days long.
 *
 * Pick one contiguous range of days whose length is a multiple of k
 * (so k days, or 2k days, or 3k days, ...) and return the largest
 * total profit you can get.
 *
 * Rules:
 *   - You must pick at least k days.
 *   - The answer can be negative.
 *
 * Constraints:
 *   - 1 <= k <= profits.length
 *
 * Example 1:
 *   Input:  profits = {1, 2}, k = 1
 *   Output: 3
 *   Length must be a multiple of 1, so any range works. Take both days.
 *
 * Example 2:
 *   Input:  profits = {-1, -2, -3, -4, -5}, k = 4
 *   Output: -10
 *   Length must be 4 (8 is too long). Only two ranges are legal:
 *     {-1, -2, -3, -4} = -10   <-- best
 *     {-2, -3, -4, -5} = -14
 *   Every day loses money, but you still have to pick one range.
 *
 * Example 3:
 *   Input:  profits = {-5, 1, 2, -3, 4}, k = 2
 *   Output: 4
 *   Length must be 2 or 4.
 *     {-5, 1}         = -4
 *     {1, 2}          =  3
 *     {2, -3}         = -1
 *     {-3, 4}         =  1
 *     {-5, 1, 2, -3}  = -5
 *     {1, 2, -3, 4}   =  4   <-- best
 */
public class ProfitsOfKdays {

        private int maxProfit;

        public int getTotalProfit(int[] profits, int k) {
            maxProfit = Integer.MIN_VALUE;
            for (int i = 1; i * k < profits.length; i ++) {
                helper(profits, i*k);
            }
            return maxProfit;
        }

        private void helper(int[] sumArray, int k) {
            if (sumArray == null || sumArray.length == 0 || k > sumArray.length) {
                throw new IllegalArgumentException("Invalid argument");
            }

            int n = sumArray.length;
            for (int i = 0; i < n; i ++) {
                int sum = 0;
                if (i + k > n) {
                    continue;
                }
                for (int j = i; j < i + k; j ++) { //01234
                    sum += sumArray[j];
                }
                System.out.println("Curr sum=" + sum + " k=" + k);
                maxProfit = Math.max(maxProfit, sum);
            }
        }

        public static void main(String[] args ) {
            int[] profits = {-5, 1, 2, -3, 4};
            ProfitsOfKdays p = new ProfitsOfKdays();
            int maxTotal = p.getTotalProfit(profits, 2);
            System.out.println(maxTotal);
        }


}
