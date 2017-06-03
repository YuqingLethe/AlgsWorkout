package LintCode.Binary.Binary;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/31.
 */
public class LintBinary248CountOfSmallerNumber {
    /** 第一次用时15min
     * @param A: An integer array
     * @return: The number of element in the array that
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        Arrays.sort(A);
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (A.length == 0) {
                al.add(0);
                continue;
            }
            int target = queries[i];
            int lo = 0;
            int hi = A.length - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[mid] == target) {
                    hi = mid;
                    break;
                } else if (A[mid] > target) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            if (A[hi] < target) {
                al.add(hi + 1);
            } else {//忘记考虑如果有重复数字且A[hi] == target的情况
                while (hi > 0 && A[hi - 1] == A[hi]) {
                    hi--;
                }
                al.add(hi);
            }
        }
        return al;
    }
}
