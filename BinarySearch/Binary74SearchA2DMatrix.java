package BinarySearch;

/**
 * Created by Administrator on 2017/6/4.
 */
public class Binary74SearchA2DMatrix {
    /**
     * 直接用模板
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int lo = 0;
        int hi = n*m - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid/m][mid%m] == target) {
                return true;
            } else if (matrix[mid/m][mid%m] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (matrix[hi/m][hi%m] == target) {
            return true;
        } else if (matrix[lo/m][lo%m] == target) { //又犯了以为nums[lo]这个值一定会被mid选到 (进入过循环)的错误!
            return true;
        }
        return false;
    }

    /**
     * 如果while执行条件一定要用lo < hi, 则调整如下. lo必须进一位来避免死循环, 但有时lo这样+1会溢出Integer.MAX_VALUE!
     */
    public boolean searchMatrixLoHi(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int lo = 0;
        int hi = n*m - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid/m][mid%m] == target) {
                return true;
            } else if (matrix[mid/m][mid%m] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        if (matrix[hi/m][hi%m] == target) {
            return true;
        }
        return false;
    }
}
