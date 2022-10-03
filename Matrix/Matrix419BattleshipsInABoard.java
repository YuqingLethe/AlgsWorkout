package Matrix;

public class Matrix419BattleshipsInABoard {
    /**
     * Crib answer Oct 2022
     * 这道题不需要自己考虑是否valid. 题目的输入一定是个valid matrix. 只需要考虑怎么算总数即可.
     *
     * 解题思路:
     * 1. 因为ship不是横着就是竖着, 而我们的遍历顺序是从左到右从上到下.
     * 2. 因此只要左边或者上边是X则依旧同一艘船
     * 3. 只要左边和上边为空.则不是一艘船
     * 4. 想清楚这个, 答案就好理解了
     */
    class Solution {
        public int countBattleships(char[][] board) {
            int ans = 0, m = board.length, n = board[0].length;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] == 'X') {
                        if( (i > 0 && board[i-1][j] == 'X') || (j > 0 && board[i][j-1] == 'X') )   // If there is 'X' in left or up of current cell, continue. Otherwise, it'll definitely make battleship.
                            continue ;
                        else
                            ans++ ;
                    }
                }
            }
            return ans ;
        }
    }
}
