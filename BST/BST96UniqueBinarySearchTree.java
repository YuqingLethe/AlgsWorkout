package BST;

public class BST96UniqueBinarySearchTree {
    /**
     * Apirl 2022 炒答案
     * time complexity is O(N^2)
     * Diff between Dynamic Programming and Brute Force
     * https://leetcode.com/problems/unique-binary-search-trees/discuss/1565543/C%2B%2BPython-5-Easy-Solutions-w-Explanation-or-Optimization-from-Brute-Force-to-DP-to-Catalan-O(N)
     * The only change is every time we calculate the result for numTrees(i), we store the result in dp[i] and only then return it.
     * After that, each time we encounter dp[i] that's already calculated, we can directly return the result.
     * This way, we won't solve for the same numTrees(i) multiple times.
     *
     */
    class DP2022April {
        // The approach and code will be very similar. The only change is every time we calculate the result for numTrees(i), we store the result in dp[i] and only then return it. After that, each time we encounter dp[i] that's already calculated, we can directly return the result. This way, we won't solve for the same numTrees(i) multiple times.
        int[] dp = new int[20];
        public int numTrees(int n) {
            if (n <= 0) {
                return 1;
            }
            if (dp[n] != 0) {

                return dp[n];
            }
            for (int i = 1; i <= n; i++) {
                dp[n] += numTrees(i - 1) * numTrees(n-i);
            }
            return dp[n];
        }
    }


    class Math2022April {
        public int numTrees(int n) {
            long c = 1;
            for (int i = 0; i < n; ++i) {
                c = c * 2 * (2*i + 1) / (i + 2);
            }
            return (int) c;
        }
    }
}
