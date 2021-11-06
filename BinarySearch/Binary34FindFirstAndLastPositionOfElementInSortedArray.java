package BinarySearch;

public class Binary34FindFirstAndLastPositionOfElementInSortedArray {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int firstOccurrence = this.findBound(nums, target, true);
            if (firstOccurrence == -1) {
                return new int[]{-1, -1};
            }
            int lastOccurrence = this.findBound(nums, target, false);
            return new int[]{firstOccurrence, lastOccurrence};
        }

        private int findBound(int[] nums, int target, boolean isFirst) {
            int begin = 0, end = nums.length - 1;
            while (begin <= end) {
                int mid = begin + (end - begin) / 2;
                if (nums[mid] == target) {
                    if (isFirst) {
                        if ( mid == 0 || nums[mid - 1] != target) {
                            return mid;
                        }
                        end = mid - 1;
                    } else {
                        if (mid == nums.length - 1 || nums[mid + 1] != target) {
                            return mid;
                        }
                        begin = mid + 1;
                    }
                } else if (nums[mid] > target){
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            }
            return -1;
        }
    }

    /**
     * Created by Administrator on 2017/7/25.
     */
    public static class LintBinary462TotalOccurrenceOfTarget {
        /**
         * 2017/7/25
         */
        public int totalOccurrence(int[] A, int target) {
            if (A == null || A.length == 0) {
                return 0;
            }
            int lo = 0;
            int hi = A.length - 1;
            int firstIdx = 0;
            while(lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[mid] < target) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            if (A[lo] == target)  {
                firstIdx = lo;
            } else {
                firstIdx = hi;
            }

            if (A[firstIdx] != target) {
                return 0;
            }

            lo = firstIdx;
            hi = A.length - 1;
            while(lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[mid] == target) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            if (A[hi] == target) {
                return hi - firstIdx + 1;
            }
            return lo - firstIdx + 1;
        }
    }
}
