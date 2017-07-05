package LintCode.Binary.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class LintGraph598ZombieInMatrix {
    /**
     * 2017/7/4 无法探测Corner Case
     * [
     *  [0,2,0],
     *  [2,2,0],
     *  [2,0,1]
     * ]
     */
    public class SolutionWrong {

        private int n;
        private int m;
        public int zombie(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            n = grid.length;
            m = grid[0].length; //别忘了initialize

            int count = 0;
            boolean hasPeople = true;
            outerCycle:
            while (hasPeople) {
                oneDayPass(grid);
                count++; //count应该在哪里加, 根据意思在最直观的地方加, 一天过后!
                hasPeople = false;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (grid[i][j] == 0) {
                            hasPeople = true;
                            continue outerCycle;
                        }
                    }
                }
            }
            return count;
        }

        private void oneDayPass(int[][] grid) {
            //Find all zombies at the beginning of the day
            Queue<Cord> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        q.offer(new Cord(i, j));
                    }
                }
            }
            //Turn all nearest people into zombies at the end of the day
            int[] directionX = {1, -1, 0,  0};
            int[] directionY = {0,  0, 1, -1};
            while(!q.isEmpty()) {
                Cord co = q.poll();
                for (int k = 0; k < 4; k++) {
                    int xCord = co.x + directionX[k];
                    int yCord = co.y + directionY[k];
//                System.out.println(xCord+" " + yCord);
                    if (inBound(xCord, yCord) && grid[xCord][yCord] == 0) {
                        grid[xCord][yCord] = 1;
                    }
                }

            }
            return;
        }

        private boolean inBound(int i, int j) {
            if (i < 0 || i >= n || j < 0 || j >= m) {
                return false;
            }
            return true;
        }
    }

    /**
     * 7/5/2017 Correct solution, same as Jiuzhang
     */
    public class Solution {
        private int N;
        private int M;
        public int ZOMBIE = 1;
        public int WALL = 2;
        public int PEOPLE = 0;

        int[] directionX = {1,-1, 0, 0};
        int[] directionY = {0, 0, 1, -1};
        /**
         * @param grid  a 2D integer grid
         * @return an integer
         */
        public int zombie(int[][] grid) {
            // Write your code here
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            N = grid.length;
            M = grid[0].length;

            //Calculate how many people in matrix
            int peopleCount = 0;
            Queue<Cord> zombieList = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == PEOPLE) {
                        peopleCount ++;
                    }
                    if (grid[i][j] == ZOMBIE) {
                        zombieList.offer(new Cord(i, j));
                    }
                }
            }

            if (peopleCount == 0) { //可以加上这个corner case
                return 0;
            }

            //Find days
            int count = 0;
            while(!zombieList.isEmpty()) {
                int size = zombieList.size();
                count++;
                while(size-- > 0) {
                    Cord crt = zombieList.poll();
                    for (int i = 0; i < 4; i++) {
                        int xCord = crt.x + directionX[i];
                        int yCord = crt.y + directionY[i];
                        if (inBound(grid, xCord, yCord) && grid[xCord][yCord] == PEOPLE) {
                            grid[xCord][yCord] = ZOMBIE;
                            zombieList.offer(new Cord(xCord, yCord));
                            peopleCount--;

                        }
                    }
                }
                if (peopleCount == 0) {
                    return count;
                }
            }
            return -1;


        }

        private boolean inBound(int[][] grid, int x, int y) {
            if (x < 0 || x >= N || y < 0 || y >= M) {
                return false;
            }
            return true;
        }
    }

}