package BinarySearch;

public class Binary302SmallestRectangleEnclosingBlackPixels {
    /**
     * 最佳答案
     * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/discuss/75128/1ms-Concise-Java-Binary-Search-(DFS-is-4ms)
     *
     *
     */
    class solution {
        public int minArea(char[][] image, int x, int y) {
            int leftMost = fromWhiteToBlack(image, 0, y, true);
            int rightMost = fromBlackToWhite(image, y + 1, image[0].length, true);
            int topMost = fromWhiteToBlack(image, 0, x, false);
            int bottomMost = fromBlackToWhite(image, x + 1, image.length, false);
            return (rightMost - leftMost)*(bottomMost - topMost);
        }


        private int fromWhiteToBlack(char[][] image, int from, int to, boolean vertical) {
            while (from < to) {
                int mid = from + ((to - from) >> 1);
                if (hasBlack(image, mid, vertical)) {
                    to = mid;
                } else {
                    from = mid + 1;
                }
            }
            return to;
        }
        private int fromBlackToWhite(char[][] image, int from, int to, boolean vertical) {
            while (from < to) {
                int mid = from + ((to - from) >> 1);
                if (hasBlack(image, mid, vertical)) {
                    from = mid + 1;
                } else {
                    to = mid;
                }
            }
            return to;
        }

        private boolean hasBlack(char[][] image, int idx, boolean vertical) {
            if (vertical) {
                for (int i = 0; i < image.length; i++) {
                    if (image[i][idx] == '1') {
                        return true;
                    }
                }
            } else {
                for (int i = 0; i < image[0].length; i++) {
                    if (image[idx][i] == '1')
                        return true;
                }
            }
            return false;
        }
    }
}
