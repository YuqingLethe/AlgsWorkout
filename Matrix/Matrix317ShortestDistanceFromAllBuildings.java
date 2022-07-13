package Matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yuqing on 7/13/22.
 */
public class Matrix317ShortestDistanceFromAllBuildings {
    /**
     * July2022 只coding + debug 就做了60min
     * Time Complexity: N squired * M squared   N^2*M^2
     * Space Complexity: N*M
     */
    class BFS_FromHouseToLand {
        int row;
        int col;
        public int shortestDistance(int[][] grid) {
            row = grid.length;
            col = grid[0].length;
            // 注意第一個值是distance， 第二個是幾個house we met
            int[][][] distanceMap = new int[row][col][2];
            int totalHouse = 0;

            // Get all values of distanceMap
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (grid[i][j] == 1) {
                        totalHouse ++;
                        getDistanceToAllEmptyTiles(grid, distanceMap, i, j);
                    }
                }
            }
            // Get minimum distance to all houses
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (grid[i][j] == 0 && distanceMap[i][j][1] == totalHouse) {
                        result = Math.min(result, distanceMap[i][j][0]);
                    }
                }
            }

            if (result == Integer.MAX_VALUE) {
                return -1; //這個corner case忘記了！
            }

            return result;
        }

        private void getDistanceToAllEmptyTiles(int[][] grid, int[][][] distanceMap, int houseRow, int houseCol) {
            boolean[][] vis = new boolean[row][col];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{houseRow, houseCol}); // offer
            vis[houseRow][houseCol] = true;


            int[][] fourWay = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int step = 0;
            while (!q.isEmpty()) {
                step ++;
                for (int i = q.size(); i > 0; --i) { // 注意這裏因爲q不斷變化不是一次取出所有， 因此不能用i< q.size()來判斷！！！！
                    int[] curr = q.poll();

                    for (int[] dir : fourWay) {  // four directions
                        int nextRow = curr[0] + dir[0];
                        int nextCol = curr[1] + dir[1];
                        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col
                                || vis[nextRow][nextCol] == true || grid[nextRow][nextCol] != 0) {
                            continue;
                        }
                        vis[nextRow][nextCol] = true;
                        distanceMap[nextRow][nextCol][0] += step;
                        distanceMap[nextRow][nextCol][1] += 1;
                        q.offer(new int[]{nextRow, nextCol});
                        // System.out.println("next: " + nextRow + " " + nextCol + " distanceMap: " + distanceMap[nextRow][nextCol][0] + " " + distanceMap[nextRow][nextCol][1]);
                    }
                }
            }
        }
    }

    /**
     * July 2022 Crib answer directly
     * 優化： 這個方法變換grid[nextRow][nextCol]的值，可以提前發現不通。構思巧妙。
     *
     * Time Complexity（N*M）發現房子的loop * (N*M)得到到那個房子所有distances ~ N*N*M*M）
     * Space Complexity：N*M
     */
    class Optimized_FromLandToHouse {
        int row;
        int col;
        public int shortestDistance(int[][] grid) {
            int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int rows = grid.length;
            int cols = grid[0].length;
            int[][] totals = new int[rows][cols];
            int emptyLandValue = 0;
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (grid[i][j] == 1) { // every house
                        Queue<int[]> q = new LinkedList<>();
                        q.offer(new int[]{i, j});
                        int steps = 0;

                        minDist = Integer.MAX_VALUE; // only the last house traversal counts
                        while (!q.isEmpty()) {
                            steps ++;
                            for (int k = q.size(); k > 0; --k) { // one distance further
                                int[] curr = q.poll();
                                for (int[] dir : dirs) {
                                    int nextRow = curr[0] + dir[0];
                                    int nextCol = curr[1] + dir[1];
                                    if (nextRow >= rows || nextRow < 0 || nextCol >= cols || nextCol < 0 || grid[nextRow][nextCol] != emptyLandValue) {
                                        continue;
                                    }

                                    grid[nextRow][nextCol] --;
                                    totals[nextRow][nextCol] += steps;
                                    q.offer(new int[]{nextRow, nextCol});
                                    minDist = Math.min(minDist, totals[nextRow][nextCol]);
                                    // System.out.println(nextRow + " " + nextCol +  " " + totals[nextRow][nextCol]);
                                }
                            }
                        }
                        emptyLandValue --;
                    }

                }
            }
            return minDist == Integer.MAX_VALUE ? -1 : minDist;
        }
    }
}
