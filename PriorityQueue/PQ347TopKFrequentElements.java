package PriorityQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PQ347TopKFrequentElements {
    /**
     * 从AI问syntax问出来的解题思路，因为单靠PQ是没有办法增加freq的
     */
    class Solution202610 {
        private class Item {
            int val;
            int freq;
            Item (int val, int freq) {
                this.val = val;
                this.freq = freq;
            }
        }
        public int[] topKFrequent(int[] nums, int k) {
            // Get frequency map
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i ++) {
                int currFreq = map.getOrDefault(nums[i], 0);
                map.put(nums[i], currFreq + 1);
            }



            // push to priority queue
            PriorityQueue<Item> q = new PriorityQueue<>(
                    (i1, i2) ->
                            Integer.compare(i2.freq, i1.freq));

            for (Integer curr : map.keySet()) {
                Item currItem = new Item(curr, map.get(curr));
                q.add(currItem);
            }

            // get top k
            int[] result = new int[k];
            for (int i = 0; i < k; i ++) {
                result[i] = q.poll().val;
            }
            return result;
        }
    }
}
