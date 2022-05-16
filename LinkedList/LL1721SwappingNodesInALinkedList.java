package LinkedList;

public class LL1721SwappingNodesInALinkedList {
    /**
     * May 2022 没想清楚第三个pass怎么走, 因为k might > n/2
     * 尽量避免indx和length的计算, 越简单越好
     */
    class ThreePass {
        public ListNode swapNodes(ListNode head, int k) {
            if (k == 0 || head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            int n = 1;
            while (head.next != null) {
                head = head.next;
                n++;
            }
            ListNode a = dummy;
            ListNode b = dummy;
            for (int i = 0; i < k; ++ i) {
                a = a.next;

            }
            for (int i = 0; i < (n - k) + 1; ++i) {
                b = b.next;
            }
            int tmp = a.val;
            a.val = b.val;
            b.val = tmp;
            return dummy.next;
        }
    }

    /**
     * 2017/7/6.
     * 不一样的题目, in place查找两个values替换节点
     */
    public class Lint511_FindValuesSwapInPlace {
        public ListNode swapNodes(ListNode head, int v1, int v2) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode crt = dummy;
            ListNode prev1 = null;
            ListNode prev2 = null;
            while (crt.next != null) {
                if (crt.next.val == v1) {
                    prev1 = crt;
                }
                if (crt.next.val == v2) {
                    prev2 = crt;
                }
                crt = crt.next;
            }

            if (prev1 == null || prev2 == null) {
                return dummy.next;
            }

            //swap two nodes
            ListNode nodev1 = prev1.next;
            ListNode nodev2 = prev2.next;
            if (nodev2.next == nodev1) { //不要忘了这个情况
                ListNode tmp = nodev1.next;
                prev2.next = nodev1;
                nodev1.next = nodev2;
                nodev2.next = tmp;
                return dummy.next;
            }

            prev1.next = nodev2;
            ListNode tmp = nodev2.next;
            if (nodev1.next == nodev2) {
                nodev2.next = nodev1;
                nodev1.next = tmp;
            } else {
                nodev2.next = nodev1.next;
                prev2.next = nodev1;
                nodev1.next = tmp;
            }

            return dummy.next;
        }
    }
}
