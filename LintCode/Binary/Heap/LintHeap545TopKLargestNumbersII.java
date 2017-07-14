package LintCode.Binary.Heap;

import java.util.*;

public class LintHeap545TopKLargestNumbersII {
    /**
     * 2017/7/13
     * PriorityQueue只能保证第一个值为最大或最小, 因此用iterator取无法得到最什么的k个, 因此根据题意, 只能用minHeap
     */
    public class Solution {
        private Queue<Integer> pq;
        private int k;
        private int min;

        public Solution(int k) {
            // initialize your data structure here.
            this.k = k;
//        pq = new PriorityQueue<>(k, new comp()); //如果要写comparator记得class name后面的()
            pq = new PriorityQueue<>();
            this.min = Integer.MIN_VALUE;

        }

        public void add(int num) {
            if (pq.size() == k && num < pq.peek()) {//只有size够了才poll
                return;
            }
            if (pq.size() == k && num > pq.peek()) {
                pq.poll();
            }
            pq.add(num);

        }

        public List<Integer> topk() {
            List<Integer> list = new ArrayList<>();
            Iterator it = pq.iterator();
            int idx = 0;
            while(it.hasNext() && idx ++ < k) {
                list.add((Integer) it.next());
            }
            Collections.sort(list, Collections.reverseOrder());

            return list;
        }
/*
    class comp implements Comparator<Integer> {
        @Override
        public int compare (Integer a, Integer b) {
            if (a < b) {
                return 1;
            } else if (a > b) {
                return -1;
            } else {
                return 0;
            }
        }
    }
*/
    };
}
