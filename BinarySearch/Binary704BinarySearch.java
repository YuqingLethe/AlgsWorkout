package BinarySearch;

public class Binary704BinarySearch {
    class Solution {
        public int search1(int[] nums, int target) {

            int start = 0;
            int end = nums.length - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else {
                    start = mid++;
                }
            }
            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
            return -1;

        }

        public int search(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    end = mid;
                } else {
                    start = mid++;
                }
            }
            if (nums[0] == target) {
                return 0;
            }
            if (nums[nums.length - 1] == target) {
                return nums.length - 1;
            }
            return -1;

        }
    }
}
