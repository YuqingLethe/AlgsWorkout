package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 Given an integer n, return all distinct solutions to the n-queens puzzle.
 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 For example,
 There exist two distinct solutions to the 4-queens puzzle:
 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 */
public class BC51NQueens {
    /**
     * 2017/6/9
     * 之前看答案写的不理解, 尤其是valid的函数, 后来看了youtube讲解自己写了一遍, 豁然开朗
     *
     * 2017/6/27
     * 很多细节小错误, 慢慢debug出来. 不过第一次自己写完了.
     * 是一步步按照思维添加写的, 并坚持先写主体再写不重要的, 感觉还不错.
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        ArrayList<Integer> nthRow = new ArrayList<>();
        search (n, results, nthRow);
        return results;
    }
    private static void search (int  n, List<List<String>> results, ArrayList<Integer> nthRow) {
        if (nthRow.size() == n) {
            results.add(draw(nthRow));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!valid(nthRow, i)) {
                continue; //6/27/2017 这里不能用break啊, 不然这一行其他column就不考虑了吗.....
            }
            nthRow.add(i);
            search(n, results, nthRow);
            nthRow.remove(nthRow.size() - 1);
        }
    }
    private static ArrayList<String> draw (ArrayList<Integer> nthRow) {
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < nthRow.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nthRow.size(); j++) {
                sb.append(nthRow.get(i) == j ? 'Q': '.');
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    /**
     *
     * @param nthRow how many rows have been considered
     * @param x nth column of the current row
     * // @param n size 先用n带入写一写发现rowIdx只需要到currRow截至即可
     * @return
     */
    private static boolean valid (ArrayList<Integer> nthRow, int x) {
        int currRows = nthRow.size();
        for (int rowIdx = 0; rowIdx < currRows; rowIdx++) { //先用n带入写一写发现rowIdx只需要到currRow截至即可
            if (nthRow.get(rowIdx) == x) { //Cannot be in same column
                return false;
            }
            if (rowIdx - nthRow.get(rowIdx) == currRows - x) {
                return false;
            }
            if (rowIdx + nthRow.get(rowIdx) == currRows + x) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(solveNQueens(n).toString());
    }
}
