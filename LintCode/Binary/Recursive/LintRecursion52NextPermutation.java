package LintCode.Binary.Recursive;

/**
 * Created by Administrator on 2017/7/24.
 */
public class LintRecursion52NextPermutation {
    /**
     * 7/23/2017 找规律解法
     后来才发现原来是数学中的排列组合，比如“1，2，3”的全排列，依次是：
     1 2 3
     1 3 2
     2 1 3
     2 3 1
     3 1 2
     3 2 1
     每一列的数都是可用数的从小到大排列

     因此, 下一个排列组合一定是从最后一个增序开始, 与其后面第二小的交换, 然后后面的增序排列.

     Good test case: [6 ,5, 4 ,8, 7, 5 ,1]
     */
    public class SolutionPattern {
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
     * 九章的找规律解法, 和上面的略有不同. 找到最后的增序之后先排列再swap, 这样找到第二小的比较方便
     */
    //todo: write my own
    public static class SolutionJiuzhangPattern {
        public class Solution {
            /**
             * @param nums: A list of integers
             * @return: A list of integers that's next permuation
             */
            public void swapItem(int[] nums, int i, int j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            public void swapList(int[] nums, int i, int j) {
                while (i < j) {
                    swapItem(nums, i, j);
                    i ++; j --;
                }
            }
            public int[] nextPermutation(int[] nums) {
                int len = nums.length;
                if ( len <= 1)
                    return nums;
                int i = len - 1;
                while (i > 0 && nums[i] <= nums[i - 1])
                    i --;
                swapList(nums, i, len - 1);
                if (i != 0) {
                    int j = i;
                    while (nums[j] <= nums[i - 1]) j++;
                    swapItem(nums, j, i-1);
                }
                return nums;
            }
        }
    }


}
