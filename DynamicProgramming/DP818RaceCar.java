package DynamicProgramming;

/**
 *
 * Consider two general cases for number i with bit_length n.
 *
 * Case 1: i==2^n-1, this case, n is the best way
 * Case 2: 2^(n-1)-1<i<2^n-1, there are two ways to arrive at i:
 * 1. Use n A to arrive at 2^n-1 first, then use R to change the direction, by here there are n+1 operations (n A and one R),
 *    then the remaining is same as dp[2^n-1-i]
 * 2. Use n-1 A to arrive at 2^(n-1)-1 first, then R to change the direction, use m A to go backward,
 *    then use R to change the direction again to move forward, by here there are n-1+2+m=n+m+1 operations (n-1 A, two R, m A) ,
 *    current position is 2^(n-1)-1-(2^m-1)=2^(n-1)-2^m, the remaining operations is same as dp[i-(2^(n-1)-1)+(2^m-1)]=dp[i-2^(n-1)+2^m)].
 * Why dp in this way?
 *
 * I first think the dp way should be:
 *
 * dp[i] = min(n+1+dp[2**n-1-i], n-1+2+dp[i-2**(n-1)+1])
 * But it's wrong, look at the (n-1) A case, we do A for (n-1) times, then do two R, then the situation is the same as dp[i-2**(n-1)+1]. This can be larger than the actual min operations since, there may be redundant R operations, we can combine RR operation with the remaining (2**(n-1)-1) to i path. So we use m to go backward between the two R operations and count the remaining (2^(n-1)-2^m) to i path to include the combining situation.
 *
 * For example:
 *
 * target = 5
 *
 * The right answer should be AARARAA, positions: 0, 1, 3, 3, 2, 2, 3, 5
 * But if we use the above formula, the answer is AA (0->3) RR (make speed at 1 again) AARA (3->5)
 *
 * We can move the last A to the middle of RR, so that we save an operation. That's where the combine can happen.
 * So we do dp by adding m A between the RR and add the # operations for remaining distance.
 */
public class DP818RaceCar {
    /**
     * Nov 2022
     * 比较好的解说 https://leetcode.com/problems/race-car/discussion/comments/1564632 以及crib answer的留言:
     * https://leetcode.com/problems/race-car/solutions/123834/java-c-python-dp-solution/
     */
    class DP_CribAnswer {
        private int[] dp = new int[10001];

        public int racecar(int target) {
            if (dp[target] > 0) {
                return dp[target];
            }
            int n = (int) (Math.log(target) / Math.log(2)) + 1;
            if ((1 << n) - 1 == target) { //加速直达 target==2^n-1, this case, n is the best way
                dp[target] = n;
            } else {
                dp[target] = racecar((1 << n) - 1 - target) + n + 1; //这里换成2倍会报错
                // Use n A to arrive at 2^n-1 first, then use R to change the direction, by here there are n+1 operations (n A and one R), then the remaining is same as dp[2^n-1-i]
                for (int m = 0; m < n - 1; ++m) {
                    dp[target] = Math.min(dp[target], racecar(target - (1 << (n - 1)) + (1 << m)) + n + m + 1);
                }
                // Use n-1 A to arrive at 2^(n-1)-1 first, then R to change the direction, use m A to go backward, then use R to change the direction again to move forward, by here there are n-1+2+m=n+m+1 operations (n-1 A, two R, m A) , current position is 2^(n-1)-1-(2^m-1)=2^(n-1)-2^m, the remaining operations is same as dp[i-(2^(n-1)-1)+(2^m-1)]=dp[i-2^(n-1)+2^m)].
            }
            return dp[target];
        }
    }
    public static void main(String[] args) {
        int target = 5;
        int expect = 7;
    }

}
