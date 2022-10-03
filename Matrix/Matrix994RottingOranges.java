package Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix994RottingOranges {
    /**
     * TODO: Low Priority, any time: Use increasing number to indicate minutes/levels
     * Leetcode official solution
     */
    class InPlaceBFS {

    }

    /**
     * Oct 2022 self
     */
    class QueueBFS {
        public int orangesRotting(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return -1;
            }
            // Deep copy and find all rotted oranges in initial status
            final int M = grid.length;
            final int N = grid[0].length;
            int[][] orangeGrid = new int[M][N];
            Queue<int[]> q = new LinkedList<>();
            boolean findFreshOrange = false;
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (grid[i][j] == 2) {
                        q.add(new int[]{i, j});
                    } else if (grid[i][j] == 1) {
                        findFreshOrange = true;
                    }
                    orangeGrid[i][j] = grid[i][j]; //deep copy
                }
            }
            if (!findFreshOrange) { // No fresh oranges to start with 这是一个忽略的case, [[0]]应该返回0 不是-1
                return 0;
            } else if (q.isEmpty()) { // Have fresh orange but no rotted oranges
                return -1;
            }

            // BFS to get minutes to rot all avaialble oranges
            int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
            int time = 0;
            while (!q.isEmpty()) {
                int currMinutesRottedOranges = q.size();
                for (int i = 0; i < currMinutesRottedOranges; ++i) {
                    int[] rottedOrange = q.poll();
                    int currRow = rottedOrange[0];
                    int currCol = rottedOrange[1];
                    for (int[] dir : directions) {
                        int nextRow = currRow + dir[0];
                        int nextCol = currCol + dir[1];
                        if (nextRow < 0 || nextRow >= M || nextCol < 0 || nextCol >= N || orangeGrid[nextRow][nextCol] != 1) {
                            continue;
                        }
                        orangeGrid[nextRow][nextCol] = 2;
                        q.add(new int[]{nextRow, nextCol});
                    }
                }
                time ++;
            }

            // check if any fresh oranges left
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (orangeGrid[i][j] == 1) {
                        return -1;
                    }
                }
            }
            return time - 1;
        }
    }
}
