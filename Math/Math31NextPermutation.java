package Math;

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

 For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

 For example, the next permutation of arr = [1,2,3] is [1,3,2].
 Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 Given an array of integers nums, find the next permutation of nums.

 The replacement must be in place and use only constant extra memory.



 Example 1:
 Input: nums = [1,2,3]
 Output: [1,3,2]

 Example 2:
 Input: nums = [3,2,1]
 Output: [1,2,3]

 Example 3:
 Input: nums = [1,1,5]
 Output: [1,5,1]


 Constraints:
 1 <= nums.length <= 100
 0 <= nums[i] <= 100
 */
public class Math31NextPermutation {
    /**
     * 2017/7/24找规律解法
     后来才发现原来是数学中的排列组合，比如“1，2，3”的全排列，依次是：
     1 2 3
     1 3 2
     2 1 3
     2 3 1
     3 1 2
     3 2 1
     每一列的数都是可用数的从小到大排列

     因此, 下一个排列组合一定是从最后一个增序开始, 与其后面第二小的交换, 然后后面的增序排列.
     http://blog.csdn.net/nomasp/article/details/49913627
     Good test case: [6 ,5, 4 ,8, 7, 5 ,1]
     */
    public class Lint52 {
        public int[] nextPermutation(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return nums;
            }
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    int second = findNextSmallestInRestList(nums, i + 1, nums[i]);
                    if (second == -1)  {
                        sort(nums, 0);
                    } else {
                        swap(nums, i, second);
                        sort(nums, i + 1);
                    }
                    return nums; //在loop里面用if直接结束程序的, 都可以分开步骤, 先找到这样的index, break, 再进行下面的操作
                }
            }
            sort(nums, 0);
            return nums;

        }
        private int findNextSmallestInRestList(int[] nums, int startIdx, int crt) {
            int minIdx = startIdx;
            int min = nums[minIdx];
            if (minIdx == nums.length - 1) { //递增序列必然后面有比之大的数字
                return minIdx;
            }

            for (int i = startIdx + 1; i < nums.length; i++) {
                if (nums[i] > crt && nums[i] < min) {
                    min = nums[i];
                    minIdx = i;
                }
            }
            return minIdx;
        }

        private void swap (int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        private void sort(int[] nums, int start) {
            for (int i = start; i < nums.length - 1; i++) {
                int min = nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    if (min > nums[j]) {
                        min = nums[j];
                        swap(nums, i, j);
                    }
                }

            }
        }
    }

    /**
     * July 2017
     * 九章的找规律解法, 和上面的略有不同. 找到最后的增序之后先排列再swap, 这样找到第二小的比较方便
     * May 2022依旧不会做 抄答案。Leetcode的答案比较整洁
     */
    public static class Solution_StandardSolution {
        public class Solution {
            private void reverse(int[] nums, int start) {
                int i = start, j = nums.length - 1;
                while (i < j) {
                    swap(nums, i, j);
                    ++ i;
                    -- j;
                }
            }
            private void swap(int[] nums, int leftIdx, int rightIdx) {
                int tmp = nums[leftIdx];
                nums[leftIdx] = nums[rightIdx];
                nums[rightIdx] = tmp;
            }
            public void nextPermutation(int[] nums) {
                int N = nums.length;
                int i = N - 2;
                //Find the first valley from right
                while (i >= 0 && nums[i] >= nums[i + 1]) {
                    i --;
                }
                // Find the next largest number on the right and swap them
                if (i >= 0) {
                    int j = N - 1;
                    while (nums[j] <= nums[i]) {
                        j --;
                    }
                    swap(nums, i, j);
                }
                // reverse the right side from leftIdx. 右面一定是降序
                reverse(nums, i + 1);
            }
        }
    }
}
