package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/26.
 */
public class LintBinary414DivideTwoIntegers {
    /**
     * 绕开乘法, 只能位移
     */
    public class Solution {

        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            if (divisor == 0) {
                return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            if (dividend == 0) {
                return 0;
            }

            //新颖的方法
            boolean isNeg = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

            long a = Math.abs((long)dividend);
            long b = Math.abs((long)divisor);

            //因为不能用乘法, while的条件只能不断减少divident来避免乘法
            // 所以变动结果范围只能用位运算.
            int result = 0;
            while(a >= b) {
                int shift = 0;
                long div = b << shift;
                while(a >= div) {
                    shift++;
                    div = b << shift;
                }
                div = b << (shift - 1);
                a = a - div;
                result += 1 << (shift - 1);
            }
            return isNeg ? -result : result;
        }
    }
}
