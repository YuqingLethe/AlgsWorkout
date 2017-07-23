package LintCode.Binary.Heap;

import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/7/23.
 */
public class LintHeap606KthLargestElementII {
    /**
     * @param nums an integer unsorted array
     * @param k an integer from 1 to n
     * @return the kth largest element
     */
    public int kthLargestElement2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll(); //也可以用peek()
    }
}
