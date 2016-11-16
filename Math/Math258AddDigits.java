package Math;

/**
 * Created by Administrator on 2016/11/6.
 */
public class Math258AddDigits {
    /**
     * Runtime: 2ms  BruteForce  Use: 30min 11/6/2016
     * Time complexity: O(n)
     */
    public static int addDigits(int num) {
        int sum = 0;
        while (sum != 0 || num > 9) {
            sum += num%10;
            num /= 10;
            if (num == 0) {
                num = sum;
                sum = 0;
            }
        }
        return num;
    }

    /**
     * Runtime: 2ms
     */
    public static int addDigitsByNine(int num) {
        return (num%9 == 0 && num != 0) ? 9 : num%9;
    }

    /**
     * Another way to deal with 9, 18, -9, 18
     * https://discuss.leetcode.com/topic/36722/java-o-1-solution-for-positive-and-negative-numbers
     */
    public static int addDigitsAlsoByNineForNegative(int num) {
        if (num >= 0)
            return 1 + (num - 1)%9;
        else
            return -1 + (num + 1)%9;
    }
    public static void main(String[] args) {
        System.out.println(addDigitsAlsoByNineForNegative(-9));
        System.out.println(-37%9);
    }
}
