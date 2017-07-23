package LintCode.Binary.Heap;
import java.util.PriorityQueue;
import java.util.Random;
/**
 * Created by Administrator on 2017/7/23.
 */
public class LintHeap544TopKLargestNumbers {
    /**
     * QuickSort 教学指导
     * http://www.jiuzhang.com/solution/top-k-largest-numbers/
     */
    class SolutionQuickSort {
        // base on quicksort
        class Solution {
            /*
             * @param nums an integer array
             * @param k an integer
             * @return the top k largest numbers in array
             */
            public int[] topk(int[] nums, int k) {
                // Write your code here
                quickSort(nums, 0, nums.length - 1, k);

                int[] topk = new int[k];
                for (int i = 0; i < k; ++i)
                    topk[i] = nums[i];

                return topk;
            }

            private void quickSort(int[] A, int start, int end, int k) {
                if (start >= k)
                    return;

                if (start >= end) {
                    return;
                }

                int left = start, right = end;
                // key point 1: pivot is the value, not the index
                Random rand = new Random(end - start + 1);
                int index = rand.nextInt(end - start + 1) + start;
                int pivot = A[index];

                // key point 2: every time you compare left & right, it should be
                // left <= right not left < right
                while (left <= right) {
                    // key point 3: A[left] < pivot not A[left] <= pivot
                    while (left <= right && A[left] > pivot) {
                        left++;
                    }
                    // key point 3: A[right] > pivot not A[right] >= pivot
                    while (left <= right && A[right] < pivot) {
                        right--;
                    }

                    if (left <= right) {
                        int temp = A[left];
                        A[left] = A[right];
                        A[right] = temp;

                        left++;
                        right--;
                    }
                }

                quickSort(A, start, right, k);
                quickSort(A, left, end, k);
            }

        }
    }


    /**
     * minHeap方法 2017/7/23
     */
    class SolutionMinHeap {
        public int[] topk(int[] nums, int k) {
            int[] ans = new int[k];
            if (nums == null || nums.length < k) {
                return ans;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) {
                pq.offer(nums[i]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            System.out.println();
            for (int i = k - 1; i >= 0; i--) {
                ans[i] = pq.poll();
            }
            return ans;
        }
    };
}
