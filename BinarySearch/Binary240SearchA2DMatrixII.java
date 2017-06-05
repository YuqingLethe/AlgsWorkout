package BinarySearch;

/**
 * Created by Administrator on 2017/6/5.
 */
public class Binary240SearchA2DMatrixII {
    /**
     * 完全不会做, 用的九章答案
     */
    public boolean searchMatrixTrick(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int x = 0;
        int y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[y][x] < target) {//别把x y写反了
                x++;
            } else if (matrix[y][x] > target) {
                y--;
            } else {
                return true;
            }
        }
        return false;
    }
}
