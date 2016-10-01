package BitManipulation;

/**
 * 5.6 Write a program to swap odd and even bits in an integer with as few instructions as possible
 * (e.g., bit 1 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on)
 */
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
