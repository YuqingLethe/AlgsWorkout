package LintCode.Binary.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2017/7/5.
 */
public class LintGraph573BuildPostOffice {
    /**
     * 和Graph文件里定义的是一样的, 我发现inner class更方便
     */
    class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 2017/7/5 和答案一样, 先求出所有empty点到所有house的距离和 distanceSum,
     * visitedTime最后和house数目做比较, 是有的house无法联通的话, 那这个empty点直接排除
     */
    public class Solution {
        int N, M;
        public int WALL = 2;
        public int HOUSE = 1;
        public int EMPTY = 0;
        int[] directionX = {1,-1,0,0};
        int[] directionY = {0,0,1,-1};
        int[][] grid;

        public int shortestDistance(int[][] grid) {
            // Write your code here
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            setGrid(grid);

            //Build visitedTimes and distanceSum
            List<Coord> houses = getList(HOUSE);
            int[][] visitedTimes = new int[N][M];
            int[][] distanceSum  = new int[N][M];

            for (Coord house : houses) {
                bfs(house, visitedTimes, distanceSum );
            }
            //Find ideal empty point
            List<Coord> empties = getList(EMPTY);
            int shortest = Integer.MAX_VALUE;

            for (Coord empty : empties) {
                if (visitedTimes[empty.x][empty.y] != houses.size()) {
                    continue;
                }
                if (distanceSum[empty.x][empty.y] < shortest) {
                    shortest = distanceSum[empty.x][empty.y];
                }
            }

            if (shortest == Integer.MAX_VALUE) {
                return -1;
            }

            return shortest;

        }

        private void bfs(Coord start, int[][] visitedTimes, int[][] distanceSum) {
            Queue<Coord> q = new LinkedList<>();
            boolean[][] hash = new boolean[N][M]; //这个hashtable足够了, 不需要用set, 还无需变Coord

            q.offer(start);
            hash[start.x][start.y] = true;
            int steps = 0;
            while(!q.isEmpty()) {
                int size = q.size();
                steps++;
                while(size-- > 0) {
                    Coord crt = q.poll();
                    for (int i = 0; i < 4; i++) {
                        int x = crt.x + directionX[i];
                        int y = crt.y + directionY[i];
                        if (inBound(x, y) && !hash[x][y] && grid[x][y] == EMPTY) {
                            hash[x][y] = true;
                            distanceSum[x][y] += steps;
                            visitedTimes[x][y]++;
                            q.offer(new Coord(x, y));
                        }
                    }
                }
            }
        }
        private boolean inBound(int x, int y) {
            if (x < 0 || x >= N || y < 0 || y >= M) {
                return false;
            }
            return true;
        }
        private void setGrid(int[][] grid) {
            this.N = grid.length;
            this.M = grid[0].length;
            this.grid = grid;
        }
        private List<Coord> getList(int type) {
            List<Coord> results = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == type) {
                        results.add(new Coord(i, j));
                    }
                }
            }
            return results;
        }
    }

}
