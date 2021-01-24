package HashMap;

import java.util.*;

/**
 * Created by yuqing on 1/23/21.
 * 主要看的答案： https://leetcode.com/problems/sort-the-matrix-diagonally/discuss/1030582/2-Solution-or-Easy-to-Understand-or-Using-Map-and-PriorityQueue
 * hint1：diagonal有特点就是j-i的值相同
 * hint2：diagonal遍历的特点是row column都+1
 * Hint3：PriorityQueue可以natual order poll最小值
 */
public class HM1329SortTheMatrixDiagonally {
    /**
     * 别人的solution，用Map和PriorityQueue
     * Runtime：80ms
     * Memory 40MB
     * TC - O(mnlog(d)) -> m - num of row, n - num of col, d - avg lenght of diagonal
     * SC - O(mn)
     *
     * @param mat
     * @return
     */
    public int[][] diagonalSortBest(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(j - i, p -> new PriorityQueue()).add(mat[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                mat[i][j] = map.get(j - i).poll();
            }
        }
        return mat;
    }


    /**
     * 这是我一开始的思路，但是没想通hint1和2所以没做出来
     * TC - O((m + n)*dlogd)
     * Runtime: 5 ms, faster than 82.42% of Java online submissions for Sort the Matrix Diagonally.
     * Memory Usage: 40.1 MB, less than 42.38% of Java online submissions
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            sortDiagonally(mat, i, 0);
        }
        for (int i = 0; i < n; i++) {
            sortDiagonally(mat, 0, i);
        }
        return mat;
    }
    private void sortDiagonally(int[][] mat, int r, int c) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = r, j = c; i < mat.length && j < mat[0].length; i++, j++) {
            al.add(mat[i][j]);
        }
        Collections.sort(al);
        for (int num : al) {
            mat[r++][c++] = num;
        }
    }

    /**
     * TODO: use split and priorityQueueß
     * @param mat
     * @return
     */
    public int[][] diagonalSortPriorityAndSplit(int[][] mat) {
        return mat;
    }
}
