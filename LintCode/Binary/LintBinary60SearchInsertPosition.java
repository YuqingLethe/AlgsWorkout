package LintCode.Binary;

/**
 * Created by Administrator on 2017/5/31.
 */
public class LintBinary60SearchInsertPosition {
    /**
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     * 我觉得我的方法比答案简单: http://www.jiuzhang.com/solutions/search-insert-position/
     */
    public static int searchInsert(int[] A, int target) {
        // write your code here
        if (A.length == 0 || A[0] >= target) {
            return 0;
        }
        if (A.length == 1 && A[0] < target) {
            return 1;
        }
        int lo = 0;
        int hi = A.length - 1;
        while (lo < hi) { //又bug陷入死循环了
            int mid = lo + (hi - lo) / 2;
            if (A[mid] > target) {
                hi = mid;
            } else if (A[mid] < target) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        if (target > A[hi]) { //这个边界情况忘了考虑了! 只记得了前面的
            return hi + 1;
        }
        return hi;
    }
    public static void main(String[] args) {
        int[] A = {1,3,5,6,8,9};
        System.out.println(searchInsert(A, 10));
    }
}
