package Matrix;

public class Matrix289GameOfLife {
    /**
     * Nov 2022 20min
     */
    class BruteForce {
        private final int[][] directions = {{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1}};
        private int[][] board;
        private int M, N;
        public void gameOfLife(int[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            this.M = board.length;
            this.N = board[0].length;
            this.board = board;

            int[][] newBoard = new int[M][N];
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    int liveNum = getLiveNumAround(i, j);
                    if (board[i][j] == 1 && (liveNum < 2 || liveNum > 3)) {
                        newBoard[i][j] = 0;
                    } else if (board[i][j] == 0 && liveNum == 3) {
                        newBoard[i][j] = 1;
                    } else { //这个别忘了否则默认都是0了
                        newBoard[i][j] = board[i][j];
                    }
                }
            }
            // Copy next state to the board
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    board[i][j] = newBoard[i][j];
                }
            }
        }
        private int getLiveNumAround(int x, int y) {
            // Get num of live and dead tiles
            int countLive = 0;
            for (int[] dir : directions) {
                int row = x + dir[0];
                int col = y + dir[1];
                if (row < 0 || row >= M || col < 0 || col >= N) {
                    continue;
                }
                if (board[row][col] == 1) {
                    countLive ++;
                }
            }
            return countLive;
        }
    }

    /**
     * TODO: Low Priority 用别的数字来表示前期状态和后期状态. e.g. 之前是0增加人口就变2. 之前是1减少人口就变-1
     * answer https://leetcode.com/problems/game-of-life/solutions/293337/game-of-life/
     */
    class InPlace {

    }
}
