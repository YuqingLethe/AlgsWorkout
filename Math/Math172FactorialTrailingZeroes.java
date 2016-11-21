package Math;

public class Math172FactorialTrailingZeroes {
    /**
     * Runtime: 1ms   Recursive method 11/6/2016
     * https://discuss.leetcode.com/topic/6516/my-one-line-solutions-in-3-languages
     */
    public int trailingZeroes(int n) {
        if (n == 0) return 0;
        return n/5 + trailingZeroes(n/5);
    }

    /**
     * Runtime: 1ms  11/20/2016
     * return n/5 + n/25 + n/125 + n/625 + n/3125+...;
     * https://discuss.leetcode.com/topic/6848/my-explanation-of-the-log-n-solution
     * Overflow problem: https://discuss.leetcode.com/topic/6899/failing-test-case-1808548329
     */
    public static int trailingZeroesIterative(int n) {
        long divider = 5;
        int answer = 0;
        while(divider <= n) {
            answer += n/divider;
            divider *= 5;
        }
        /*
        //Can also do this
        for (long i = 5; i <= n; i *= 5) {
            answer += n/i;
        }
        */
        return answer;
    }


    public static void main(String[] args) {
        System.out.println(trailingZeroesIterative(1808548329));
    }
}
