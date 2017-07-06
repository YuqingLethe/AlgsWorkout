package LintCode.Binary.LinkedList;

public class LintLL511SwapTwoNodesinLinkedList {
    /**
     * 2017/7/6.
     */
    public class Solution {
        public ListNode swapNodes(ListNode head, int v1, int v2) {
            // Write your code here
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
