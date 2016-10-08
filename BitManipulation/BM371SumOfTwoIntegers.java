package BitManipulation;

public class BM371SumOfTwoIntegers {
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
    public static void main(String[] args) {
        int a = 2, b = 3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        int c = getSumByIntersection(a, b);
        System.out.println(Integer.toBinaryString(c) + " = " + c);
    }
}
