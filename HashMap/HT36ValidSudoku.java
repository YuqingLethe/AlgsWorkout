package HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HT36ValidSudoku {
    /**
     * Runtime:48ms  Use 1hr to debug the second for loop condition bug....
     * Runtime: 37ms if curr != '.' {...}
     *
     * Adding numbers in three ways, 10/7/2016
     * vertical (V+columnNumber+number), e.g. V91 #1 in the ninth column
     * horizontal, (H+columnNumber+number) e.g. H29 #9 in the second row
     * and 3x3 grid (B+blockNumber+number) e.g. B23 #3 in topmiddle block
     * https://discuss.leetcode.com/topic/27436/short-simple-java-using-strings
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        Set s = new HashSet();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char curr = board[i][j];
                if (curr == '.') continue;
                if (!s.add("H"+ i + curr) || !s.add("V"+ j + curr) || !s.add("B"+ i/3 + j/3 + curr)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printSet(Set s) {
        Iterator is = s.iterator();
        while(is.hasNext()) {
            System.out.println(is.next());
        }
    }

    /**
     * 这个比答案更直观，第一个for循环每次检查一行一列一个box。但这个答案最多联系一下坐标的计算，最好的还是用Array或者bit
     * 难点：确认currBox的坐标
     *
     * 28min看答案到写最简单的思路，包括问AI
     */
    public boolean isValidSudoku_NormalArraySolution(char[][] board) {
        final int n = 9; // dimention

        for (int i = 0; i < n; i ++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> colSet = new HashSet<>();
            Set<Character> boxSet = new HashSet<>();
            for (int j = 0; j < n; j ++) {
                char currRow = board[i][j];
                char currCol = board[j][i];
                char currBox = board[3*(i/3) + (j/3)][3*(i%3) + (j%3)]; // AI给我说的
                if (currRow != '.') {
                    if (rowSet.contains(currRow)) {
                        return false;
                    }
                    rowSet.add(currRow);
                }
                if (currCol != '.') {
                    if (colSet.contains(currCol)) {
                        return false;
                    }
                    colSet.add(currCol);
                }
                if (currBox != '.') {
                    if (boxSet.contains(currBox)) {
                        return false;
                    }
                    boxSet.add(currBox);
                }
            }
        }
        return true;
    }

    /**
     * 难在移动位置和确认box的index
     * @param board
     * @return
     */
    public boolean isValidSudoku_BitMasking(char[][] board) {
        final int N = board.length;
        int[] rows = new int[N];
        int[] cols = new int[N];
        int[] boxes = new int[N];

        for (int i = 0; i < N; i ++) {
            for (int j = 0;  j < N; j ++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int val = board[i][j] - '0'; //用数字确认移动的位置
                int pos = 1 << (val - 1); //为什么-1？ 因为1的时候不需要移动！
                System.out.println("val=" + val + " pos=" + pos);

                if ((rows[i] & pos) > 0) {
                    return false;
                }
                rows[i] |= pos;

                if ((cols[i] & pos) > 0) {
                    return false;
                }
                cols[i] |= pos;

                int idx = (i / 3) * 3 + j / 3; //难点！
                if ((boxes[idx] & pos) > 0) {
                    return false;
                }
                boxes[idx] |= pos;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] sa = {"..4...63.",".........","5......9.","...56....",
                "4.3.....1","...7.....","...5.....",".........","........."};
        char[][] board = new char[sa.length][9];
        for (int i = 0; i < sa.length; i++) {
            board[i] = sa[i].toCharArray();
        }
        //Printout the sudoku
        for (char[] ca : board) {
            System.out.println(ca);
        }
        System.out.println(isValidSudoku(board));
    }
}
