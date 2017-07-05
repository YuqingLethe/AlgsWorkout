package LintCode.Binary.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2017/7/4.
 */
public class LintGraph433NumberOfIslands {
    /**
     * 7/4/2017 自己写用的DFS,传说中不推荐. 很多小细节需要注意
     */
    public class SolutionDFS {
        private int n;
        private int m;

        public int numIslands(boolean[][] grid) {
            int count = 0;
            if (grid == null || grid.length == 0) {
                return 0;
            }
            n = grid.length;
            m = grid[0].length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j]) {
                        count++;
                        checkAdj(grid, i, j);
                    }
                }
            }
            return count;
        }
        private void checkAdj (boolean[][] grid, int a, int b) {
            if (!grid[a][b]) {
                return;
            }
            int[] directionX = {0, 1, -1, 0};
            int[] directionY = {1, 0, 0, -1};

            grid[a][b] = false;
            //corner cases 其实这个变成一句话if (a < 0 || a >= m || b < 0 || b >= n) return;更方便
            if (a == 0) {
                directionX[2] = 0;
            }
            if (a == n - 1) { //不能用else if, 考虑到[[1]]的情况
                directionX[1] = 0;
            }
            if (b == 0) {
                directionY[3] = 0;
            }
            if (b == m - 1) { //行列别搞反了
                directionY[0] = 0;
            }
            for (int i = 0; i < 4; i++) {
//            System.out.println("check:" + directionX[i] + " " + directionY[i]);
                if (directionX[i] == 0 && directionY[i] == 0) {
                    continue;
                }
                if (grid[a + directionX[i]][b + directionY[i]]) {
                    checkAdj(grid, a + directionX[i], b + directionY[i]);
                }
            }
            return;
        }
    }
    /**
     * BFS 7/1/2017
     */
    public class SolutionBFS {
        private int n;
        private int m;

        public int numIslands(boolean[][] grid) {
            // Write your code here
            if (grid == null || grid.length == 0 || grid[0].length == 0) { //一口气写比较利落
                return 0;
            }
            n = grid.length;
            m = grid[0].length;

            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j]) {
                        markByBFS(grid, i, j);
                        count++;
                    }
                }
            }

            return count;

        }

        private void markByBFS(boolean[][] grid, int i, int j) {
            Queue<Cord> q = new LinkedList<>();
            int[] directionX = {1, -1, 0,  0};
            int[] directionY = {0,  0, 1, -1};

            Cord start = new Cord(i, j);
            q.offer(start);

            while(!q.isEmpty()) {
                Cord co = q.poll();
                grid[co.x][co.y] = false;
                for (int k = 0; k < 4; k++) {
                    int xCord = co.x + directionX[k];
                    int yCord = co.y + directionY[k];
                    if (OutOfBound(xCord, yCord) || !grid[xCord][yCord]) {
                        continue;
                    }
//                System.out.println("xCord, yCord: " + xCord + " " + yCord);
                    q.offer(new Cord(xCord, yCord));
                }
            }
            return;
        }

        private boolean OutOfBound(int i, int j) {
            if (i < 0 || i >= n || j < 0 || j >= m) {
                return true;
            }
            return false;
        }
    }

    class Cord {
        int x, y;
        public Cord(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }
}
