package Matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.1point3acres.com/bbs/thread-942984-1-1.html
 * 很常规的bfs找路径问题，char[][] grid上， x代表obstacle， . 代表可以走，求小车a能否从起始地到目的停车位A。
 * followup没有用代码实现出来，说了大概思路，留了comment就结束了，求大佬给给思路或者指点是否有lc题目，感谢！
 * Followup是如果这个grid上有两辆车a,b，每辆车都有各自目的停车为A,B，返回a和b是否都能到达各自的目的地。
 */
public class Matrix50001PathFromCarToParkingSpot {
    static class BFS {
        private int[][] directions = {{1, 0},{-1, 0},{0, 1},{0, -1}};
        private static int rows;
        private static int cols;
        public boolean canReachParkingSpot(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return false;
            }
            this.rows = grid.length;
            this.cols = grid[0].length;
            Queue<int[]> q = new LinkedList<>(); // store cells of next step

            // Find start point a if needed. Visited cells mark as 'V'
            q.add(new int[]{0, 0});
            grid[0][0] = 'V';
            while (!q.isEmpty()) {
                int[] currCell = q.poll();
                for (int[] dir : directions) {
                    int nextRow = currCell[0] + dir[0];
                    int nextCol = currCell[1] + dir[1];
                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols
                            || grid[nextRow][nextCol] == 'V' || grid[nextRow][nextCol] == 'x') { //注意这里的or, 不适合用!=
                        continue;
                    }
                    if (grid[nextRow][nextCol] == 'A') {
                        return true;
                    }
                    grid[nextRow][nextCol] = 'V';
                    q.add(new int[]{nextRow, nextCol});
                }
            }
            return false;
        }
    }

    static class FollowUp {
        private int[][] directions = {{1, 0},{-1, 0},{0, 1},{0, -1}};
        private static int rows;
        private static int cols;
        public boolean[] canReachParkingSpot(char[][] grid, int[][] car, int[][] parking) {
            // 先判断a能否到A, 把b和B设成通路'.'

            // 先用BFS判断a是否能到A, 顺带看最短路径长度.

            // 用DFS找出a到A的最短路径的那条通路并标记1

            // 判断b能否挪车到a路径以外的空地上, 用bfs移动1或.,只要挪到标记到.的地方就行. 则证明a可到达A
            return new boolean[1];
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'a', '.', '.', '.'},
                {'x', 'x', 'x', '.'},
                {'A', '.', '.', '.'},
                {'.', 'x', '.', '.'},
        };
        BFS solution = new BFS();
        System.out.println(solution.canReachParkingSpot(grid));

        char[][] gridNotReach = {
                {'a', '.', '.', '.'},
                {'x', 'x', 'x', '.'},
                {'A', '.', '.', 'x'},
                {'.', 'x', '.', '.'},
        };
        System.out.println(solution.canReachParkingSpot(gridNotReach));


        char[][] gridTwoCars = {
                {'a', '.', '.', 'B'},
                {'x', 'x', 'x', '.'},
                {'A', '.', 'b', '.'},
                {'.', 'x', '.', '.'},
        };
        System.out.println(solution.canReachParkingSpot(gridNotReach));
    }
}
