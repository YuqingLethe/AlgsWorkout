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
}
