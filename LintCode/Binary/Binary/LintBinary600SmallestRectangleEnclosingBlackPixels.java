package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/6/14.
 */
public class LintBinary600SmallestRectangleEnclosingBlackPixels {
    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    public int minArea(char[][] image, int x, int y) {
        // Write your code here
        int n = image.length;
        if (image == null || n == 0) {
            return -1;
        }
        int top = searchTop(image, x);
//        System.out.println(top);
        int bottom = searchBottom(image, x);
//        System.out.println(bottom);
        int left = searchLeft(image, top, bottom, y);
//        System.out.println(left);
        int right = searchRight(image, top, bottom, y);
//        System.out.println(right);
        return (right - left + 1) * (bottom - top + 1);
    }
    public int searchLeft(char[][] image, int top, int bottom, int hi) {
        int lo = 0;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (!isEmptyColumn(image, top, bottom, mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        if (!isEmptyColumn(image, top, bottom, lo)) {
            return lo;
        }
        return hi;
    }
    public int searchRight(char[][] image, int top, int bottom, int lo) {
        int hi = image[0].length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (!isEmptyColumn(image, top, bottom, mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        if (!isEmptyColumn(image, top, bottom, hi)) {
            return hi;
        }
        return lo;
    }
    private boolean isEmptyColumn(char[][] image, int top, int bottom, int col) {
        for (int i = top; i <= bottom; i++) {
            if (image[i][col] == '1') {
                return false; //竟然把函数意思和返回值搞反了.....
            }
        }
        return true;
    }
    public int searchTop (char[][] image, int hi) {
        int lo = 0;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (!isEmptyRow(image[mid])) {
                hi = mid;
            } else {
                lo = mid;
            }
//            System.out.println("searchTop, mid=" + mid);
        }
        if (!isEmptyRow(image[lo])) {
//            System.out.println("searchTop, mid=" + lo);
            return lo;
        }
        return hi;
    }
    public int searchBottom (char[][] image, int lo) {
        int hi = image.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (!isEmptyRow(image[mid])) {
                lo = mid;
            } else {
                hi = mid;
            }
//            System.out.println("searchBottom, mid=" + mid);
        }
        if (!isEmptyRow(image[hi])) {
            return hi;
        }
        return lo;
    }
    private boolean isEmptyRow(char[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == '1') {
                return false;
            }
        }
        return true;
    }
    /**
     * 7/27/2017
     * 有了bug先检查编程时候最容易错的地方, 一定要把最容易错的地方先记下来
     */
    public class Solution {

        public int minArea(char[][] image, int x, int y) {
            if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
                return 0;
            }
            int n = image.length;
            int m = image[0].length;
            // Check rows
            int upperBound = checkRowBoundaries(image, 0, x, true);
            int lowerBound = checkRowBoundaries(image, x, n - 1, false);
            //Check Columns
            int leftBound = checkColBoundaries(image, 0, y, true);
            int rightBound = checkColBoundaries(image, y, m - 1, false);

            return (rightBound - leftBound + 1) * (lowerBound - upperBound + 1);
        }
        private boolean colHasBlack(char[][] image, int col) {
            int n = image.length;
            int m = image[0].length;
            for (int i = 0; i < n; i++) {
                if (image[i][col] == '1') {
                    return true;
                }
            }
            return false;
        }
        private int checkColBoundaries(char[][] image, int start, int end, boolean left) {
            if (left) {
                int lo = start;
                int hi = end;
                while (lo + 1 < hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (colHasBlack(image, mid)) {
                        hi = mid;
                    } else {
                        lo = mid;
                    }
                }
                if (colHasBlack(image, lo)) {
                    return lo;
                }
                return hi;
            } else {
                int lo = start;
                int hi = end;
                while (lo + 1 < hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (colHasBlack(image, mid)) {
                        lo = mid;
                    } else {
                        hi = mid;
                    }
                }
                if (colHasBlack(image, hi)) {
                    return hi;
                }
                return lo;
            }
        }

        private boolean rowHasBlack(char[] row) {
            for (char c : row) {
                if (c == '1') {
                    return true;
                }
            }
            return false;
        }
        private int checkRowBoundaries (char[][] image, int start, int end, boolean upper) {
            int lo = start;
            int hi = end;
            while (upper && lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (rowHasBlack(image[mid])) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }
            if (upper && rowHasBlack(image[lo])) {
                return lo;
            }
            if (upper) {
                return hi;
            }

            while (!upper && lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (rowHasBlack(image[mid])) {
                    lo = mid;
                } else  {
                    hi = mid;
                }
            }
            if (rowHasBlack(image[hi])) {
                return hi;
            }
            return lo;
        }
    }
}
