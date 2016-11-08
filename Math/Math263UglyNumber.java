package Math;

public class Math263UglyNumber {
    /**
     * Runtime: 2ms  Use: 7min 11/8/2016
     */
    private static boolean isUgly(int num) {
        if (num < 1) return false;
        if (num == 1) return true;
        while(num%5 == 0) num /= 5;
        while(num%3 == 0) num /= 3;
        while(num%2 == 0) num /= 2;
        return num > 1 ? false : true;
    }
    public static void main(String[] args) {
        System.out.println(0%5);
        System.out.println(isUgly(50));
    }
}
