package DynamicProgramming;

/**
 * Created by Administrator on 2016/11/6.
 */
public class DP70ClimbingStairs {
    /**
     * Runtime: 0ms 11/6/2016
     * Like house robber, the solutionNumber[i] based on solutionNumber[i - 1]
     */
    public int climbStairsByNSpaces(int n) {
        if (n == 0) return 0;
        int[] answer = new int[n + 1];
        answer[1] = 1;
        if (n == 1) return 1;
        answer[2] = 2;
        if (n == 2) return 2;

        for (int i = 3; i <= n; i++) {
            answer[i] = answer[i - 1] + answer[i - 2];
        }
        return answer[n];
    }

    /**
     * Runtime: 0ms  11/6/2016
     * Optimize the above solution, only keep spaces of i - 1 and i - 2;
     */
    public int climbStairsSaveSpace(int n) {
        if (n == 1) return 1;
        int a = 1, b = 1;
        while(--n > 0) {
            int temp = b;
            b += a;
            a = temp;
            //can also use a = (b += a) - a; to simplify inside
        }
        return b;
    }

    /**
     * Best solution, no if logic
     * https://discuss.leetcode.com/topic/17002/3-4-short-lines-in-every-language
     */
    public int climbStairsSaveSpace2(int n) {
        int a = 1, b = 1;
        while(n-- > 0) {
            a = (b += a) - a;
        }
        return a;
    }

}
