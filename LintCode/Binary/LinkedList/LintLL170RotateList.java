package LintCode.Binary.LinkedList;

/**
 * Created by Administrator on 2017/7/6.
 */
public class LintLL170RotateList {
    /**
     * 数数的方法, build 环, 数到len - k个值再把环拆开
     */
    public class Solution {

        public ListNode rotateRight(ListNode head, int k) {
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
            //break the cycle
            head = dummy.next;
            dummy.next = null;
            return head;
        }
    }
}
