package BinarySearch;

import java.util.LinkedList;

public class Binary852PeakIndexOfAMountainArray {
    /**
     * Created by Administrator on 2017/6/14.
     */
    public static class LintBinary585MaximumNumberinMountainSequence {
        /**
         * 这个方法通不过时间复杂度, 不过可以实现 2017/6/14
         */
        public int mountainSequenceDupRemoveFirst(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            LinkedList<Integer> nodup = new LinkedList<>();
            nodup.add(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nodup.peekLast()) {
                    nodup.add(nums[i]);
                }
            }
            int lo = 0;
            int hi = nodup.size() - 1;
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nodup.get(mid) < nodup.get(mid + 1)) {
                    lo = mid + 1;
                } else if (nodup.get(mid) > nodup.get(mid + 1)) {
                    hi = mid;
                }
            }
            if (nodup.get(hi) > nodup.get(lo)) {
                return nodup.get(hi);
            } else {
                return nodup.get(lo);
            }
        }
        /**
         * 2017/7/27
         * 在二分法while里面比较相邻数字的大小, 最后两位其实已经比过了, 如果最后的大, lo会落在最后一位, 不需要单独拎出来比较
         *
         * 最后的七行可以用 return Math.max(nums[hi], nums[lo]); 代替
         */
        public int mountainSequence(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int lo = 0;
            int hi = nums.length - 1;
            while(lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] <= nums[mid + 1]) {
                    lo = mid;
                } else if (nums[mid] > nums[mid + 1]) {
                    hi = mid;
                }
            }
            if (nums[lo] > nums[hi]) {
                return nums[lo];
            }
            if (nums.length > 1 && nums[nums.length - 1] > nums[nums.length - 2]) {
                return nums[nums.length - 1];
            }
            return nums[hi];
        }
    }

    /**
     * 9/20/2021
     */
    class Solution {
        public int peakIndexInMountainArray(int[] arr) {
            int left = 0;
            int right = arr.length - 1;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (isLeftAsce(arr, mid) && isRightDesc(arr, mid)) {
                    return mid;
                } else if (isLeftAsce(arr, mid) && !isRightDesc(arr, mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

        private boolean isLeftAsce(int[] arr, int p) {
            if (p <= 0) {
                return true;
            } else if (arr[p - 1] < arr[p]) {
                return true;
            }
            return false;
        }

        private boolean isRightDesc(int[] arr, int p) {
            if (p >= arr.length - 1) {
                return true;
            }
            if (arr[p] > arr[p + 1]) {
                return true;
            }
            return false;
        }
    }
}
