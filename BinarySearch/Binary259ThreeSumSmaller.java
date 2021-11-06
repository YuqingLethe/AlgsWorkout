package BinarySearch;

import java.util.Arrays;

public class Binary259ThreeSumSmaller {

    class Solution {
        public int threeSumSmaller(int[] nums, int target) {
            // 4pm -

            // return binarySearch(nums, target, 0);

            Arrays.sort(nums);
            int result = 0;
            for (int i = 0; i < nums.length - 2; i ++) {
                result += twoSumSmaller(nums, target - nums[i], i + 1); //注意開始位置是i + 1
            }
            return result;
        }

        private int twoSumSmaller(int[] nums, int target, int start) {
            // already sorted array
            int sum = 0;
            for (int i = start; i < nums.length; i ++) {
                int j = binarySearch(nums, target - nums[i], i);
                sum += j - i;
            }
            return sum;
        }

        private int binarySearch(int[] nums, int target,  int startIndex ) {
            int left = startIndex;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (left + right + 1) / 2;
                if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

    }

    class TwoPointers {
        public int threeSumSmaller(int[] nums, int target) {
            Arrays.sort(nums);
            int result = 0;
            // 這裏不能把停止條件變成i < nums.length - 2 && nums[i] <= target;  因爲負數
            for (int i = 0; i < nums.length - 2; i++) { //持續忘記-2!!!!
                // System.out.println("i=" + i);
                result += twoSumSmallerByTwoPointers(nums, target - nums[i], i + 1); //持續忘記初始地方是i + 1
                // System.out.println("result=" + result);
            }
            return result;
        }


        private int twoSumSmallerByTwoPointers(int[] nums, int target, int start) {
            // Sorted array
            int sum = 0;

            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    sum += right - left;
                    left ++;
                } else {
                    right --;
                }
            }
            return sum;
        }
    }
    static void main() {
        int[] array = {-1,1,-1,-1};
        int target = -1;

//        int[] array = {0,-4,-1,1,-1,2};
//        int target = -5;
    }
}
