package LintCode.Binary.Binary;

/**
 * Created by Administrator on 2017/7/26.
 */
public class LintBinary460KClosestNumbersInSortedArray {
    /**
     * distance相同时候的先后顺序
     * target不存在的情况
     * 2017/7/26
     */
    public class Solution {

        public int[] kClosestNumbers(int[] A, int target, int k) {
            int len = Math.min(A.length, k);
            int[] ans = new int[len];
            if (A == null || A.length == 0) {
                return ans;
            }
            //binary search find the target number
            int index = binarySearch(A, target);
            int i = index - 1;
            int j = index;
            int count = 0;
            do { //相同数字distance相同, 因此不存在谁先谁后的情况
                if (i < 0 || j == index) {
                    ans[count] = A[j ++];
                    continue;
                }
                if (j == A.length) {
                    ans[count] = A[i --];
                    continue;
                }
                int diffi = target - A[i];
                int diffj = A[j] - target;

                if (diffi <= diffj) {
                    ans[count] = A[i --];
                } else {
                    ans[count] = A[j ++];
                }
            } while (++count < len);

            return ans;
        }
        //return the index of the first target
        private int binarySearch(int[] A, int target) {
            int lo = 0;
            int hi = A.length - 1;
            while(lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[mid] < target) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            if (A[lo] >= target) { //不要忘了target不存在的情况[22,25,100], 15, 5
                return lo;
            }
            return hi;
        }
    }
}
