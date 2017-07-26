package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/25.
 */
public class LintBinary462TotalOccurrenceOfTarget {
    /**
     * 2017/7/25
     */
    public int totalOccurrence(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int lo = 0;
        int hi = A.length - 1;
        int firstIdx = 0;
        while(lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (A[lo] == target)  {
            firstIdx = lo;
        } else {
            firstIdx = hi;
        }

        if (A[firstIdx] != target) {
            return 0;
        }

        lo = firstIdx;
        hi = A.length - 1;
        while(lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] == target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (A[hi] == target) {
            return hi - firstIdx + 1;
        }
        return lo - firstIdx + 1;
    }
}
