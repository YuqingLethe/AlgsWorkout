package TwoPointers;

import java.util.ArrayDeque;

public class SlidingWindow239SlidingWindowMaximum {
    /**
     * Crib answers Sep 10
     * 必须想办法把之前的index值都删掉. 否则答案不准确
     */
    class Deque_PQ {
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        int[] nums;
        public int[] maxSlidingWindow(int[] nums, int k) {
            final int n = nums.length;
            if (nums == null || nums.length == 0) {
                return new int[0];
            }
            if (k == 1) {
                return nums;
            }


            int max_idx = 0;
            this.nums = nums;

            for (int i = 0; i < k; ++i) {
                clean_deque(i, k);
                deq.addLast(i);
                if (nums[i] > nums[max_idx]) {
                    max_idx = i;
                }
            }

            int[] output = new int[n - k + 1];
            output[0] = nums[max_idx];

            for (int i = k; i < n; ++i) {
                clean_deque(i, k);
                deq.addLast(i);
                output[i - k + 1] = nums[deq.getFirst()];
            }
            return output;
        }

        private void clean_deque(int i, int k) {
            if (!deq.isEmpty() && deq.getFirst() == i - k) {
                deq.removeFirst();
            }

            while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) { //用这个方法保证了最前面的一直是最大值!
                deq.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, -1};
        int k = 1;
        int[] expect = {1, 1};

        nums = new int[]{9,10,9,4,2,3,11};
    }
}
