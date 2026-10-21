package Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix54201Matrix {

    /**
     * 容易错的点：忘记visited【】【】，得到相邻tile的数据的时候记得向Queue里面输送
     * 因为Queue的拿去顺序无所谓，可以用Add和Remove
     */
    class CopyAnswer {
        private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private int cNum;
        private  int rNum;
        private class TileWithStep {
            int row;
            int col;
            int step;
            TileWithStep(int i, int j, int step) {
                this.row = i;
                this.col = j;
                this.step = step;
            }
        }

        public int[][] updateMatrix(int[][] mat) {
            if (mat == null || mat.length == 0) {
                return null;
            }
            this.cNum = mat[0].length;
            this.rNum = mat.length;
            int[][] result = new int[rNum][cNum];
            boolean[][] visited = new boolean[rNum][cNum];
            Queue<TileWithStep> q = new LinkedList<>();

            // Push all 0s to the queue
            for (int i = 0; i < rNum; i ++) {
                for (int j = 0; j < cNum; j ++) {
                    if (mat[i][j] == 0) {
                        TileWithStep curr = new TileWithStep(i, j, 0);
                        q.add(curr);
                        result[i][j] = 0; // TODO: default to 0 so we can skip it
                        visited[i][j] = true;
                    }
                }
            }

            // Iterate from 0s to get all matrix
            while (!q.isEmpty()) {
                TileWithStep curr = q.remove();
                int currR = curr.row;
                int currC = curr.col;
                int currStep = curr.step;
                for (int[] direction : directions) {
                    int nextR = currR + direction[0];
                    int nextC = currC + direction[1];
                    int nextStep = currStep + 1;
                    if (nextR < 0 || nextR > rNum - 1 || nextC < 0 || nextC > cNum - 1) {
                        continue;
                    }
                    if (visited[nextR][nextC]) {
                        continue;
                    }
                    result[nextR][nextC] = nextStep;
                    TileWithStep next = new TileWithStep(nextR, nextC, nextStep);
                    q.add(next);
                    visited[nextR][nextC] = true;
                }
            }
            return result;
        }
    }


    /**
     * 我这个做法是从1开始找四周的点，他的错误是
     * 我必须访问完四周所有的点我自己才能有结果，我从A找B，但是想计算B必须要找A。
     * 这会导致碰到的第一个非0开始的tile就要遍历其他所有tile，陷入了死循环，导致StackOverflowError
     */
    class WrongAnswer {
        private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private int cNum;
        private  int rNum;

        public int[][] updateMatrix(int[][] mat) {
            if (mat == null || mat.length == 0) {
                return null;
            }
            this.cNum = mat[0].length;
            this.rNum = mat.length;
            int[][] result = new int[rNum][cNum];

            // initialize all element as -1
            for (int i = 0; i < rNum; i ++) {
                for (int j = 0; j < cNum; j ++) {
                    if (mat[i][j] != 0) { // 前期assign一些
                        result[i][j] = -1;
                    }

                }
            }

            // Calculate result
            for (int i = 0; i < rNum; i ++) {
                for (int j = 0; j < cNum; j ++) {
                    if (result[i][j] == 0) {
                        continue;
                    }
                    bfs(i, j, mat, result);
                }
            }
            return result;
        }
        private int bfs(int i, int j, int[][] mat, int[][] result) {
            if (i < 0 || i > rNum - 1 || j < 0 || j > cNum - 1) {
                return 0;
            }
            if (result[i][j] != -1) {
                return result[i][j];
            }

            int minNextTileDistance = Integer.MAX_VALUE;
            for (int[] direction : directions) {
                int nextRow = i + direction[0];
                int nextCol = j + direction[1];
                int nextTileDistance = bfs(nextRow, nextCol, mat, result);
                minNextTileDistance = Math.min(minNextTileDistance, nextTileDistance);
            }

            int resultValue = minNextTileDistance + 1;
            result[i][j] = resultValue;
            return resultValue;
        }
    }
}
