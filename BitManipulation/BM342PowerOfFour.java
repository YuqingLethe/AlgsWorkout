package BitManipulation;

/**
 * Created by Administrator on 2016/11/8.
 */
public class BM342PowerOfFour {
    /**
     * Runtime : 2ms  Use: 10min
     * Forgot how to make a 10101 mask...
     */
    public static boolean isPowerOfFourByBit(int num) {
        if (num == 0) return false;
        return (num&0x55555555) == num && (num&(num - 1)) == 0;
    }

    /**
     * Runtime: 2ms
     * https://discuss.leetcode.com/topic/42956/1-line-java-solution-using-math-log
     */
    public static boolean isPowerOfFourByMath(int num) {
        return (Math.log(num)/Math.log(4))%1 == 0;
    }

    //TODO: USing looping
    public static boolean isPowerOfFourByRecursive(int num) {
        return true;
    }
    public static boolean isPowerOfFourByIterative(int num) {
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isPowerOfFourByMath(1073741824));
    }
}
