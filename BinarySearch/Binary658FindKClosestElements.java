package BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 *   The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 *
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class Binary658FindKClosestElements {
    /**
     * Aug 2022 Crib answer
     */
    class BinarySearch_SlidingWindow {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            // Binary search to find x
            List<Integer> res = new ArrayList<>();
            int left = 0, right = arr.length - 1;
            int xIdx = 0; //没必要设置xIdx, 更何况如果找不到x则依旧返回最近的k个数字
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == x) {
                    left = mid;
                    break;
                }
                if (arr[mid] < x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 答案的binary search, 最简洁!
            // while (left < right) {
            //     int mid = (left + right) / 2;
            //     if (x - arr[mid] > arr[mid + k] - x) {
            //         left = mid + 1;
            //     } else {
            //         right = mid;
            //     }
            // }

            // Two pointers to find nearest k numbers
            left = left - 1;
            right = left + 1;
            while (right - left - 1 < k) {
                int leftDiff, rightDiff;
                if (left == -1) { //这里巧妙, 左边到边界了只能动右面
                    right += 1;
                    continue;
                }
                if (right == arr.length || (x - arr[left] <= arr[right] - x)) {
                    left --;
                } else {
                    right ++;
                }
            }
            for (int i = left + 1; i < right; ++i) {
                res.add(arr[i]);
            }
            return res;
        }
    }

    /**
     * TODO: crib answer, comparator
     */
    class Sort_CustomComparator {

    }
    /**
     * Created by Administrator on 2017/7/26.
     */
    public static class LintBinary460KClosestNumbersInSortedArray {
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
}
