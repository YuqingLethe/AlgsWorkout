package BitManipulation;

public class BM371SumOfTwoIntegers {
    /**
     * Wrong one with very typical errors  201609
     */
    public static int getSumByIntersection(int a, int b) {
        int sum = a|b;
        int increase = a&b;//Mark the digits that should be 2 by adding a & b
        System.out.println(Integer.toBinaryString(increase));
        while(increase > 0) {
            int length = Integer.toBinaryString(increase).length();

            sum &= ~increase; //Clean the bits == 2 to 0
            System.out.println(Integer.toBinaryString(~increase));
            sum = sum |(increase << 1); //Increase the bit to the previous bit
            increase = sum&(increase << 1); //Find the bits should be two
        }
        return sum;
    }

    /**
     * Runtime: 0ms Forgot negative 11092016
     */
    public static int getSum(int a, int b) {
        int sum = a^b;
        int increase = a&b;
        while (increase != 0) {
            int carry = increase << 1;
            increase = carry&sum;
            sum = sum^carry;
        }
        return sum;
    }

    //TODO: can optimize the above algs to reduce space cost https://discuss.leetcode.com/topic/49771/java-simple-easy-understand-solution-with-explanation
    public static void main(String[] args) {
        System.out.println(2^2);
        int a = -2, b = -3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        int c = getSum(a, b);
        System.out.println(Integer.toBinaryString(c) + " = " + c);
    }
}
