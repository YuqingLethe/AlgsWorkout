package BitManipulation;

public class BM190ReverseBits {
    public static int reverseBits(int n) {
        char[] ca = Integer.toBinaryString(n).toCharArray();

        for (int i = 0; i < ca.length/2; i++) {
            char temp = ca[i];
            ca[i] = ca[ca.length - 1- i];
            ca[ca.length - 1 - i] = temp;
        }

        String s = String.valueOf(ca);
        System.out.println(s);
        return Integer.parseInt(s, 2);
    }

    //Good to know.....
    public static int reverseBitsByBuildInFunction (int n) {
        return Integer.reverse(n);
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(4294967295));
    }
}
