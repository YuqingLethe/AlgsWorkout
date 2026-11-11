package HashSet;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class HS128LongestConsecutiveSequence {
    /**
     * 这个解法不行时间复杂度不对，PQ add时间复杂度是logN，poll时间复杂度也是logN，所以整个是nlogn不符合题目要求
     */
    class SolutionPQ {

        /**
         * 官方答案 HashSet+IntelligenceTraversal，但是Time Exceed Limit
         */
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            // Check consecutive by get just the beginnign of a consecutive sequence
            int longestStrike = 0;
            for (int num : nums) {
                if (!set.contains(num - 1)) {
                    int currStrike = 1;
                    int curr = num;
                    while (set.contains(curr + 1)) {
                        currStrike ++;
                        curr ++;
                    }
                    longestStrike = Math.max(longestStrike, currStrike);
                }
            }
            return longestStrike;
        }

        /**
         * 这个自己想的，被accept但是时间复杂度不对。
         */
        public int longestConsecutivePQ(int[] nums) {
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
