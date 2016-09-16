package BitManipulation;

public class BM191NumberOf1Bits {
    //Runtime: 2ms Use: 40 9/16/2016
    //https://discuss.leetcode.com/topic/57007/java-tle-question/2
    public static int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result += n&1;
            n = n>>> 1;
        }
        return result;
    }

    //Runtime: 4ms Use: 10min 9/16/2016
    public static int hammingWeightByArray(int n) {
        char[] ca = Integer.toBinaryString(n).toCharArray();

        int result = 0;
        for (int i = 0; i < ca.length; i++) {
            System.out.print(ca[i] + " ");
            if (ca[i] == '1') {
                result++;
            }
        }
        return result;
    }
    //Can also use %2 but only for positive integers
    public static int hammingWeightByDivision(int n) {
        int result = 0;
        while (n == 1 || n/2 != 0){
            if (n%2 == 1) {
                result++;
            }
            n = n/2;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(1 / 2);
        System.out.println(hammingWeightByDivision(  214748364));
    }
}
