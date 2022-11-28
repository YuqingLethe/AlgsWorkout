package BackTracking;

import java.util.HashMap;

public class BC465OptimalAccountBalancing {
    /**
     * Crib answer Nov 2022
     * https://leetcode.com/problems/optimal-account-balancing/solutions/1157154/java-clean-backtracking-solution-with-comments/
     * Time: O(N * 2^N)
     * Space: O(2^N)
     */
    class HM_BackTracking {
        public int minTransfers(int[][] transactions) {
            // Find the final balance of each person
            HashMap<Integer, Integer> hm = new HashMap<>();
            for (int[] txn : transactions) {
                int from = txn[0], to = txn[1], amount=txn[2];
                hm.put(from, hm.getOrDefault(from, 0) - amount);
                hm.put(to, hm.getOrDefault(to, 0) + amount);
            }

            // Build the balance array
            final int N = hm.size();
            int[] balance = new int[N];
            int i = 0;
            for (Integer k : hm.keySet()) { //注意这里必须用i做index写, 然后拿k的value, 因为keyset里面的from的人不一定是从0开始计数的!
                // 我们不关心是谁的balance, 只关心有几笔balance, 因此balance[]里面不存k
                balance[i++] = hm.get(k);
            }

            return getMinTxnTimesFromIdx(0, balance);
        }
        private int getMinTxnTimesFromIdx(int idx, int[] balance) {
            if (idx == balance.length) {
                return 0;
            }
            if (balance[idx] == 0) {
                return getMinTxnTimesFromIdx(idx + 1, balance);
            }

            int res = Integer.MAX_VALUE;
            int currDebt = balance[idx];
// N * (2^N)
            for (int i = idx + 1; i < balance.length; ++i) {
                if (currDebt * balance[i] >= 0) { // Both giving money, no txn between them needed.
                    continue;
                }
                balance[i] += currDebt;
                res = Math.min(res, 1 + getMinTxnTimesFromIdx(idx + 1, balance));
                balance[i] -= currDebt;
            }
            return res;
        }
    }
}
