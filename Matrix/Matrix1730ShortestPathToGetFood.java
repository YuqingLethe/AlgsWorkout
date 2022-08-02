package Matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class Matrix1730ShortestPathToGetFood {
    /**
     * What I was wrong:
     * 1. When checking visited[][] or depth[][], didn't go through all tile. Stopped when find the entry point.
     * 2. When traversal, use DFS, which is wrong. For shortest path, use BFS.
     * 3. When checking every level of BFS, used stack. Should use Queue.
     */
    class Solution {
        private char[][] grid;
        private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private int[][] depth;
        int m; // width of map
        int n; // height of map
        private Queue<int[]> q;

        public int getFood(char[][] g) {
            this.grid = g; // should do a deep copy
            this.m = grid.length;
            this.n = grid[0].length;
            this.depth = new int[m][n];
            this.q = new LinkedList<>();

            // Find the start point
            int standRow = 0;
            int standCol = 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 'X') {
                        depth[i][j] = -1;
                    } else if (grid[i][j] == '*') {
                        depth[i][j] = 0;
                        q.add(new int[]{i, j});
                        standRow = i;
                        standCol = j;
                    }
                }
            }
            if (grid[standRow][standCol] != '*') {
                return -1; // Stand point does not exist.
            }
            return bfs();
        }
        private int bfs() {
            // Init the BFS
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                int[] curr = q.poll();
                int currRow = curr[0];
                int currCol = curr[1];
                int currDepth = depth[currRow][currCol];

                if (grid[currRow][currCol] == '#') { // find nearest food cell
                    return currDepth;
                }
                for (int[] dir : directions) {
                    int nextRow = curr[0] + dir[0];
                    int nextCol = curr[1] + dir[1];

                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || depth[nextRow][nextCol] !=0 || grid[nextRow][nextCol] == '*') {
                        continue;
                    }
                    q.add(new int[]{nextRow, nextCol});
                    depth[nextRow][nextCol] = currDepth + 1;
                }
            }
            if (q.isEmpty()) {
                return -1;
            }
            return bfs(); // This is wired. we can use iterative not recursive;
            // e.g. https://leetcode.com/problems/shortest-path-to-get-food/discuss/1127459/JAVA-BFS-Clean-Solution
        }
    }
    public static void  main(String[] args) {
        // Test 1
        // [["O","O","*","O"],["#","O","O","O"],["O","O","X","O"],["O","O","O","O"]]
        int ans1 = 3;
    }
}
