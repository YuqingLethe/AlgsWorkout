package Matrix;

public class Matrix733FloodFill {

    /**
     * 天啊AI答案绝了，太neat了
     */
    public class Matrix733FloodFill_AIANswer {
        class Solution {
            public int[][] floodFill(int[][] image, int sr, int sc, int color) {
                int origColor = image[sr][sc];
                // 关键：如果新旧颜色一致，直接返回，避免死循环
                if (origColor == color) {
                    return image;
                }
                dfs(image, sr, sc, origColor, color);
                return image;
            }

            private void dfs(int[][] image, int r, int c, int origColor, int newColor) {
                // 越界或者当前格子不是目标旧颜色，直接返回
                if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != origColor) {
                    return;
                }

                // 染色
                image[r][c] = newColor;

                // 向四个方向扩散
                dfs(image, r + 1, c, origColor, newColor);
                dfs(image, r - 1, c, origColor, newColor);
                dfs(image, r, c + 1, origColor, newColor);
                dfs(image, r, c - 1, origColor, newColor);
            }
        }
    }

    /**
     * 如果看答案会觉得我这个写得有啰嗦了
     */
    class Solution {
        private int m;
        private int n;
        private int[][] image;
        private int origColor;
        private int tgtColor;

        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            // Special image
            if (image == null || (image.length == 1 && image[0].length == 1)) {
                return image;
            }
            this.m = image.length;
            this.n = image[0].length;
            this.image = image;
            this.origColor = image[sr][sc];
            this.tgtColor = color;

            if (sr < 0 || sc < 0 || sr > m || sc > n) { // 这里写错了应该是m-1 和n-1
                throw new IllegalArgumentException("Invalid input");
            }
            floodFillTile(sr, sc);
            return image;
        }

        private void floodFillTile(int sr, int sc) {
            // 这个终止条件一开始都没加！！！后来想到了tgtColor又忘了origColor！！
            // 死循环隐患（最关键）：
            //当 origColor == tgtColor 时（即起始点颜色本来就等于目标颜色），如果不提前拦截，递归会不断重复访问相同颜色的格子导致 StackOverflowError
            if (image[sr][sc] == tgtColor || image[sr][sc] != origColor) {
                return;
            }
            if (floodableTile(sr, sc)) {
                image[sr][sc] = tgtColor;
            }
            if (sr >= 1) {
                floodFillTile(sr - 1, sc);
            }
            if (sr < m - 1) {
                floodFillTile(sr + 1, sc);
            }
            if (sc >= 1) {
                floodFillTile(sr, sc - 1);
            }
            if (sc < n - 1) {
                floodFillTile(sr, sc + 1);
            }
        }

        private boolean floodableTile(int sr, int sc) {
            if (image[sr][sc] != tgtColor && image[sr][sc] == origColor) {
                return true;
            }
            return false;
        }

    }


}
