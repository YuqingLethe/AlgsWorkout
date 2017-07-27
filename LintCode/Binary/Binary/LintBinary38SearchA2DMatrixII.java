package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/26.
 */
public class LintBinary38SearchA2DMatrixII {
    /**
     * 2017/7/26
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int count = 0;
        int i = n - 1;
        int j = 0;
        while(i >= 0 && j < m) {
            if (matrix[i][j] == target) {
                count ++;
                i --;
                j ++;
            } else if (matrix[i][j] < target) {
                j ++;
            } else {
                i --;
            }
        }
        return count;
    }

}
