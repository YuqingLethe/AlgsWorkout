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

    //TODO: use normal array to do it

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
