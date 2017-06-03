package LintCode.Binary;

/**
 * Created by Administrator on 2017/6/2.
 */
public class LintBinary62SearchInRotatedSortedArray {
    /**
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search(int[] A, int target) {
        // 直接去看答案了, 跟我想的差不多, 先判断target在前半段还是后半段
        // 看完答案自己写一遍AC 9min
        if (A == null || A.length == 0) {
            return -1;
        }
        if (target == A[0]) {
            return 0;
        }
        int lo = 0;
        int hi = A.length - 1;
        if (target > A[0]) { //完全可以放在一个while里面分情况讨论(看答案)
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[mid] < A[0] || A[mid] > target) {
                    hi = mid;
                } else if (A[mid] == target) {
                    return mid;
                } else {
                    lo = mid;
                }
            }
        } else {
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[mid] > A[0] || A[mid] < target) {
                    lo = mid;
                } else if (A[mid] == target) {
                    return mid;
                } else {
                    hi = mid;
                }
            }
        }
        if (A[lo] == target) { //没有first/last position之扰先比什么都行
            return lo;
        } else if (A[hi] == target) {
            return hi;
        } else {
            return -1;
        }
    }

    //Answer:
    public int searchAnswer(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid]) {
                // situation 1, red line
                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid; //没必要再考虑A[mid] == target的情况因为最终会落到start或者end上面
                }
            } else {
                // situation 2, green line
                if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        } // while

        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }
}
