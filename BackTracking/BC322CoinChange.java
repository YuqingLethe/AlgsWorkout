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

    /**
     * 2026 Topdown DP 每次都可以运用所有的coin，并不是subtract问题，没必要传入n或者n-1.
     * 但是我们每次都需要遍历amount - coins[i],从而选择subset。
     * 基本抄的答案
     */
    class TopDown_dp_2026 {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0 || coins.length == 0) {
                return 0;
            }
            int[] memo = new int[amount];
            return findChange(coins, amount, memo);
        }

        private int findChange(int[] coins, int amount, int[] memo) {
            System.out.println("amount=" + amount);
            if (amount < 0) {
                return -1;
            }
            if (amount == 0) {
                return 0;
            }
            if (memo[amount - 1] != 0) {
                return memo[amount - 1];
            }
            Integer min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i ++) {
                int nextMin = findChange(coins, amount - coins[i], memo);
                if (nextMin >= 0) { // 这里增加&& res < min可以减少条件
                    min = Math.min(min, nextMin) + 1;
                }

            }
            memo[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return memo[amount - 1];
        }
    }
    public static void main(String[] args) {
        int[] coins = {186,419,83,408};

    }
}


