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

    //Runtime: 2ms  Use: 1min
    public static boolean isPowerOfFourByRecursive(int num) {
        if (num == 1) return true;
        if (num <= 0) return false;
        if (num%4 != 0) return false;
        else return isPowerOfFourByRecursive(num/4);
    }

    //Runtime: 2ms  Use: 1hr to debug....
    public static boolean isPowerOfFourByIterative(int num) {
        if (num == 1) return true;
        if (num < 4 || num <= 0) return false;
        int div = 4;
        while (num > 1) {
            if (num%div != 0){
                return false;
            } else {
                num /= 4;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfFourByRecursive(2));
        System.out.println(isPowerOfFourByIterative(8));
        System.out.println(isPowerOfFourByIterative(-4));
        System.out.println(isPowerOfFourByIterative(1073741824));
    }
}
