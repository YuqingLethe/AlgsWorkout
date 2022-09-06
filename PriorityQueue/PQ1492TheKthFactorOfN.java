package PriorityQueue;

import java.util.PriorityQueue;
import java.util.Stack;

public class PQ1492TheKthFactorOfN {
    /**
     * Crib solution from leetcode
     */
    static class Priority_Queue {
        private PriorityQueue<Integer> pq;
        public int kthFactor(int n, int k) {
            this.pq = new PriorityQueue<>((a, b) -> (b - a));

            int sqrtN = (int) Math.sqrt(n);
            for (int i = 1; i <= sqrtN; ++i) {
                if (n % i != 0) {
                    continue;
                }
                pushToPQ(i, k);
                if (i != n / i) {
                    pushToPQ(n / i, k);
                }
            }
            if (pq.size() < k) {
                return -1;
            }
            // System.out.println(pq.toString());
            return pq.poll();
        }

        private void pushToPQ(int x, int k) {
            pq.add(x);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    /**
     * 自己做的, 写好了test case改了3个地方, leetcode上面一遍过.
     *
     * Push the factor i and its pair N/i to two stacks. One is for i , one if for its pair N/i
     * Smaller stack contains i, when popup, it is descending order;
     * Larger stack contains N/i, when popup, it is ascending order;
     *
     * Find all factors while comparing smaller size and k;
     * If smaller size reached k, pop the kth
     * Otherwise, until all factors found, pop the larger until Kth.
     */
    static class TwoStack {
        public int kthFactor(int n, int k) {
            Stack<Integer> smaller = new Stack<>();; // Store smaller factor
            Stack<Integer> larger = new Stack<>(); // Store larger factor from the end

            // Traverse the till n/2 to get all factors
            for (int i = 1; i <= Math.max(n/2, 1); ++i) { //这里Math.max(n/2, 1), 要考虑到1的factor范围, 这个范围最小是1!!
                if (n % i == 0) {
                    int otherFactor = n / i;
                    if (!larger.isEmpty() && (i >= larger.peek() || otherFactor >= larger.peek())) {
                        break; // Break loop if all factors found
                    }
                    smaller.push(i);
                    if (smaller.size() == k) { // If first K factors found, return result directly;
                        return smaller.peek();
                    }
                    if (otherFactor != i) {
                        larger.push(otherFactor);
                    }
                }
            }
            if (smaller.size() + larger.size() < k) { // 这个之前忘记了, 情况是存在的!!! 应该一想到就写个note
                return -1;
            }
            // Count the Kth factor in larger stack by pop in ascending order
            int prefixFactor = smaller.size();
            while (prefixFactor + 1 < k) { // 这里是先比对后操作, 因此计数总在操作前面!!!!
                larger.pop();
                prefixFactor ++; //用while别忘了自增, 总是忘记!!!
            }
            return larger.pop();
        }
    }
    public static void main(String [] args) {
        printResult(1, 12, 3, 3);
        printResult(2, 7, 2, 7);
        printResult(3, 4, 4, -1);
        printResult(4, 36, 5, 6);
        printResult(5, 1, 1, 1);
        printResult(6, 2, 1, 1);
    }
    private static void printResult(int testNum, int n, int m, int expect) {
        Priority_Queue solution = new Priority_Queue();
        System.out.println("------------ Test Case " + testNum + " ------------");
        int mine = solution.kthFactor(n, m);
        System.out.println("Answer: " + mine);
        System.out.println("Expect: " + expect);

    }
}
