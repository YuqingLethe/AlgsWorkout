package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/28.
 */
public class LintBinary586SqrtXII {
    /**
     * 2017/7/28 看得答案
     * 得不到准确值竟然是精度问题. 我没想到
     */
    public double sqrt(double x) {
        if (x == 0.0 || x == 1.0) {
            return x;
        }
        double lo = 0;
        double hi = x;
        double eps = 1e-12;

        if (x < 1.0) { //答案写的
            hi = 1.0;
        }

        while (lo + eps < hi) {
            double mid = lo + (hi - lo) / 2;
            if (mid == 0 || mid*mid < x) {
                lo = mid;
            } else if (mid*mid > x) {
                hi = mid;
            } else {
                return mid;
            }
        }
        // if (hi <= x / hi) {
        //     return hi;
        // }
        // if (lo <= x / lo) {
        //     return lo;
        // }
        return lo;
    }
}
