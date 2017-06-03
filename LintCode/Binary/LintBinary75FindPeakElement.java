package LintCode.Binary;

/**
 * Created by Administrator on 2017/6/2.
 */
public class LintBinary75FindPeakElement {
    /**
     * @param A: An integers array. 七分钟一遍AC
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if (A == null || A.length < 3) {
            return -1;
        }
        int lo = 0;
        int hi = A.length - 1;
        while (lo + 2 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] > A[mid - 1]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo + 1;
    }
}
