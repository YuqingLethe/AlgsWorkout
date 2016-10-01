package BitManipulation;

public class CC150Ch5BM56 {
    private static int swapOddEvenBits (int n) {
     //   return ( (n&0xaaaaaaaa) >> 1 )|( (n&0x55555555) << 1);
        return ( (n&0xaaaaaaaa) >> 1 )|( (n&(0xaaaaaaaa<<1)) << 1);
    }
    public static void main(String[] args) {
        int n = 4;
        System.out.println(Integer.toBinaryString((15&0xaaaaaaaa )>> 1));
        System.out.println(n + " = " + Integer.toBinaryString(n));
        int y = swapOddEvenBits(n);
        System.out.println(y + " = " + Integer.toBinaryString(y));
    }
}
