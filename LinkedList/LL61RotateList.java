package LinkedList;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class LL61RotateList {
    /**
     *  2017/7/6.
     * 数数的方法, build 环, 数到len - k个值再把环拆开
     */
    public static class LintLL170RotateList_Ring {
        public ListNode rotateRight_July2017(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            if (k == 0) {
                return head;
            }
            // Find how many nodes in total
            ListNode dummy = head;
            int total = 1;
            while(dummy.next != null) {
                dummy = dummy.next;
                total++;
            }
            if (k >= total) {
                k = k % total;
            }
            //build a cycle and find the total - k th node as the head of the new list
            dummy.next = head;
            dummy = head;
            int count = total - k;
            while(count > 0) { //stop at the node before the head of the new list
                dummy = dummy.next;
            }
            //break the ring
            head = dummy.next;
            dummy.next = null;
            return head;
        }

        public ListNode rotateRight_April2022(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            // Find the length and close the ring
            ListNode last= head;
            int len = 1;
            while (last.next != null) {
                last = last.next;
                len ++;
            }
            last.next = head;
            // Save movings if k > len
            int rest = k % len;
            // find new tail : (n - k % n - 1)th node
            for (int i = 1; i < len - rest; ++i) {
                head = head.next;
            }
            // Find new head : (n - k % n)th node
            ListNode dummy = new ListNode(0);
            dummy.next = head.next;
            head.next = null; //别忘了断开环!
            return dummy.next;
        }
    }

    /**
     * Self 移动头到末尾 April 2022
     */
    public static class Move_Front_To_End {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            // Find the length and the last node
            ListNode last= head;
            int len = 1;
            while (last.next != null) {
                last = last.next;
                len ++;
            }

            // Save movings if k > len
            int rest = k % len;

            for (int i = 0; i < len - rest; ++i) {
                last.next = dummy.next;
                dummy.next = dummy.next.next;
                last.next.next = null;
                last = last.next;
            }
            return dummy.next;
        }
    }
}
