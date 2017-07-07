package LintCode.Binary.Array;

/**
 * Created by Administrator on 2017/7/7.
 */
public class LintArray6MergeTwoSortedArrays {
    /**
     * 无脑比较copy
     */
    class Solution {

        public int[] mergeSortedArray(int[] A, int[] B) {
            if (A == null || A.length == 0) {
                return B;
            }
            if (B == null || B.length == 0)  {
                return A;
            }
            int lenA = A.length;
            int lenB = B.length;
            int[] ans = new int[lenA + lenB];

            int idxA = 0, idxB = 0;
            int i = 0; //idx of ans array
            while(idxA != lenA && idxB != lenB) {
                if (A[idxA] <= B[idxB]) {
                    ans[i] = A[idxA++];
                } else {
                    ans[i] = B[idxB++];
                }
                i++;
            }
            while (idxA != lenA) {
                ans[i++] = A[idxA++];
            }
            while (idxB != lenB) {
                ans[i++] = B[idxB++];
            }
            return ans;
        }
    }
}
