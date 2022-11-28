package BackTracking;

public class BC79WordSearch {
    /**
     * Nov 2022 30min
     * Questions:
     *     1. Is it possible two valid paths?
     *     2. (Should) For a valid path, can the same letter can bu used twice?
     *
     * 自己的思路对但是忘记了上面#2这种极端情况:
     * 坑:
     * 1. 同一个cell不能用两次, 因此要标记
     * 2. 路径不通的还要把之前标记的再改回!!
     */
    class Solution {
        private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private int M;
        private int N;
        private char[][] board;
        public boolean exist(char[][] board, String word) {
            if (board == null || board[0].length == 0) {
                return false;
            }
            this.M = board.length;
            this.N = board[0].length;
            this.board = board;
            if (M*N < word.length()) {
                return false;
            }


            char[] charArr = word.toCharArray();
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    if(findCharArrayFromCords(i, j, charArr, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }
        private boolean findCharArrayFromCords(int x, int y, char[] charArr, int charIdx) {
            if (x < 0 || x >= M || y < 0 || y >= N || board[x][y] != charArr[charIdx]) {
                return false;
            }
            if (charIdx == charArr.length - 1) { // reach the end of word
                return true;
            }
            board[x][y] = '#'; //这个技巧是直接改原数据了
            for (int[] dir : directions) {
                int row = x + dir[0];
                int col = y + dir[1];
                if (findCharArrayFromCords(row, col, charArr, charIdx + 1)) {
                    return true;
                }
            }
            board[x][y] = charArr[charIdx]; //一定记得这里要改回来, 前面符合的不一定是valid path, 走不通再删除标记!!!
            return false;
        }
    }

}
