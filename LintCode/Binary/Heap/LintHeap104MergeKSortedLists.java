package LintCode.Binary.Heap;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/7/14.
 */
public class LintHeap104MergeKSortedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    /**
     * Heap
     */
    public class SolutionHeap {
        public ListNode mergeKLists(List<ListNode> lists) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (ListNode ln : lists) {
                while(ln != null) {
                    pq.offer(ln.val);
                    ln = ln.next;
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode tmp = dummy;
            while(pq.size() > 0) {
                ListNode ln = new ListNode(pq.poll());
                tmp.next = ln;
                tmp = tmp.next;
            }
            return dummy.next;
        }
    }
}
