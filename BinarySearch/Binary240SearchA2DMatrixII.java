package BinarySearch;

/**
 * Created by Administrator on 2017/6/5.
 */
public class Binary240SearchA2DMatrixII {
    /**
     * 完全不会做, 用的九章答案 -> 右上角開始!
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

    /**
     * 這個的時間複雜度是log(n!) Therefore, each iteration of the loop runs in O(log(m−i)+log(n−i)) time. Worst case n=m
     * O(2⋅log(n−i))=O(log(n−i))
     *
     * 實際上: O(log(n)+log(n−1)+log(n−2)+…+log(1))
     * We can leverage the log multiplication rule log(a)+log(b)=log(ab)) to rewrite the complexity as
     * O(log(n⋅(n−1)⋅(n−2)⋅…⋅1)) = O(log(n!))
     */
    class 對角線BinarySearch {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            int shorter = Math.min(matrix.length, matrix[0].length);

            for (int i = 0; i < shorter; i++) {
                boolean verticalFound = searchElement(matrix, target, i, true);
                boolean horizontalFound = searchElement(matrix, target, i ,false);
                if (verticalFound || horizontalFound) { // 這個不需要放外面 一旦找到了就可以停止了
                    return true;
                }
            }

            return false;
        }

        private boolean searchElement(int[][] matrix, int target, int p, boolean vertical) {
            int lo = 0;
            int hi = vertical ? matrix[0].length - 1 : matrix.length - 1; //忘記-1 別搞反, 搞反不容易查出來因爲dianal

            while (lo <= hi) {
                int mid = lo + ((hi - lo) >> 1 );
                if (vertical) {
                    if (target == matrix[p][mid]) {
                        return true;
                    } else if (target < matrix[p][mid]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {
                    if (target == matrix[mid][p]) {
                        return true;
                    } else if (target < matrix[mid][p]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
            }
            return false;
        }
    }
}
