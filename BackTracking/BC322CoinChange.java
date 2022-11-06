package BackTracking;

import java.util.HashMap;

public class BC322CoinChange {
    /**
     * Nov 2022 Crib answer 20min
     * https://leetcode.com/problems/coin-change/solutions/127438/coin-change/
     * 主要栽倒在没想清楚这个是个任意组合的题目, 最大面值数目最多不一定能凑出答案, 可能会超.
     */
    class BackTracking_DP {
        private int total;
        private int[] coins;
        private HashMap<Integer, Integer> dp; // amount, fewest number of coins
        private int N;
        public int coinChange(int[] coins, int amount) {
            this.N = coins.length;
            this.dp = new HashMap<>();
            this.coins = coins;
            this.total = -1;

            return helper(amount);
        }

        private int helper(int amount) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }
            if (dp.containsKey(amount)) {
                return dp.get(amount);
            }
            int minCoins = Integer.MAX_VALUE;
            for (int i = 0; i < N; ++i) {
                int remainingNumOfCoins = helper(amount - coins[i]);
                if (remainingNumOfCoins != -1) {
                    minCoins = Math.min(minCoins, remainingNumOfCoins + 1);
                }
            }
            if (minCoins == Integer.MAX_VALUE) {
                minCoins = -1;
            }
            dp.put(amount, minCoins);
            return minCoins;
        }
    }
    public static void main(String[] args) {
        int[] coins = {186,419,83,408};

    }
}


