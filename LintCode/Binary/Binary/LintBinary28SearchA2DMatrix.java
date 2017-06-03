package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/5/30.
 */
public class LintBinary28SearchA2DMatrix {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 很聪明的对付空集的办法, 无论多少维
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int lo = 0;
        int hi = m * n - 1;
        while (lo  + 1 < hi) { //因为会有找不到target的情况, 所以当low == hi的是时候可能会陷入死循环. 因此要这么写
            int mid = lo + (hi - lo) / 2;
            int i = mid / m;
            int j = mid % m;
            if (matrix[i][j] < target) {
                lo = mid;
            } else if (matrix[i][j] > target) {
                hi = mid;
            } else {
                return true;
            }
        }
        //下面这个情况完全是因为上面lo + 1 < hi的终止条件才有的
        if (matrix[lo / m][lo % m] == target
                || matrix[hi / m][hi % m] == target) {
            return true;
        }
        return false;
    }

    public static boolean searchMatrixTwoBinary(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid][m - 1] > target) {
                hi = mid;
            } else if (matrix[mid][m - 1] < target) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        int row = hi;
        lo = 0;
        hi = m - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[row][mid] > target) {
                hi = mid - 1;
            } else if (matrix[row][mid] < target) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        return false;

    }

}
