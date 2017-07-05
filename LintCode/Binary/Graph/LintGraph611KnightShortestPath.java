package LintCode.Binary.Graph;

import java.util.LinkedList;
import java.util.Queue;

class Point {
      public int x, y;
      public Point() { x = 0; y = 0; }
      public Point(int a, int b) { x = a; y = b; }
  }
public class LintGraph611KnightShortestPath {
    /**
     * 2017/7/5 何时变true非常关键, Memory Limit Exceed  所以在求next的时候就变true能剩下很多空间
     */
    public class SolutionMLT {

        int[] directionX = {1,1,-1,-1,2,2,-2,-2};
        int[] directionY = {2,-2,2,-2,1,-1,1,-1};
        int N;
        int M;
        public int shortestPath(boolean[][] grid, Point source, Point destination) {
            // Write your code here \
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            N = grid.length;
            M = grid[0].length;

            Queue<Point> q = new LinkedList<>();
            q.offer(source);

            int steps = 0;
            while(!q.isEmpty()) {
                int size = q.size();
                while(size-- > 0) {
                    Point crt = q.poll();
                    if (crt.x == destination.x && crt.y == destination.y) { //注意object的比较
                        return steps;
                    }
                    for (int i = 0; i < 8; i++) {
                        Point next = new Point(crt.x + directionX[i], crt.y + directionY[i]);
                        if (!validMove(grid, next)) {
                            continue;
                        }
                        q.offer(next);
                        grid[crt.x][crt.y] = true; //一定要在这里就变True,否则会MemoryLimitExceed

                    }
                }
                steps++;
            }
            return -1;
        }
        private boolean validMove(boolean[][] grid, Point p) {
            if (p.x < 0 || p.x >= N || p.y < 0 || p.y >= M) {
                return false;
            }
            if (grid[p.x][p.y]) {
                return false;
            }
            return true;
        }
    }
}
