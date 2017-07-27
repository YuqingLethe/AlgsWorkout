package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/26.
 */
public class LintBinary459ClosestNumberInSortedArray {
    /**
     * 2017/7/26
     * 在找firstIdx的时候, 别忘了A[hi] == target的情况
     */
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = A.length - 1;
        while(lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A[mid] < target) {
                lo = mid;
            } else if (A[mid] > target) {
                hi = mid;
            } else {
                return mid;
            }
        }
        if (A[hi] == target) { //其实这里应该也印证A[lo] == target 是我理解不深. 但这个情况被后面的return 覆盖掉了
            return hi;
        }
        if (A[lo] > target) {
            return lo;
        }
        if (A[hi] < target) {
            return hi;
        }
        return target - A[lo] > A[hi] - target ? hi : lo;
    }
}
