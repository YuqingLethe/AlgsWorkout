package Math;

/**
 * Created by Administrator on 2016/11/6.
 */
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
     * TODO: write this
     * return n/5 + n/25 + n/125 + n/625 + n/3125+...;
     * https://discuss.leetcode.com/topic/6848/my-explanation-of-the-log-n-solution
     */
    public int trailingZeroesIterative(int n) {
        return 1;
    }
}
