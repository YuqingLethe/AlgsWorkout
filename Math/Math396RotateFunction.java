package Math;

/**
 * Created by Administrator on 2016/11/6.
 */
public class Math396RotateFunction {
    /**
     * Runtime: 4ms  11/6/2016
     * Add one sum onto f0 and minus the last number in the current rotate array
     */
    public static int maxRotateFunction(int[] A) {
        int f0 = 0, sum = 0;
        for ( int i = 0; i < A.length; i++) {
            f0 += i*A[i];
            sum += A[i];
        }
        int max = f0;
        for (int i = 1; i < A.length; i++) {
            f0 += sum - A.length*A[A.length - i];
            if (f0 > max) max = f0;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] A = {4,3,2,6};
        System.out.println(maxRotateFunction(A));
    }

}
