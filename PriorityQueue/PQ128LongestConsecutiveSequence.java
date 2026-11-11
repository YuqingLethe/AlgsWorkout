package PriorityQueue;

import java.util.PriorityQueue;

public class PQ128LongestConsecutiveSequence {
    class SolutionPriorityQueue {
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            final int N = nums.length;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i ++) {
                pq.add(nums[i]);
            }

            int answer = 1; // 别忘了我们求最长
            int currMax = 1;
            Integer prevNum = pq.poll();
            while (!pq.isEmpty()) {
                Integer curr = pq.poll();
                if (curr.equals(prevNum)) { // 写curr == prevNum对于-128 ～ 127的数字会报错
                    continue;
                }
                if (curr.equals(prevNum + 1)) {
                    currMax ++;
                    answer = Math.max(answer, currMax); // 别忘了我们求最长
                } else {
                    currMax = 1;
                }
                prevNum = curr;
            }
            return answer;
        }
    }
}
