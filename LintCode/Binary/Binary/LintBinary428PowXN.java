package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/6/4.
 */
public class LintBinary428PowXN {
    /**
     * 这个题目在leetcode上要求更严格, 看Binary50
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        //忘记了n还可以是负数
        if (n == 0) {
            return 1;
        }
        if (x == 0 && n < 0) {
            return Double.POSITIVE_INFINITY;
        }
        if (n == 1) {
            return x;
        } else if (n == 2) {
            return x * x;
        } else if (n == -1) {
            return 1 / x;
        } else if (n == -2) {
            return 1 / (x * x);
        } else {
            int mid = n / 2;
            return myPow(x, mid) * myPow(x, n - mid);
        }
    }
}
