package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/4.
 */
public class LintBinary65MedianOfTwoSortedArray {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // z
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (helper(A, 0, B, 0, len / 2) + helper(A, 0, B, 0, len / 2 + 1))/2.0;
        } else {
            return (double) helper(A, 0, B, 0, len / 2 + 1);
        }
    }
    //注意是找第k个, 不是下标为k的值, 所以k不会为0, 而主程序也都要 + 1
    public int helper(int[] A, int A_start, int[] B, int B_start, int k) {
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }
        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }

        int A_key = (A_start + k / 2 - 1) < A.length
                ? A[A_start + k / 2 - 1]
                : Integer.MAX_VALUE;
        int B_key = (B_start + k / 2 - 1) < B.length
                ? B[B_start + k / 2 - 1]
                : Integer.MAX_VALUE;
        if (A_key < B_key) {
            return helper(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return helper(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
}
