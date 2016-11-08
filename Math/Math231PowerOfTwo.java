package Math;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Math231PowerOfTwo {
    /**
     * Not work. Math.log()   11/7/2016
     */
    public static boolean isPowerOfTwoByLog(int n) {
        double num = Math.log((double) n)/Math.log(2.0);
        System.out.println(num);
        String numString = Double.toString(num);
        for (int i = 0; i < numString.length(); i++) {
            if (!Character.isDigit((numString.charAt(i)))
                && i == numString.length() - 2
                && numString.charAt(i + 1) == '0') {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Try the math, see hints in CSDN
     * https://discuss.leetcode.com/topic/47195/4-different-ways-to-solve-iterative-recursive-bit-operation-math
     */
    public static boolean isPowerOfTwoByMath(int n) {
        return true;
    }

    /**
     * Runtime: 2ms Use 5min  11/7/2016
     */
    public static boolean isPowerOfTwoByBit(int n) {
        if (n <= 0) return false;
        return (n&(n- 1)) == 0 ? true : false;
    }

    /**
     * Runtime: 2ms 11/8/2016
     * The return statement is brilliant
     * https://discuss.leetcode.com/topic/47195/4-different-ways-to-solve-iterative-recursive-bit-operation-math
     */
    public static boolean isPowerOfTwoByIterative(int n) {
        if (n == 0) return false;
        while(n%2 == 0) {
            n /= 2;
        }
        return n == 1;

    }

    /**
     * Runtime: 2ms Use: 2min 11/8/2016
     * Recursive
     */
    public static boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        return isPowerOfTwo(n/2) && n%2 == 0;
    }

    public static void main(String[] args) {
        System.out.println(4&3);
        System.out.println(isPowerOfTwo(536870912));
    }
}
