package Math;

/**
 * Created by Administrator on 2016/11/6.
 */
public class Fibonacci {
    public static int solution(int A, int B, int N) {
        if (N == 0) return A;
        if (N == 1) return B;

        long gf1 = A, gf2 = B;
        while(N-- > 1) {
            gf1 = (gf2 += gf1) - gf1;
            if (gf1 > 1000000007) gf1 %= 1000000007;
            if (gf2 > 1000000007) gf2 %= 1000000007;
        }
        return (int) gf2;
    }
    public static int solution2(int A, int B, int N) {
        if (N <= 0) return 0;

        int i = N - 1; //index of the loop
        long a = A, b = B, c = 0, d = 1, tmp1,tmp2;
        int divider = 1000000007;

        while (i > 0) {
            if (i % 2 != 0) {
                tmp1 = d * b + c * a;
                tmp2 = d * (b + a) + c * b;
                if (tmp1 > divider) tmp1 %= divider;
                a = tmp1;
                b = tmp2;
            }

            tmp1 = (long) (Math.pow(c, 2) + Math.pow(d, 2));
            tmp2 = d * (2 * c + d);
            if (tmp1 > divider) tmp1 %= divider;
            if (tmp2 > divider) tmp2 %= divider;


            c = tmp1;
            d = tmp2;

            i = i / 2;
        }

        return  (int) ((long)a + (long)b)%divider;
    }

    public static void main(String[] args) {
        System.out.println(solution2(3, 4, 0));
//        System.out.println((long) (Integer.MAX_VALUE * (double) (3* Integer.MAX_VALUE-1)));
    }
}
