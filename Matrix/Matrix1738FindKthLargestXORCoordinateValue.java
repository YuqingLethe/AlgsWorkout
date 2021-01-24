package Matrix;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
 *
 * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 *
 * Find the kth largest value (1-indexed) of all the coordinates of matrix.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[5,2],[1,6]], k = 1
 * Output: 7
 * Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
 * Example 2:
 *
 * Input: matrix = [[5,2],[1,6]], k = 2
 * Output: 5
 * Explanation: The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
 * Example 3:
 *
 * Input: matrix = [[5,2],[1,6]], k = 3
 * Output: 4
 * Explanation: The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.
 * Example 4:
 *
 * Input: matrix = [[5,2],[1,6]], k = 4
 * Output: 0
 * Explanation: The value of coordinate (1,1) is 5 XOR 2 XOR 1 XOR 6 = 0, which is the 4th largest value.
 */
public class Matrix1738FindKthLargestXORCoordinateValue {
    /**
     * 20210123 20min 学习matrix XOR的性质！
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] target = new int[m][n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(m*n, Collections.reverseOrder());

        target[0][0] = matrix[0][0];
        pq.add(target[0][0]);//不要忘记加第一个值、检查的时候target准确性检查一遍，corner case，还要看pq的准确性
        //Column 0
        for (int i = 1; i < m; i++) {
            target[i][0] = target[i - 1][0] ^ matrix[i][0];
            pq.add(target[i][0]);
        }
        //Row 0
        for (int i = 1; i < n; i ++) {
            target[0][i] = target[0][i - 1] ^ matrix[0][i];
            pq.add(target[0][i]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                target[i][j] = target[i][j - 1] ^ target[i - 1][j] ^ target[i - 1][j - 1] ^ matrix[i][j];
                pq.add(target[i][j]);
            }
        }

        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.peek();
    }

}
